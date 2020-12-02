/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.constants;

/**
 *
 * @author truongnq
 */
public enum Rescode {

    SUCCESS("00") {
        @Override
        public String description() {
            return "Success";
        }
    },
    FAILED("01") {
        @Override
        public String description() {
            return "Failed";
        }
    },
    CREATE_VNMART_FAILED("02") {
        @Override
        public String description() {
            return "Can't create vnmart";
        }
    },
    CREATE_VNMART_EXIST("01") {
        @Override
        public String description() {
            return "Vnmart is exist";
        }
    },
    MOBILE_TID_INVALID("03") {
        @Override
        public String description() {
            return "Mobile invalid";
        }
    },
    TIMEOUT("08") {
        @Override
        public String description() {
            return "Timeout.";
        }
    },
    MAINTENANCE("96") {
        @Override
        public String description() {
            return "System is maintaing.";
        }
    },
    ACTIVE_STATUS("1") {
        @Override
        public String description() {
            return "Active success";
        }
    },
    INACTIVE_STATUS("-1") {
        @Override
        public String description() {
            return "Inactive success";
        }
    },
    EXCEPTION("99") {
        @Override
        public String description() {
            return "Internal error";
        }
    },
    NO_CONNECTION_DB("10") {
        @Override
        public String description() {
            return "No Connection to database";
        }
    },
    SQL_EXCEPTION("30") {
        @Override
        public String description() {
            return "SQL Exception";
        }
    };

    public final String i;

    Rescode(String i) {
        this.i = i;
    }

    public final String code() {
        return i;
    }

    public abstract String description();

}
