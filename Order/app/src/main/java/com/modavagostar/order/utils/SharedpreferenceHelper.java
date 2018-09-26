package com.modavagostar.order.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.modavagostar.order.MainActivity;
import com.modavagostar.order.MaterialDesignLogInForm;
import com.modavagostar.order.model.User;

import java.util.HashMap;


public class SharedpreferenceHelper {


    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;


    private static final String PREF_NAME = "Order";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_NATIONALCODEE = "nationalCode";
    public static final String KEY_FIRSNAME="name";
    public static final String KEY_PASSWORD="pasword";
    public static final String KEY_PHONE="phone";
    public static final String KEY_ADDRESS="address";
    public static final String main="";
    public static final String cartitemid="cart";

    public static final String token="token";



    public SharedpreferenceHelper(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(User user){

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_FIRSNAME,user.getName());
        editor.putString(KEY_NATIONALCODEE,user.getNationalCode());
        editor.putString(KEY_PASSWORD,user.getPassword());
        editor.putString(KEY_PHONE,user.getPhone());
        editor.putString(KEY_ADDRESS,user.getAddress());
        editor.putLong(KEY_ID,user.getId());


        // commit changes
        editor.commit();
    }

    public void main(String m){

        // Storing login value as TRUE
        editor.putString(main,m);



        // commit changes
        editor.commit();
    }



    public void token(String m){

        // Storing login value as TRUE
        editor.putString(token,m);



        // commit changes
        editor.commit();
    }






    public void cartitemid(int m){

        // Storing login value as TRUE
        editor.putInt(cartitemid,m);



        // commit changes
        editor.commit();
    }

    public void createLoginSession(String id) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.commit();
    }





    public HashMap<String, String> getUserDetails(){

        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NATIONALCODEE, pref.getString(KEY_NATIONALCODEE, null));


        // return user
        return user;
    }

    public long getStoredId() {

        return pref.getLong(KEY_ID,0);
    }

    public String getStoredname() {

        return pref.getString(KEY_FIRSNAME,null);
    }

    public String getStorednational() {

        return pref.getString(KEY_NATIONALCODEE,null);
    }
    public String getStoredpass() {

        return pref.getString(KEY_PASSWORD,null);
    }

    public String getStoredphone() {

        return pref.getString(KEY_PHONE,null);
    }






    public String getmain() {

        return pref.getString(main,null);
    }


    public String gettoken() {

        return pref.getString(token,null);
    }


    public int getcart() {

        return pref.getInt(cartitemid,0);
    }




    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MaterialDesignLogInForm.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}






