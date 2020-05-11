package com.lkrh.storescontrol.bean;

import java.util.List;

public class DispatchBean {


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

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        private String ID;
        private String ccode;

        public String getDdate() {
            return ddate;
        }

        public void setDdate(String ddate) {
            this.ddate = ddate;
        }

        private String ddate;
        private String cwhcode;
        private String cwhname;


        public void setCcode(String ccode) {
            this.ccode = ccode;
        }
        public String getCcode() {
            return ccode;
        }



        public void setCwhcode(String cwhcode) {
            this.cwhcode = cwhcode;
        }
        public String getCwhcode() {
            return cwhcode;
        }

        public void setCwhname(String cwhname) {
            this.cwhname = cwhname;
        }
        public String getCwhname() {
            return cwhname;
        }

    }
}
