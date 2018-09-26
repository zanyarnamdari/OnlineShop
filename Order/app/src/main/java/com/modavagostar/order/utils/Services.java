package com.modavagostar.order.utils;


import java.util.ArrayList;

public class Services
{

//66.70.134.119

    public static String LOGIN = "http://185.172.68.32:8096/v1/mobileUser/loginMobileUser";
    public static String MAINCATEGORY = "http://185.172.68.32:8096/v1/mainCategory/getAllMainCategoryWithMobileUserId/";
    public static String getbrand = "http://185.172.68.32:8096/v1/brand/getAllBrand/";
    public static String IMAGEPREVIEWURL = "http://185.172.68.32:8096/v1/product/returnProductImage/";

    public static String slider = "http://185.172.68.32:8096/v1/product/getAllProductSlider";


    public static String returnpro = "http://185.172.68.32:8096/v1/product/getProduct/";




    public static String cart = "http://185.172.68.32:8096/v1/shoppingCart/getCartItemList/";

public static String addcart1 = "http://185.172.68.32:8096/v1/shoppingCart/addCartItem";
  public static String cartitemlistforremove = "http://185.172.68.32:8096/v1/shoppingCart/getCartItemList/";

    public static String deletcart2 = "http://185.172.68.32:8096/v1/shoppingCart/removeItem";
    public static String kharid = "http://185.172.68.32:8096/v1/checkout";

    public static String pass = "http://185.172.68.32:8096/v1/mobileUser/newPassword";
    public static String brandimage = "http://185.172.68.32:8096/v1/brand/returnBrandImage/";
    public static String histori = "http://185.172.68.32:8096/v1/getOrderList/";


//



    public static ArrayList<Long> sliderimage =  new ArrayList<Long>();
    public static ArrayList<String> pro =  new ArrayList<String>();

}
