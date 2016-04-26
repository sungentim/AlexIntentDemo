package com.alex.alexintentdemo.utils;

import android.util.Log;

/**
 * Created by lizetong on 15/7/10.
 */
public class AlexLog {
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    private static final String APP_TAG = "alex_";
    private static int sLevel = LEVEL_VERBOSE;

    /**
     * get log level
     * @return
     */
    public static int getLogLevel() {
        return sLevel;
    }

    /**
     * set log level, the level lower than this level will not be logged
     *
     * @param level
     */
    public static void setLogLevel(int level) {
        sLevel = level;
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (sLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(APP_TAG + tag, msg);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void v(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(APP_TAG + tag, msg, throwable);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void v(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_VERBOSE) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.v(APP_TAG + tag, msg);
    }

    /**
     * Send a DEBUG log message
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (sLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(APP_TAG + tag, msg);
    }

    /**
     * Send a DEBUG log message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void d(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_DEBUG) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.d(APP_TAG + tag, msg);
    }

    /**
     * Send a DEBUG log message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void d(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(APP_TAG + tag, msg, throwable);
    }

    /**
     * Send an INFO log message
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (sLevel > LEVEL_INFO) {
            return;
        }
        Log.i(APP_TAG + tag, msg);
    }

    /**
     * Send an INFO log message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void i(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_INFO) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.i(APP_TAG + tag, msg);
    }

    /**
     * Send an INFO log message
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_INFO) {
            return;
        }
        Log.i(APP_TAG + tag, msg, throwable);
    }

    /**
     * Send a WARNING log message
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (sLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(APP_TAG + tag, msg);
    }

    /**
     * Send a WARNING log message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void w(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_WARNING) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.w(APP_TAG + tag, msg);
    }

    /**
     * Send a WARNING log message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void w(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(APP_TAG + tag, msg, throwable);
    }

    /**
     * Send an ERROR log message
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (sLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(APP_TAG + tag, msg);
    }

    /**
     * Send an ERROR log message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void e(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_ERROR) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.e(APP_TAG + tag, msg);
    }

    /**
     * Send an ERROR log message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void e(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(APP_TAG + tag, msg, throwable);
    }

    /**
     * Send a FATAL ERROR log message
     *
     * @param tag
     * @param msg
     */
    public static void f(String tag, String msg) {
        if (sLevel > LEVEL_FATAL) {
            return;
        }
        Log.wtf(APP_TAG + tag, msg);
    }

    /**
     * Send a FATAL ERROR log message
     *
     * @param tag
     * @param msg
     * @param args
     */
    public static void f(String tag, String msg, Object... args) {
        if (sLevel > LEVEL_FATAL) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.wtf(APP_TAG + tag, msg);
    }

    /**
     * Send a FATAL ERROR log message
     *
     * @param tag
     * @param msg
     * @param throwable
     */
    public static void f(String tag, String msg, Throwable throwable) {
        if (sLevel > LEVEL_FATAL) {
            return;
        }
        Log.wtf(APP_TAG + tag, msg, throwable);
    }
}
