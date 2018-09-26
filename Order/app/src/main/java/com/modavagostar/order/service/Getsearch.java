//package com.modavagostar.order.service;
//
//import android.annotation.SuppressLint;
//import android.content.BroadcastReceiver;
//import android.content.ComponentName;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.IntentSender;
//import android.content.ServiceConnection;
//import android.content.SharedPreferences;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.content.res.AssetManager;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.database.DatabaseErrorHandler;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.UserHandle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.Snackbar;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.Display;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkError;
//import com.android.volley.NetworkResponse;
//import com.android.volley.NoConnectionError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.ServerError;
//import com.android.volley.TimeoutError;
//import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.RequestFuture;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import com.modavagostar.order.ProductActivity;
//import com.modavagostar.order.R;
//import com.modavagostar.order.app.AppController;
//import com.modavagostar.order.model.Product;
////import com.modavagostar.order.model.ProductRecyclerAdapter;
//import com.modavagostar.order.searchproduct;
//import com.modavagostar.order.utils.Services;
//import com.wang.avi.AVLoadingIndicatorView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Xaniar on 3/17/2018.
// */
//
//public class Getsearch {
//    searchproduct context;
//    RecyclerView recyclerView;
//
//    public Getsearch(searchproduct context, RecyclerView recyclerView) {
//        this.context = context;
//        this.recyclerView = recyclerView;
//    }
//
//
//    public void getAllproducts(String m) throws JSONException, UnsupportedEncodingException {
//
//
//
//
//        final String urlLogin = Services.search;
//        final int reqType = Request.Method.POST;
//
//
//
//
//        JSONObject jsonObject_one = new JSONObject();
//
//        try {
//            jsonObject_one.put("keyword",m );
//
//
//
//
//
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//
//        }
//
//        JsonObjectRequest jsonObjReq=null;
//
//        RequestFuture<JSONObject> future = RequestFuture.newFuture();
//
//        jsonObjReq = new JsonObjectRequest(reqType,
//                urlLogin,jsonObject_one,
//
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        if (response!=null) {
//
//
//
//
//                                parseJson2(response);
//
//
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//
//
//                if ((error instanceof NetworkError) || (error instanceof NoConnectionError) ) {
//                    Snackbar.make(context.findViewById(android.R.id.content),"ارتباط با شبکه قطع می باشد",Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (error instanceof TimeoutError){
//
//                    Snackbar.make(context.findViewById(android.R.id.content),"TimeoutError",Snackbar.LENGTH_SHORT).show();
//
//                    return;
//                }
//
//                if ((error instanceof ServerError) || (error instanceof AuthFailureError)){
//
//                    Snackbar.make(context.findViewById(android.R.id.content),"ارتباط با سرور  قطع می باشد",Snackbar.LENGTH_SHORT).show();
//
//                    return;
//                }
//
//            }
//        })
//        {
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//        };
//
//
//
//        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
//                20000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
////        Log.e("Body",new String(jsonObjReq.getBody(), "UTF-8"));
//        String tag_json_arry = "json_array_req";
//// Adding request to request queue
//        // mRequestQueue.add(jsonObjReq);
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_arry);}
//
//    private void parseJson2(JSONObject response) {
//
//
//
//
//
//    }
//
//
//}