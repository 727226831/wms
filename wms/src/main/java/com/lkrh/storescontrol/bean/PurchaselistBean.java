package com.lkrh.storescontrol.bean;

import java.util.List;

public class PurchaselistBean {
        private String Resultcode;
        private String ResultMessage;
        private List<Data> data;
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
    public class Data {

        private String ccode;

        public String getDdate() {
            return ddate;
        }

        public void setDdate(String ddate) {
            this.ddate = ddate;
        }

        private String ddate;
        private String cpocode;
        private String cvenabbname;
        public void setCcode(String ccode) {
            this.ccode = ccode;
        }
        public String getCcode() {
            return ccode;
        }



        public void setCpocode(String cpocode) {
            this.cpocode = cpocode;
        }
        public String getCpocode() {
            return cpocode;
        }

        public void setCvenabbname(String cvenabbname) {
            this.cvenabbname = cvenabbname;
        }
        public String getCvenabbname() {
            return cvenabbname;
        }

    }


}
