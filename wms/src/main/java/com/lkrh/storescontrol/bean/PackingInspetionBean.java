package com.lkrh.storescontrol.bean;

public class PackingInspetionBean {


        private String Resultcode;
        private String ResultMessage;
        private Formdata formdata;
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

        public void setFormdata(Formdata formdata) {
            this.formdata = formdata;
        }
        public Formdata getFormdata() {
            return formdata;
        }

    public class Formdata {

        private String cusinvcode;
        private String packqty;

        public void setCusinvcode(String cusinvcode) {
            this.cusinvcode = cusinvcode;
        }
        public String getCusinvcode() {
            return cusinvcode;
        }

        public void setPackqty(String packqty) {
            this.packqty = packqty;
        }
        public String getPackqty() {
            return packqty;
        }

    }

}
