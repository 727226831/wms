package com.lkrh.storescontrol.bean;

public class ProductionOrderBean {
    String ordertype;
    String orderclass;
    String depcode;

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderclass() {
        return orderclass;
    }

    public void setOrderclass(String orderclass) {
        this.orderclass = orderclass;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getInvcode() {
        return invcode;
    }

    public void setInvcode(String invcode) {
        this.invcode = invcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    String invcode;
    String quantity;
    String memo;
}
