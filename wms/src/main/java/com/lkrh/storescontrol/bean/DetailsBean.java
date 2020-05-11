package com.lkrh.storescontrol.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DetailsBean implements Parcelable {


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



    public static class Data implements Parcelable {

        private String cposition;
        private String cInvCode;
        private String cInvName;
        private String cInvStd;
        private String cBatch;
        private String iQuantity;

        public String getCompleted() {
            return completed;
        }

        public void setCompleted(String completed) {
            this.completed = completed;
        }

        public String getIncomplete() {
            return incomplete;
        }

        public void setIncomplete(String incomplete) {
            this.incomplete = incomplete;
        }

        private String completed;
        private String incomplete;
        public void setCposition(String cposition) {
            this.cposition = cposition;
        }
        public String getCposition() {
            return cposition;
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

        public void setCInvStd(String cInvStd) {
            this.cInvStd = cInvStd;
        }
        public String getCInvStd() {
            return cInvStd;
        }

        public void setCBatch(String cBatch) {
            this.cBatch = cBatch;
        }
        public String getCBatch() {
            return cBatch;
        }

        public void setIQuantity(String iQuantity) {
            this.iQuantity = iQuantity;
        }
        public String getIQuantity() {
            return iQuantity;
        }

        public Data() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cposition);
            dest.writeString(this.cInvCode);
            dest.writeString(this.cInvName);
            dest.writeString(this.cInvStd);
            dest.writeString(this.cBatch);
            dest.writeString(this.iQuantity);
            dest.writeString(this.completed);
            dest.writeString(this.incomplete);
        }

        protected Data(Parcel in) {
            this.cposition = in.readString();
            this.cInvCode = in.readString();
            this.cInvName = in.readString();
            this.cInvStd = in.readString();
            this.cBatch = in.readString();
            this.iQuantity = in.readString();
            this.completed = in.readString();
            this.incomplete = in.readString();
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel source) {
                return new Data(source);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Resultcode);
        dest.writeString(this.ResultMessage);
        dest.writeTypedList(this.data);
    }

    public DetailsBean() {
    }

    protected DetailsBean(Parcel in) {
        this.Resultcode = in.readString();
        this.ResultMessage = in.readString();
        this.data = in.createTypedArrayList(Data.CREATOR);
    }

    public static final Parcelable.Creator<DetailsBean> CREATOR = new Parcelable.Creator<DetailsBean>() {
        @Override
        public DetailsBean createFromParcel(Parcel source) {
            return new DetailsBean(source);
        }

        @Override
        public DetailsBean[] newArray(int size) {
            return new DetailsBean[size];
        }
    };
}
