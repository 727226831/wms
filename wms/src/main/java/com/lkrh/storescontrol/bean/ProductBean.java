package com.lkrh.storescontrol.bean;

import java.util.List;

public class ProductBean   {


        private String Resultcode;
        private String ResultMessage;
        private ArrivalHeadBean formdata;
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

        public void setFormdata(ArrivalHeadBean formdata) {
            this.formdata = formdata;
        }
        public ArrivalHeadBean getFormdata() {
            return formdata;
        }


}
