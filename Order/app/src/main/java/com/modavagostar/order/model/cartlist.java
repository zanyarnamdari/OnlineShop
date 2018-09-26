package com.modavagostar.order.model;

/**
 * Created by Xaniar on 3/16/2018.
 */

public class cartlist {
    private int productId;

    private String title;

    private int price;

    public int getInBoxNumber() {
        return inBoxNumber;
    }

    public void setInBoxNumber(int inBoxNumber) {
        this.inBoxNumber = inBoxNumber;
    }

    private String description;

    private int qty;
    private long imageId;
    private int inBoxNumber;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
