package com.modavagostar.order.service;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.modavagostar.order.ProductActivity;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.searchproduct;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

//import com.modavagostar.order.model.ProductRecyclerAdapter;

/**
 * Created by Xaniar on 3/15/2018.
 */

public class Addcartitem3 {
    searchproduct context;
    RecyclerView recyclerView;
    SharedpreferenceHelper share;

    public Addcartitem3(searchproduct context) {
        this.context = context;
        this.recyclerView = recyclerView;
    }


    public void addcart1(int m, String zaaz) throws JSONException, UnsupportedEncodingException {

        final String urlLogin = Services.addcart1;
        final int reqType = Request.Method.POST;

        share = new SharedpreferenceHelper(context);

        JSONObject jsonObject_one = new JSONObject();

        try {

            jsonObject_one.put("userId", String.valueOf(share.getStoredId()));
            jsonObject_one.put("productId", String.valueOf(m));
            jsonObject_one.put("qty", zaaz);


        } catch (JSONException e) {
            e.printStackTrace();

        }

        JsonObjectRequest jsonObjReq = null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        jsonObjReq = new JsonObjectRequest(reqType,
                urlLogin, jsonObject_one,

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {

                            try {

                                parseJson2(response);

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                if ((error instanceof NetworkError) || (error instanceof NoConnectionError)) {
                    Snackbar.make(context.findViewById(android.R.id.content), "ارتباط با شبکه قطع می باشد", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (error instanceof TimeoutError) {

                    Snackbar.make(context.findViewById(android.R.id.content), "TimeoutError", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if ((error instanceof ServerError) || (error instanceof AuthFailureError)) {

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(context);
                    sessionManager.logoutUser();
                    return;
                }

            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Android");
                headers.put("tokens", String.valueOf(share.gettoken()));

                return headers;
            }


            public String getBodyContentType() {
                return "application/json";
            }

        };


        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Log.e("Body",new String(jsonObjReq.getBody(), "UTF-8"));
        String tag_json_arry = "json_array_req";
// Adding request to request queue
        // mRequestQueue.add(jsonObjReq);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_arry);
    }

    public void parseJson2(JSONObject response) throws JSONException {

        boolean res = response.getBoolean("result");
        if (res) {

            int z = Integer.parseInt(response.getString("cartItemId"));
            share.cartitemid(z);

            Snackbar.make(context.findViewById(android.R.id.content), "کالای شما به سبد خرید اضافه شد", Snackbar.LENGTH_SHORT).show();

        }
        if (!res) {
            Snackbar.make(context.findViewById(android.R.id.content), "موجودی انبار ازتعداد درخواست شماکمتر میباشد.", Snackbar.LENGTH_SHORT).show();


        }


    }
}