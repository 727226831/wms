package com.lkrh.storescontrol.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LoginBean implements Parcelable {

        private String Resultcode;
        private String ResultMessage;
        private String VersionNumber;
        private String usercode;
        private String username;
        private String acccode;
        private String accname;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private String company;
        private List<Menu> menu;
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

        public void setVersionNumber(String VersionNumber) {
            this.VersionNumber = VersionNumber;
        }
        public String getVersionNumber() {
            return VersionNumber;
        }

        public void setUsercode(String usercode) {
            this.usercode = usercode;
        }
        public String getUsercode() {
            return usercode;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }

        public void setAcccode(String acccode) {
            this.acccode = acccode;
        }
        public String getAcccode() {
            return acccode;
        }

        public void setAccname(String accname) {
            this.accname = accname;
        }
        public String getAccname() {
            return accname;
        }

        public void setMenu(List<Menu> menu) {
            this.menu = menu;
        }
        public List<Menu> getMenu() {
            return menu;
        }
    public static class Menu implements Parcelable {

        private String menucode;
        private String menushowname;
        private String menutype;
        private String formcount;
        public String getFormcount() {
            return formcount;
        }

        public void setFormcount(String formcount) {
            this.formcount = formcount;
        }


        public void setMenucode(String menucode) {
            this.menucode = menucode;
        }
        public String getMenucode() {
            return menucode;
        }

        public void setMenushowname(String menushowname) {
            this.menushowname = menushowname;
        }
        public String getMenushowname() {
            return menushowname;
        }

        public void setMenutype(String menutype) {
            this.menutype = menutype;
        }
        public String getMenutype() {
            return menutype;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.menucode);
            dest.writeString(this.menushowname);
            dest.writeString(this.menutype);
            dest.writeString(this.formcount);
        }

        public Menu() {
        }

        protected Menu(Parcel in) {
            this.menucode = in.readString();
            this.menushowname = in.readString();
            this.menutype = in.readString();
            this.formcount = in.readString();
        }

        public static final Parcelable.Creator<Menu> CREATOR = new Parcelable.Creator<Menu>() {
            @Override
            public Menu createFromParcel(Parcel source) {
                return new Menu(source);
            }

            @Override
            public Menu[] newArray(int size) {
                return new Menu[size];
            }
        };
    }

    public LoginBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Resultcode);
        dest.writeString(this.ResultMessage);
        dest.writeString(this.VersionNumber);
        dest.writeString(this.usercode);
        dest.writeString(this.username);
        dest.writeString(this.acccode);
        dest.writeString(this.accname);
        dest.writeString(this.company);
        dest.writeTypedList(this.menu);
    }

    protected LoginBean(Parcel in) {
        this.Resultcode = in.readString();
        this.ResultMessage = in.readString();
        this.VersionNumber = in.readString();
        this.usercode = in.readString();
        this.username = in.readString();
        this.acccode = in.readString();
        this.accname = in.readString();
        this.company = in.readString();
        this.menu = in.createTypedArrayList(Menu.CREATOR);
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };
}




