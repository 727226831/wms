package com.lkrh.storescontrol.bean;

/**
 * Copyright 2018 bejson.com
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 */
public class ArrivalHeadBean implements Parcelable {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String ccode;
    private String ddate;
    private String cptcode;
    private String cptname;
    private String cvencode;
    private String cvenabbname;
    private String cvenname;
    private String cdepcode;
    private String cdepname;
    private String cmemo;
    private String cmaker;
    private String cmaketime;
    private String caudittime;
    private String cverifier;
    private String cInvCCode;
    private String cInvCName;
    private String cInvCode="";
    private String cInvAddCode;
    private String cInvName;
    private String cInvStd;
    private String cComUnitCode;
    private String cComUnitName;
    private String iChangRate;
    private  String stamp;
    private  String irowno;
    private String file;
    private  String cwhcode;
    private String transport="";




    public String getTray() {
        return tray;
    }

    public void setTray(String tray) {
        this.tray = tray;
    }

    private String tray;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String memo="";

    public String getInwhcode() {
        return inwhcode;
    }

    public void setInwhcode(String inwhcode) {
        this.inwhcode = inwhcode;
    }

    private  String inwhcode;
    private  String cposition;
    private  String material;
    //批号
    private  String cbatch;
    private  String iquantity;
    private String barcode;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    private String plate;
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    private String customer;


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCpocode() {
        return cpocode;
    }

    public void setCpocode(String cpocode) {
        this.cpocode = cpocode;
    }

    private  String cpocode;

    public String getCdefine10() {
        return cdefine10;
    }

    public void setCdefine10(String cdefine10) {
        this.cdefine10 = cdefine10;
    }

    private  String cdefine10;

    public String getCvenbatch() {
        return cvenbatch;
    }

    public void setCvenbatch(String cvenbatch) {
        this.cvenbatch = cvenbatch;
    }

    private  String cvenbatch;

    public String getInposition() {
        return inposition;
    }

    public void setInposition(String inposition) {
        this.inposition = inposition;
    }

    public static Creator<ArrivalHeadBean> getCREATOR() {
        return CREATOR;
    }

