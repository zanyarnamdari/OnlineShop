package com.modavagostar.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by HP on 2018/03/10.
 */

public class Brand implements Serializable {

    private int id;

    private String title;

    private long insertDate;

    private boolean logicalDelete;

    private long imageId;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
    }

    public boolean isLogicalDelete() {
        return logicalDelete;
    }

    public void setLogicalDelete(boolean logicalDelete) {
        this.logicalDelete = logicalDelete;
    }
}
