package com.alex.alexintentdemo.god;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;

import java.net.URISyntaxException;

public class UriUtils {

    public static Intent parseIntentUri(String uri, int flags) throws URISyntaxException {
        int i = 0;
        try {
            final boolean androidApp = uri.startsWith("android-app:");

            // Validate intent scheme if requested.
            if ((flags & (Intent.URI_INTENT_SCHEME | Intent.URI_ANDROID_APP_SCHEME)) != 0) {
                if (!uri.startsWith("intent:") && !androidApp) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Intent intent = new Intent();
                    try {
                        intent.setData(Uri.parse(uri));
                    } catch (IllegalArgumentException e) {
                        throw new URISyntaxException(uri, e.getMessage());
                    }
                    return intent;
                }
            }

            i = uri.lastIndexOf("#");
            // simple case
            if (i == -1) {
                if (!androidApp) {
                    return new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                }

                // old format Intent URI
            } else if (!uri.startsWith("#Intent;", i)) {
                if (!androidApp) {
//                    return getIntentOld(uri, flags);
                    throw new URISyntaxException(uri, "format Intent URI Error");
                } else {
                    i = -1;
                }
            }

            // new format
            Intent intent = new Intent();
            Intent baseIntent = intent;
            boolean explicitAction = false;
            boolean inSelector = false;

            // fetch data part, if present
            String scheme = null;
            String data;
            if (i >= 0) {
                data = uri.substring(0, i);
                i += 8; // length of "#Intent;"
            } else {
                data = uri;
            }

            // loop over contents of Intent, all name=value;
            while (i >= 0 && !uri.startsWith("end", i)) {
                int eq = uri.indexOf('=', i);
                if (eq < 0) eq = i - 1;
                int semi = uri.indexOf(';', i);
                String value = eq < semi ? Uri.decode(uri.substring(eq + 1, semi)) : "";

                // action
                if (uri.startsWith("action=", i)) {
                    intent.setAction(value);
                    if (!inSelector) {
                        explicitAction = true;
                    }
                }

                // categories
                else if (uri.startsWith("category=", i)) {
                    intent.addCategory(value);
                }

                // type
//                else if (uri.startsWith("type=", i)) {
//                    intent.mType = value;
//                }

                // launch flags
                else if (uri.startsWith("launchFlags=", i)) {
                    intent.addFlags(Integer.decode(value).intValue());
//                    Intent.FLAG_ACTIVITY_NEW_TASK
//                    if ((flags& Intent.URI_ALLOW_UNSAFE) == 0) {
//                        intent.mFlags &= ~IMMUTABLE_FLAGS;
//                    }
                }

                // package
                else if (uri.startsWith("package=", i)) {
//                    intent.mPackage = value;
                    intent.setPackage(value);
                }

                // component
                else if (uri.startsWith("component=", i)) {
//                    intent.mComponent = ComponentName.unflattenFromString(value);
                    intent.setComponent(ComponentName.unflattenFromString(value));
                }

                // scheme
                else if (uri.startsWith("scheme=", i)) {
                    if (inSelector) {
//                        intent.mData = Uri.parse(value + ":");
                        intent.setData(Uri.parse(value + ":"));
                    } else {
                        scheme = value;
                    }
                }

                // source bounds
                else if (uri.startsWith("sourceBounds=", i)) {
//                    intent.mSourceBounds = Rect.unflattenFromString(value);
                    intent.setSourceBounds(Rect.unflattenFromString(value));
                }

                // selector
                else if (semi == (i + 3) && uri.startsWith("SEL", i)) {
                    intent = new Intent();
                    inSelector = true;
                }

                // extra
                else {
                    String key = Uri.decode(uri.substring(i + 2, eq));
                    // add EXTRA
                    if (uri.startsWith("S.", i)) {
                        intent.putExtra(key, value);
                    } else if (uri.startsWith("B.", i)) {
                        intent.putExtra(key, Boolean.parseBoolean(value));
                    } else if (uri.startsWith("b.", i)) {
                        intent.putExtra(key, Byte.parseByte(value));
                    } else if (uri.startsWith("c.", i)) {
                        intent.putExtra(key, value.charAt(0));
                    } else if (uri.startsWith("d.", i)) {
                        intent.putExtra(key, Double.parseDouble(value));
                    } else if (uri.startsWith("f.", i)) {
                        intent.putExtra(key, Float.parseFloat(value));
                    } else if (uri.startsWith("i.", i)) {
                        intent.putExtra(key, Integer.parseInt(value));
                    } else if (uri.startsWith("l.", i)) {
                        intent.putExtra(key, Long.parseLong(value));
                    } else if (uri.startsWith("s.", i)) {
                        intent.putExtra(key, Short.parseShort(value));
                    } else throw new URISyntaxException(uri, "unknown EXTRA type", i);
                }

                // move to the next item
                i = semi + 1;
            }

            if (inSelector) {
                // The Intent had a selector; fix it up.
                if (baseIntent.getPackage() == null) {
//                    baseIntent.setSelector(intent);
                }
                intent = baseIntent;
            }

            if (data != null) {
                if (data.startsWith("intent:")) {
                    data = data.substring(7);
                    if (scheme != null) {
                        data = scheme + ':' + data;
                    }
                } else if (data.startsWith("android-app:")) {
                    if (data.charAt(12) == '/' && data.charAt(13) == '/') {
                        // Correctly formed android-app, first part is package name.
                        int end = data.indexOf('/', 14);
                        if (end < 0) {
                            // All we have is a package name.
                            intent.setPackage(data.substring(14));
                            if (!explicitAction) {
                                intent.setAction(Intent.ACTION_MAIN);
                            }
                            data = "";
                        } else {
                            // Target the Intent at the given package name always.
                            String authority = null;
//                            intent.mPackage = data.substring(14, end);
                            intent.setPackage(data.substring(14, end));
                            int newEnd;
                            if ((end + 1) < data.length()) {
                                if ((newEnd = data.indexOf('/', end + 1)) >= 0) {
                                    // Found a scheme, remember it.
                                    scheme = data.substring(end + 1, newEnd);
                                    end = newEnd;
                                    if (end < data.length() && (newEnd = data.indexOf('/', end + 1)) >= 0) {
                                        // Found a authority, remember it.
                                        authority = data.substring(end + 1, newEnd);
                                        end = newEnd;
                                    }
                                } else {
                                    // All we have is a scheme.
                                    scheme = data.substring(end + 1);
                                }
                            }
                            if (scheme == null) {
                                // If there was no scheme, then this just targets the package.
                                if (!explicitAction) {
                                    intent.setAction(Intent.ACTION_MAIN);
                                }
                                data = "";
                            } else if (authority == null) {
                                data = scheme + ":";
                            } else {
                                data = scheme + "://" + authority + data.substring(end);
                            }
                        }
                    } else {
                        data = "";
                    }
                }

                if (data.length() > 0) {
                    try {
//                        intent.mData = Uri.parse(data);
                        intent.setData(Uri.parse(data));
                    } catch (IllegalArgumentException e) {
                        throw new URISyntaxException(uri, e.getMessage());
                    }
                }
            }

            return intent;

        } catch (IndexOutOfBoundsException e) {
            throw new URISyntaxException(uri, "illegal Intent URI format", i);
        }
    }
}
