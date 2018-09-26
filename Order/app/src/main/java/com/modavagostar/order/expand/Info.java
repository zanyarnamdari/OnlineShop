package com.modavagostar.order.expand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by janisharali on 24/08/16.
 */
public class Info {

    @SerializedName("subCategoryTitle")
    @Expose
    private String title;
    @SerializedName("subCategoryId")
    @Expose
    private int subid;


    @SerializedName("logicalDelete")
    @Expose
    private Boolean logical;

    public Boolean getLogical() {
        return logical;
    }

    public void setLogical(Boolean logical) {
        this.logical = logical;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }
}
