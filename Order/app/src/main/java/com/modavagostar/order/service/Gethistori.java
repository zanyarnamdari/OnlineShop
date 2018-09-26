package com.modavagostar.order.service;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.modavagostar.order.R;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.cartitem;
import com.modavagostar.order.histori_activity;
import com.modavagostar.order.model.Brand;
import com.modavagostar.order.model.CartRecyclerAdapter;
import com.modavagostar.order.model.cartlist;
import com.modavagostar.order.model.hisRecyclerAdapter;
import com.modavagostar.order.model.historilist;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.modavagostar.order.model.ProductRecyclerAdapter;

/**
 * Created by Xaniar on 3/16/2018.
 */

public class Gethistori {
    histori_activity context;
    RecyclerView recyclerView;
    private AVLoadingIndicatorView progressBar;
    SharedpreferenceHelper share;

    public Gethistori(histori_activity context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }


    public void getAllcarts(long m) throws JSONException, UnsupportedEncodingException {
        progressBar = (AVLoadingIndicatorView) context.findViewById(R.id.z);
        progressBar.show();
        share = new SharedpreferenceHelper(context);

        String url = Services.histori + m;
        JSONArray obj = null;


        JsonArrayRequest jsonObjReq = null;


        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {


                        progressBar.hide();


                        if (response != null) {


                            try {
                                parseJson(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (response == null) {


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
        }

        ) {


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
                8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//         Log.e("Body",new String(jsonObjReq.getBody(), "UTF-8"));
        String tag_json_arry = "json_array_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_arry);

    }


    public void parseJson(JSONArray response) throws JSONException {


        List<historilist> productList = new ArrayList<>();

        for (int i = 0; i <= response.length(); i++) {
            try {


                JSONObject c = (JSONObject) response.get(i);

                long date = c.getLong("orderDate");

                Boolean sub = c.getBoolean("orderSubmit");
                BigDecimal orderTotal = BigDecimal.valueOf(c.getLong("orderTotal"));
                historilist product = new historilist();
                product.setOrderTotal(orderTotal);

                product.setOrderSubmit(sub);
                product.setOrderDate(date);

                productList.add(i, product);

            } catch (JSONException e) {
                Log.e("error", e.toString());
                e.printStackTrace();

            }
        }

        hisRecyclerAdapter cartRecyclerAdapter = new hisRecyclerAdapter(context, productList, recyclerView);
        recyclerView.setAdapter(cartRecyclerAdapter);
        cartRecyclerAdapter.notifyDataSetChanged();


    }


}
