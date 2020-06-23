package com.hrishikeshgawas.ekcutting;

public class shopModel {
    public String eName, eAddress, eDescription, eCost, eKeyId, eFavStatus, eLink, eDist;
    public int eVNv;



    public shopModel(String eName, String eAddress, String eDescription, String eCost, int eVNv, String eKeyId, String eFavStatus, String eLink, String eDist){
        this.eName = eName;
        this.eAddress = eAddress;
        this.eDescription = eDescription;
        this.eCost = eCost;
        this.eVNv = eVNv;
        this.eKeyId = eKeyId;
        this.eFavStatus = eFavStatus;
        this.eLink = eLink;
        this.eDist = eDist;
    }

    public String getEName(){
        return eName;
    }
    public void setEName(String eName){
        this.eName = eName;
    }
    public String getEAddress(){
        return eAddress;
    }
    public void setEAddress(String eAddress){
        this.eAddress = eAddress;
    }
    public String getEDescription(){
        return eDescription;
    }
    public void setEDescription(String eDescription){
        this.eDescription = eDescription;
    }
    public String getECost(){
        return eCost;
    }
    public void setECost(String eCost){
        this.eCost = eCost;
    }
    public int getEVNv(){
        return eVNv;
    }
    public void setEVNv(int eVNv){
        this.eVNv = eVNv;
    }
    public String getEFavStatus() {
        return eFavStatus;
    }
    public void setEFavStatus(String eFavStatus) {
        this.eFavStatus = eFavStatus;
    }
    public String getEKeyId() {
        return eKeyId;
    }
    public void setEKeyId(String eKeyId) {
        this.eKeyId = eKeyId;
    }
    public String getELink() {
        return eLink;
    }
    public void setELink(String eLink) {
        this.eLink = eLink;
    }
    public String getEDist() {
        return eDist;
    }
    public void setEDist(String eDist) {
        this.eDist = eDist;
    }



}
