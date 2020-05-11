package com.lkrh.storescontrol.bean

data class GuideBean (var Resultcode:String, var ResultMessage:String, var data:List<Data>){
    data class Data(var cWhName:String,val cPosName:String,var iQuantity:String)
}
data class FhckBean (var ccode:String, var cinvcode:String, var boxcode:String,var warehouse:String){

}