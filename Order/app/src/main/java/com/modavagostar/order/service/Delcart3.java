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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.modavagostar.order.ProductActivity;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.model.cartfordel;
import com.modavagostar.order.searchproduct;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Xaniar on 3/15/2018.
 */

public class Delcart3 {
    int nana = 0;
    searchproduct context;
    RecyclerView recyclerView;
    SharedpreferenceHelper share;

    public Delcart3(searchproduct context) {
        this.context = context;
        this.recyclerView = recyclerView;
    }


    public void delcart1(int m) throws JSONException, UnsupportedEncodingException {
        share = new SharedpreferenceHelper(context);
        nana = m;
        final String urlLogin = Services.cartitemlistforremove + share.getStoredId();
        final int reqType = Request.Method.GET;

        JSONArray obj = null;


        JsonArrayRequest jsonObjReq = null;


        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                urlLogin, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {


                        if (response != null) {


                            parseJson(response);
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

        )

        {
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


    public void parseJson(JSONArray response) {


        List<cartfordel> cart = new ArrayList<>();


        for (int i = 0; i < response.length(); i++) {

            try {

                JSONObject c = (JSONObject) response.get(Integer.parseInt(String.valueOf(i)));

                JSONObject za = c.getJSONObject("product");


                int cartid = za.getInt("productId");

                if (cartid == nana) {

                    int zaza = c.getInt("id");
//                    Toast.makeText(context,String.valueOf(zaza)
//                            , Toast.LENGTH_SHORT).show();

                    try {
                        sendtodelet1(zaza);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }


            } catch (JSONException e) {
                Log.e("error", e.toString());
                e.printStackTrace();

            }

        }


    }

    public void sendtodelet1(int idcart) throws JSONException, UnsupportedEncodingException {

        final String urlLogin = Services.deletcart2;
        final int reqType = Request.Method.POST;


        JSONObject jsonObject_one = new JSONObject();

        try {

            jsonObject_one.put("cartItemId", String.valueOf(idcart));


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

            Snackbar.make(context.findViewById(android.R.id.content), "کالای مورد نظر حذف شد", Snackbar.LENGTH_SHORT).show();


        }


    }
}