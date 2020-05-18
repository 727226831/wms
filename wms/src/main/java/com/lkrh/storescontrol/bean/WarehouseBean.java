package com.lkrh.storescontrol.bean;

import java.util.List;

public class WarehouseBean {


        private String Resultcode;
        private String ResultMessage;
        private Formdata formdata;

    public List<Posformdata> getPosformdata() {
        return posformdata;
    }

    public void setPosformdata(List<Posformdata> posformdata) {
        this.posformdata = posformdata;
    }

    private List<Posformdata> posformdata;
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

        private String cwhcode;
        private String cwhName;
        private String cposition;
        private String cPositionName;
        private String posbarcode;

        public String getCcuscode() {
            return ccuscode;
        }

        public void setCcuscode(String ccuscode) {
            this.ccuscode = ccuscode;
        }

        private String ccuscode;
        public void setCwhcode(String cwhcode) {
            this.cwhcode = cwhcode;
        }
        public String getCwhcode() {
            return cwhcode;
        }

        public void setCwhName(String cwhName) {
            this.cwhName = cwhName;
        }
        public String getCwhName() {
            return cwhName;
        }

        public void setCposition(String cposition) {
            this.cposition = cposition;
        }
        public String getCposition() {
            return cposition;
        }

        public void setCPositionName(String cPositionName) {
            this.cPositionName = cPositionName;
        }
        public String getCPositionName() {
            return cPositionName;
        }

        public void setPosbarcode(String posbarcode) {
            this.posbarcode = posbarcode;
        }
        public String getPosbarcode() {
            return posbarcode;
        }

    }

    public class Posformdata {

        private String cwhcode;
        private String cwhName;
        private String cposition;
        private String cPositionName;
        private String posbarcode;
        public void setCwhcode(String cwhcode) {
            this.cwhcode = cwhcode;
        }
        public String getCwhcode() {
            return cwhcode;
        }

        public void setCwhName(String cwhName) {
            this.cwhName = cwhName;
        }
        public String getCwhName() {
            return cwhName;
        }

        public void setCposition(String cposition) {
            this.cposition = cposition;
        }
        public String getCposition() {
            return cposition;
        }

        public void setCPositionName(String cPositionName) {
            this.cPositionName = cPositionName;
        }
        public String getCPositionName() {
            return cPositionName;
        }

        public void setPosbarcode(String posbarcode) {
            this.posbarcode = posbarcode;
        }
        public String getPosbarcode() {
            return posbarcode;
        }

    }
}
