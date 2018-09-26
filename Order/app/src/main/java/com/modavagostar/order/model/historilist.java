package com.modavagostar.order.model;

import java.math.BigDecimal;

/**
 * Created by Xaniar on 3/16/2018.
 */

public class historilist {
    private long orderDate;
    private boolean orderSubmit ;


    private BigDecimal orderTotal;

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }



    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isOrderSubmit() {
        return orderSubmit;
    }

    public void setOrderSubmit(boolean orderSubmit) {
        this.orderSubmit = orderSubmit;
    }


}
