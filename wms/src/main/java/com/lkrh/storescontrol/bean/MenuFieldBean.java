package com.lkrh.storescontrol.bean;


public class MenuFieldBean {


        private String fieldcode;
        private String fieldshowname;

    public String getFieldvalue() {
        return fieldvalue;
    }

    public void setFieldvalue(String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }

    private String fieldvalue;
        private int controlflag;
        private int visibility;
        private int inputtype;
        private int islist;
        public void setFieldcode(String fieldcode) {
            this.fieldcode = fieldcode;
        }
        public String getFieldcode() {
            return fieldcode;
        }

        public void setFieldshowname(String fieldshowname) {
            this.fieldshowname = fieldshowname;
        }
        public String getFieldshowname() {
            return fieldshowname;
        }

        public void setControlflag(int controlflag) {
            this.controlflag = controlflag;
        }
        public int getControlflag() {
            return controlflag;
        }

        public void setVisibility(int visibility) {
            this.visibility = visibility;
        }
        public int getVisibility() {
            return visibility;
        }

        public void setInputtype(int inputtype) {
            this.inputtype = inputtype;
        }
        public int getInputtype() {
            return inputtype;
        }

        public void setIslist(int islist) {
            this.islist = islist;
        }
        public int getIslist() {
            return islist;
        }


}
