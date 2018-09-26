package com.modavagostar.order.model;

/**
 * Created by HP on 2018/02/26.
 */

public class User {

    private long id;

    //private String username;
    private String nationalCode;
    private String password;
    private String Name;

    private String address;
    private String phone;
    private Long insertDate;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private boolean logicalDelete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Long insertDate) {
        this.insertDate = insertDate;
    }

    public boolean isLogicalDelete() {
        return logicalDelete;
    }

    public void setLogicalDelete(boolean logicalDelete) {
        this.logicalDelete = logicalDelete;
    }
}
