package com.modavagostar.order.model;

/**
 * Created by HP on 2018/03/11.
 */

public class Product {

    private int productId;

    private String title;

    private int price;

    private String description;

//    private int inStockNumber;

    private long imageId;


    private int inBoxNumber;
    private int sellPrice;


    public int getInBoxNumber() {
        return inBoxNumber;
    }

    public void setInBoxNumber(int inBoxNumber) {
        this.inBoxNumber = inBoxNumber;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
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

//    public int getInStockNumber() {
//        return inStockNumber;
//    }
//
//    public void setInStockNumber(int inStockNumber) {
//        this.inStockNumber = inStockNumber;
//    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
