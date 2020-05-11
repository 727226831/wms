package com.lkrh.storescontrol.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DispatchdetailsBean implements Parcelable {


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

    public static class Data implements Parcelable {

        public int getDlid() {
            return dlid;
        }

        public void setDlid(int dlid) {
            this.dlid = dlid;
        }

        private int dlid;
        private String cinvcode;
        private String cinvname;
        private String cinvstd;
        private String cbatch;
        private String iquantity;
        private String irowno;
        private int bWhPos;
        private String completed;
        private String incomplete;

        public String getCposition() {
            return cposition;
        }

        public void setCposition(String cposition) {
            this.cposition = cposition;
        }

        private String cposition;

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


        public void setCinvcode(String cinvcode) {
            this.cinvcode = cinvcode;
        }

        public String getCinvcode() {
            return cinvcode;
        }

        public void setCinvname(String cinvname) {
            this.cinvname = cinvname;
        }

        public String getCinvname() {
            return cinvname;
        }

        public void setCinvstd(String cinvstd) {
            this.cinvstd = cinvstd;
        }

        public String getCinvstd() {
            return cinvstd;
        }

        public void setCbatch(String cbatch) {
            this.cbatch = cbatch;
        }

        public String getCbatch() {
            return cbatch;
        }

        public void setIquantity(String iquantity) {
            this.iquantity = iquantity;
        }

        public String getIquantity() {
            return iquantity;
        }

        public void setIrowno(String irowno) {
            this.irowno = irowno;
        }

        public String getIrowno() {
            return irowno;
        }

        public void setBWhPos(int bWhPos) {
            this.bWhPos = bWhPos;
        }

        public int getBWhPos() {
            return bWhPos;
        }

        public Data() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.dlid);
            dest.writeString(this.cinvcode);
            dest.writeString(this.cinvname);
            dest.writeString(this.cinvstd);
            dest.writeString(this.cbatch);
            dest.writeString(this.iquantity);
            dest.writeString(this.irowno);
            dest.writeInt(this.bWhPos);
            dest.writeString(this.completed);
            dest.writeString(this.incomplete);
            dest.writeString(this.cposition);
        }

        protected Data(Parcel in) {
            this.dlid = in.readInt();
            this.cinvcode = in.readString();
            this.cinvname = in.readString();
            this.cinvstd = in.readString();
            this.cbatch = in.readString();
            this.iquantity = in.readString();
            this.irowno = in.readString();
            this.bWhPos = in.readInt();
            this.completed = in.readString();
            this.incomplete = in.readString();
            this.cposition = in.readString();
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

    public DispatchdetailsBean() {
    }

    protected DispatchdetailsBean(Parcel in) {
        this.Resultcode = in.readString();
        this.ResultMessage = in.readString();
        this.data = in.createTypedArrayList(Data.CREATOR);
    }

    public static final Creator<DispatchdetailsBean> CREATOR = new Creator<DispatchdetailsBean>() {
        @Override
        public DispatchdetailsBean createFromParcel(Parcel source) {
            return new DispatchdetailsBean(source);
        }

        @Override
        public DispatchdetailsBean[] newArray(int size) {
            return new DispatchdetailsBean[size];
        }
    };
}
