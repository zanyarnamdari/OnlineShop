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
import com.android.volley.toolbox.RequestFuture;
import com.modavagostar.order.R;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.cartitem;
import com.modavagostar.order.model.CartRecyclerAdapter;
//import com.modavagostar.order.model.ProductRecyclerAdapter;
import com.modavagostar.order.model.cartlist;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Xaniar on 3/16/2018.
 */

public class GetCart {
    cartitem context;
    RecyclerView recyclerView;
    private AVLoadingIndicatorView progressBar;
    int zan = 0;
    SharedpreferenceHelper share;

    public GetCart(cartitem context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }


    public void getAllcarts(long m) throws JSONException, UnsupportedEncodingException {
        progressBar = (AVLoadingIndicatorView) context.findViewById(R.id.z);
        progressBar.show();

        String url = Services.cart + m;
        JSONArray obj = null;
        share = new SharedpreferenceHelper(context);


        JsonArrayRequest jsonObjReq = null;


        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {


                        progressBar.hide();


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


    public void parseJson(JSONArray response) {


        List<cartlist> productList = new ArrayList<>();


        for (int i = 0; i <= response.length(); i++) {

            try {


                JSONObject c = (JSONObject) response.get(Integer.parseInt(String.valueOf(i)));
                int qty = c.getInt("qty");

                JSONObject ccc = (JSONObject) c.get("product");
                String title = ccc.getString("title");


                String des = ccc.getString("description");
                int price = ccc.getInt("price");
                int ted = ccc.getInt("inBoxNumber");


                int id = ccc.getInt("productId");

                /*String imagePreview = c.getString("imagePreview");*/
                JSONArray zaza = (JSONArray) ccc.get("productImages");
                JSONObject kn = (JSONObject) zaza.get(0);
                long imgff = kn.getLong("imageId");

                cartlist product = new cartlist();
                product.setTitle(title);
                product.setDescription(des);
                product.setPrice(price);
                product.setInBoxNumber(ted);
                product.setQty(qty);
                product.setImageId(imgff);
                product.setProductId(id);


                productList.add(i, product);


            } catch (JSONException e) {
                Log.e("error", e.toString());
                e.printStackTrace();

            }

        }
//

        CartRecyclerAdapter cartRecyclerAdapter = new CartRecyclerAdapter(context, productList, recyclerView);
        recyclerView.setAdapter(cartRecyclerAdapter);
        cartRecyclerAdapter.notifyDataSetChanged();


    }


}
