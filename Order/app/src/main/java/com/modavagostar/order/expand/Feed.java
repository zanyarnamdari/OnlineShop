package com.modavagostar.order.expand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Feed {

    @SerializedName("mainCategoryTitle")
    @Expose
    private String heading;
    @SerializedName("mainCategoryId")
    @Expose
    private int mainid;
    @SerializedName("subCategoryList")
    @Expose
    private List<Info> infoList;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }


    public int getMainid() {
        return mainid;
    }

    public void setMainid(int mainid) {
        this.mainid = mainid;
    }
}
