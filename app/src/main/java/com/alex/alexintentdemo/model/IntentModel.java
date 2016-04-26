package com.alex.alexintentdemo.model;

import java.io.Serializable;

/**
 * Created by lizetong on 16/4/25.
 */
public class IntentModel implements Serializable {


    /**
     * intent_rule : www.baidu.com
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private String intent_rule_1 ;
        private String intent_rule_2 ;
        private String intent_rule_3 ;

        public String getIntent_rule_1() {
            return intent_rule_1;
        }

        public void setIntent_rule_1(String intent_rule_1) {
            this.intent_rule_1 = intent_rule_1;
        }

        public String getIntent_rule_2() {
            return intent_rule_2;
        }

        public void setIntent_rule_2(String intent_rule_2) {
            this.intent_rule_2 = intent_rule_2;
        }

        public String getIntent_rule_3() {
            return intent_rule_3;
        }

        public void setIntent_rule_3(String intent_rule_3) {
            this.intent_rule_3 = intent_rule_3;
        }
    }
}
