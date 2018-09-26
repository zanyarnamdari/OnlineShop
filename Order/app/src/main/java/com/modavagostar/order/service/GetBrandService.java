package com.modavagostar.order.service;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.modavagostar.order.BrandActivity;
import com.modavagostar.order.R;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.model.Brand;
import com.modavagostar.order.model.BrandRecyclerAdapter;
import com.modavagostar.order.model.User;
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
 * Created by HP on 2018/03/10.
 */

public class GetBrandService {
    BrandActivity context;
    RecyclerView recyclerView;
    SharedpreferenceHelper share;

    //    private  ProgressDialog progressBar;
    private AVLoadingIndicatorView progressBar;

    public GetBrandService(BrandActivity context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }


    public void getAllBrands(int m) throws JSONException, UnsupportedEncodingException {
        share = new SharedpreferenceHelper(context);

        String url = Services.getbrand + m;
        JSONArray obj = null;

        progressBar = (AVLoadingIndicatorView) context.findViewById(R.id.z);

        JsonArrayRequest jsonObjReq = null;

//        progressBar = new ProgressDialog(context,R.style.Custom);
//        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressBar.setMessage(" لطفا صبر کنید...");
        progressBar.show();

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


        List<Brand> brandlist = new ArrayList<>();


        for (int i = 0; i <= response.length(); i++) {

            try {


                JSONObject c = (JSONObject) response.get(Integer.parseInt(String.valueOf(i)));

                int userId = c.getInt("brandId");
                String title = c.getString("title");

                /*String imagePreview = c.getString("imagePreview");*/
                JSONArray xc = (JSONArray) c.get("brandImages");

                JSONObject kn = (JSONObject) xc.get(0);
                long imgff = kn.getLong("imageId");


                Brand brand = new Brand();
                brand.setId(userId);
                brand.setTitle(title);
                brand.setImageId(imgff);


                brandlist.add(i, brand);


            } catch (JSONException e) {
                Log.e("error", e.toString());
                e.printStackTrace();

            }

        }


        BrandRecyclerAdapter brandRecyclerAdapter = new BrandRecyclerAdapter(context, brandlist, recyclerView);
        recyclerView.setAdapter(brandRecyclerAdapter);
        brandRecyclerAdapter.notifyDataSetChanged();


    }

}
