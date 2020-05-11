package com.lkrh.storescontrol.bean;

public class ConfirmBean  {


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

        private String ccode;
        private String irowno;

        public String getIrowno() {
            return irowno;
        }

        public void setIrowno(String irowno) {
            this.irowno = irowno;
        }

        private String cVenName;
        private String cpocode;
        private String cInvCode;
        private String cInvName;



        public String getcVenCode() {
            return cVenCode;
        }

        public void setcVenCode(String cVenCode) {
            this.cVenCode = cVenCode;
        }

        private String cVenCode;

        public String getMrn() {
            return mrn;
        }

        public void setMrn(String mrn) {
            this.mrn = mrn;
        }

        private String mrn;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        private String type;
        private  String describe;
        public String getCBatch() {
            return cBatch;
        }

        public void setCBatch(String cBatch) {
            this.cBatch = cBatch;
        }

        private String cBatch;
        private String iquantity;
        private String cComUnitName;
        private String barcode;
        public void setCcode(String ccode) {
            this.ccode = ccode;
        }
        public String getCcode() {
            return ccode;
        }



        public void setCVenName(String cVenName) {
            this.cVenName = cVenName;
        }
        public String getCVenName() {
            return cVenName;
        }

        public void setCpocode(String cpocode) {
            this.cpocode = cpocode;
        }
        public String getCpocode() {
            return cpocode;
        }

        public void setCInvCode(String cInvCode) {
            this.cInvCode = cInvCode;
        }
        public String getCInvCode() {
            return cInvCode;
        }

        public void setCInvName(String cInvName) {
            this.cInvName = cInvName;
        }
        public String getCInvName() {
            return cInvName;
        }

        public void setIquantity(String iquantity) {
            this.iquantity = iquantity;
        }
        public String getIquantity() {
            return iquantity;
        }

        public void setCComUnitName(String cComUnitName) {
            this.cComUnitName = cComUnitName;
        }
        public String getCComUnitName() {
            return cComUnitName;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }
        public String getBarcode() {
            return barcode;
        }

    }


}
