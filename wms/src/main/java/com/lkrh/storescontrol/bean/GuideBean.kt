package com.lkrh.storescontrol.bean

data class GuideBean (var Resultcode:String, var ResultMessage:String, var data:List<Data>){
    data class Data(var cWhName:String,var cPosName:String,var iQuantity:String)
}
data class ScanCheckBean(var code:String,var chccked:Boolean){

}