    private  String inposition;

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    private  String imageid;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }



    public String getCwhcode() {
        return cwhcode;
    }

    public void setCwhcode(String cwhcode) {
        this.cwhcode = cwhcode;
    }




    public String getIrowno() {
        return irowno;
    }

    public void setIrowno(String irowno) {
        this.irowno = irowno;
    }



    public String getCposition() {
        return cposition;
    }

    public void setCposition(String cposition) {
        this.cposition = cposition;
    }



    public String getCbatch() {
        return cbatch;
    }

    public void setCbatch(String cbatch) {
        this.cbatch = cbatch;
    }



    public  String getIquantity() {
        return iquantity;
    }

    public void setIquantity(String iquantity) {
        this.iquantity = iquantity;
    }



    public String getcInvCCode() {
        return cInvCCode;
    }

    public void setcInvCCode(String cInvCCode) {
        this.cInvCCode = cInvCCode;
    }

    public String getcInvCName() {
        return cInvCName;
    }

    public void setcInvCName(String cInvCName) {
        this.cInvCName = cInvCName;
    }

    public String getcInvCode() {
        return cInvCode;
    }

    public void setcInvCode(String cInvCode) {
        this.cInvCode = cInvCode;
    }

    public String getcInvAddCode() {
        return cInvAddCode;
    }

    public void setcInvAddCode(String cInvAddCode) {
        this.cInvAddCode = cInvAddCode;
    }

    public String getcInvName() {
        return cInvName;
    }

    public void setcInvName(String cInvName) {
        this.cInvName = cInvName;
    }

    public String getcInvStd() {
        return cInvStd;
    }

    public void setcInvStd(String cInvStd) {
        this.cInvStd = cInvStd;
    }

    public String getcComUnitCode() {
        return cComUnitCode;
    }

    public void setcComUnitCode(String cComUnitCode) {
        this.cComUnitCode = cComUnitCode;
    }

    public String getcComUnitName() {
        return cComUnitName;
    }

    public void setcComUnitName(String cComUnitName) {
        this.cComUnitName = cComUnitName;
    }

    public String getiChangRate() {
        return iChangRate;
    }

    public void setiChangRate(String iChangRate) {
        this.iChangRate = iChangRate;
    }



    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }






    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }


    public String getCmaketime() {
        return cmaketime;
    }

    public void setCmaketime(String cmaketime) {
        this.cmaketime = cmaketime;
    }

    public void setCaudittime(String caudittime) {
        this.caudittime = caudittime;
    }

    public String getCaudittime() {
        return caudittime;
    }


    public void setCInvCCode(String cInvCCode) {
        this.cInvCCode = cInvCCode;
    }
    public String getCInvCCode() {
        return cInvCCode;
    }

    public void setCInvCName(String cInvCName) {
        this.cInvCName = cInvCName;
    }
    public String getCInvCName() {
        return cInvCName;
    }

    public void setCInvCode(String cInvCode) {
        this.cInvCode = cInvCode;
    }
    public String getCInvCode() {
        return cInvCode;
    }

    public void setCInvAddCode(String cInvAddCode) {
        this.cInvAddCode = cInvAddCode;
    }
    public String getCInvAddCode() {
        return cInvAddCode;
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

    public void setCComUnitCode(String cComUnitCode) {
        this.cComUnitCode = cComUnitCode;
    }
    public String getCComUnitCode() {
        return cComUnitCode;
    }

    public void setCComUnitName(String cComUnitName) {
        this.cComUnitName = cComUnitName;
    }
    public String getCComUnitName() {
        return cComUnitName;
    }

    public void setIChangRate(String iChangRate) {
        this.iChangRate = iChangRate;
    }
    public String getIChangRate() {
        return iChangRate;
    }


    public void setCcode(String ccode) {
        this.ccode = ccode;
    }
    public String getCcode() {
        return ccode;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }
    public String getDdate() {
        return ddate;
    }

    public void setCptcode(String cptcode) {
        this.cptcode = cptcode;
    }
    public String getCptcode() {
        return cptcode;
    }

    public void setCptname(String cptname) {
        this.cptname = cptname;
    }
    public String getCptname() {
        return cptname;
    }

    public void setCvencode(String cvencode) {
        this.cvencode = cvencode;
    }
    public String getCvencode() {
        return cvencode;
    }

    public void setCvenabbname(String cvenabbname) {
        this.cvenabbname = cvenabbname;
    }
    public String getCvenabbname() {
        return cvenabbname;
    }

    public void setCvenname(String cvenname) {
        this.cvenname = cvenname;
    }
    public String getCvenname() {
        return cvenname;
    }

    public void setCdepcode(String cdepcode) {
        this.cdepcode = cdepcode;
    }
    public String getCdepcode() {
        return cdepcode;
    }

    public void setCdepname(String cdepname) {
        this.cdepname = cdepname;
    }
    public String getCdepname() {
        return cdepname;
    }

    public void setCmemo(String cmemo) {
        this.cmemo = cmemo;
    }
    public String getCmemo() {
        return cmemo;
    }

    public void setCmaker(String cmaker) {
        this.cmaker = cmaker;
    }
    public String getCmaker() {
        return cmaker;
    }



    public void setCverifier(String cverifier) {
        this.cverifier = cverifier;
    }
    public String getCverifier() {
        return cverifier;
    }


    public ArrivalHeadBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ccode);
        dest.writeString(this.ddate);
        dest.writeString(this.cptcode);
        dest.writeString(this.cptname);
        dest.writeString(this.cvencode);
        dest.writeString(this.cvenabbname);
        dest.writeString(this.cvenname);
        dest.writeString(this.cdepcode);
        dest.writeString(this.cdepname);
        dest.writeString(this.cmemo);
        dest.writeString(this.cmaker);
        dest.writeString(this.cmaketime);
        dest.writeString(this.caudittime);
        dest.writeString(this.cverifier);
        dest.writeString(this.cInvCCode);
        dest.writeString(this.cInvCName);
        dest.writeString(this.cInvCode);
        dest.writeString(this.cInvAddCode);
        dest.writeString(this.cInvName);
        dest.writeString(this.cInvStd);
        dest.writeString(this.cComUnitCode);
        dest.writeString(this.cComUnitName);
        dest.writeString(this.iChangRate);
        dest.writeString(this.stamp);
        dest.writeString(this.irowno);
        dest.writeString(this.file);
        dest.writeString(this.cwhcode);
        dest.writeString(this.transport);

        dest.writeString(this.tray);
        dest.writeString(this.category);
        dest.writeString(this.memo);
        dest.writeString(this.inwhcode);
        dest.writeString(this.cposition);
        dest.writeString(this.material);
        dest.writeString(this.cbatch);
        dest.writeString(this.iquantity);
        dest.writeString(this.barcode);
        dest.writeString(this.plate);
        dest.writeString(this.customer);
        dest.writeString(this.cpocode);
        dest.writeString(this.cdefine10);
        dest.writeString(this.cvenbatch);
        dest.writeString(this.inposition);
        dest.writeString(this.imageid);
    }

    protected ArrivalHeadBean(Parcel in) {
        this.id = in.readString();
        this.ccode = in.readString();
        this.ddate = in.readString();
        this.cptcode = in.readString();
        this.cptname = in.readString();
        this.cvencode = in.readString();
        this.cvenabbname = in.readString();
        this.cvenname = in.readString();
        this.cdepcode = in.readString();
        this.cdepname = in.readString();
        this.cmemo = in.readString();
        this.cmaker = in.readString();
        this.cmaketime = in.readString();
        this.caudittime = in.readString();
        this.cverifier = in.readString();
        this.cInvCCode = in.readString();
        this.cInvCName = in.readString();
        this.cInvCode = in.readString();
        this.cInvAddCode = in.readString();
        this.cInvName = in.readString();
        this.cInvStd = in.readString();
        this.cComUnitCode = in.readString();
        this.cComUnitName = in.readString();
        this.iChangRate = in.readString();
        this.stamp = in.readString();
        this.irowno = in.readString();
        this.file = in.readString();
        this.cwhcode = in.readString();
        this.transport = in.readString();

        this.tray = in.readString();
        this.category = in.readString();
        this.memo = in.readString();
        this.inwhcode = in.readString();
        this.cposition = in.readString();
        this.material = in.readString();
        this.cbatch = in.readString();
        this.iquantity = in.readString();
        this.barcode = in.readString();
        this.plate = in.readString();
        this.customer = in.readString();
        this.cpocode = in.readString();
        this.cdefine10 = in.readString();
        this.cvenbatch = in.readString();
        this.inposition = in.readString();
        this.imageid = in.readString();
    }

    public static final Creator<ArrivalHeadBean> CREATOR = new Creator<ArrivalHeadBean>() {
        @Override
        public ArrivalHeadBean createFromParcel(Parcel source) {
            return new ArrivalHeadBean(source);
        }

        @Override
        public ArrivalHeadBean[] newArray(int size) {
            return new ArrivalHeadBean[size];
        }
    };
}
