package com.lkrh.storescontrol.bean;

import java.util.ArrayList;
import java.util.List;

public class TROutBywhcodeBean {
    private String Resultcode;
    private String ResultMessage;
    private List<Data> data=new ArrayList<>();
    public void setResultcode(String Resultcode) {
        this.Resultcode = Resultcode;
    }
    public String getResultcode() {
        return Resultcode;
    }

    public void setResultMessage(String ResultMessage) {
        this.ResultMessage = ResultMessage;
    }
    public String getResultMessage() {
        return ResultMessage;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }
    /**
     * Copyright 2018 bejson.com
     */

    public class Data {

        private String cTRCode;
        private String ID;
        private String cCode;

        public String getdDate() {
            return dDate;
        }

        public void setdDate(String dDate) {
            this.dDate = dDate;
        }

        private String dDate;
        private String cWhCode;
        private String cWhName;
        public void setCTRCode(String cTRCode) {
            this.cTRCode = cTRCode;
        }
        public String getCTRCode() {
            return cTRCode;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
        public String getID() {
            return ID;
        }

        public void setCCode(String cCode) {
            this.cCode = cCode;
        }
        public String getCCode() {
            return cCode;
        }



        public void setCWhCode(String cWhCode) {
            this.cWhCode = cWhCode;
        }
        public String getCWhCode() {
            return cWhCode;
        }

        public void setCWhName(String cWhName) {
            this.cWhName = cWhName;
        }
        public String getCWhName() {
            return cWhName;
        }

    }
}
