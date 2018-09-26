package com.modavagostar.order;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
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
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.model.CartRecyclerAdapter;
import com.modavagostar.order.model.cartlist;
import com.modavagostar.order.service.GetBrandService;
import com.modavagostar.order.service.GetCart;
//import com.modavagostar.order.service.GetProductService;
import com.modavagostar.order.utils.AppStatus;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;
import com.travijuu.numberpicker.library.NumberPicker;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cartitem extends AppCompatActivity {
    RecyclerView recyclerView;
    GetCart getcart;
    SharedpreferenceHelper share;
    private AVLoadingIndicatorView progressBar;
    Thread splashTread;
    String tozihat = null;
    String h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartitem);


        forceRTLIfSupported();


        Button z = (Button) findViewById(R.id.titlesub2);

        z.setEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView2);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager1);
        share = new SharedpreferenceHelper(this);


        try {
            send1(share.getStoredId());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buildDialog(R.style.DialogTheme, "آیا برای ثبت خرید خود توضیحات دیگری دارید؟");


            }
        });

        getcart = new GetCart(this, recyclerView);

        try {


            getAllcarts(share.getStoredId());


//              getcart.getAllcarts(share.getStoredId());


//          Snackbar.make(findViewById(android.R.id.content),String.valueOf(zan),Snackbar.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    public void send1(long m) throws JSONException, UnsupportedEncodingException {

        String url = Services.MAINCATEGORY + m;
        JSONArray obj = null;


        JsonArrayRequest jsonObjReq = null;


        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {


                        if (response != null) {


                            parseJson4(response);
                        }

                        if (response == null) {


                        }


                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                if ((error instanceof NetworkError) || (error instanceof NoConnectionError)) {


                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "اینترنت شما قطع می باشد!", Snackbar.LENGTH_LONG)
                            .setAction("مخفی کردن", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.YELLOW);

// Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();                      // progressDialog.dismiss();
                    return;
                }
                if (error instanceof TimeoutError) {


                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "ارتباط با سرور قطع می باشد!", Snackbar.LENGTH_LONG)
                            .setAction("مخفی کردن", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.YELLOW);

// Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();                      // context.getDialog().dismiss();
                    return;
                }


                if ((error instanceof ServerError) || (error instanceof AuthFailureError)) {

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(cartitem.this);
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

    public void parseJson4(JSONArray response) {

        SharedpreferenceHelper session = new SharedpreferenceHelper(cartitem.this);


        h = response.toString();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    private void getAllcarts(long storedId) throws JSONException, UnsupportedEncodingException {
        progressBar = (AVLoadingIndicatorView) findViewById(R.id.z);
        progressBar.show();

        String url = Services.cart + storedId;
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


                            parseJson(response);
                        }

                        if (response == null) {


                        }


                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                if ((error instanceof NetworkError) || (error instanceof NoConnectionError)) {
                    Snackbar.make(findViewById(android.R.id.content), "ارتباط با شبکه قطع می باشد", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (error instanceof TimeoutError) {

                    Snackbar.make(findViewById(android.R.id.content), "TimeoutError", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if ((error instanceof ServerError) || (error instanceof AuthFailureError)) {

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(cartitem.this);
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

        CartRecyclerAdapter cartRecyclerAdapter = new CartRecyclerAdapter(cartitem.this, productList, recyclerView);
        recyclerView.setAdapter(cartRecyclerAdapter);
        cartRecyclerAdapter.notifyDataSetChanged();


        if (response.length() > 0) {
            Button z = (Button) findViewById(R.id.titlesub2);

            z.setEnabled(true);


        }


    }


    private void buildDialog(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(type);
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(getApplicationContext());
//                sessionManager.logoutUser();


                final Dialog dialog2 = new Dialog(cartitem.this);


                dialog2.setContentView(R.layout.poptozihat);

                dialog2.setCancelable(true);
//                final EditText name =(EditText)dialog.findViewById(R.id.peigiricodc1);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
//            TextView tozih=(TextView) dialog.findViewById(R.id.toto);

//                final String s1 = name.getText().toString();
                final EditText name2 = (EditText) dialog2.findViewById(R.id.peigiricodc2);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
//            TextView tozih=(TextView) dialog.findViewById(R.id.toto);
                final String s2 = name2.getText().toString();


                Button close = (Button) dialog2.findViewById(R.id.button);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        Intent intent=new Intent(getBaseContext(), searchproduct.class)  ;
//                        //   intent.putExtra("houseId",h.getHouseId());
//                        intent.putExtra("keyword",  name.getText().toString());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                        // Add new Flag to start new Activity
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getBaseContext().startActivity(intent);

//                        Toast.makeText(getApplicationContext(),z, Toast.LENGTH_SHORT).show();

                        tozihat = name2.getText().toString();

                        try {


                            kharid(share.getStorednational(), tozihat);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        dialog2.cancel();


                    }
                });


                dialog2.show();


                dialog.dismiss();
            }
        });

        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {


                    kharid(share.getStorednational(), tozihat);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();


    }


    //
    private void kharid(String storednational, String z) throws JSONException, UnsupportedEncodingException {
        progressBar = (AVLoadingIndicatorView) findViewById(R.id.z);
        progressBar.show();
        final String urlLogin = Services.kharid;
        final int reqType = Request.Method.POST;


        JSONObject jsonObject_one = new JSONObject();

        try {

            jsonObject_one.put("nationalCode", storednational);

            jsonObject_one.put("description", z);


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
                        progressBar.hide();

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
                    Snackbar.make(findViewById(android.R.id.content), "ارتباط با شبکه قطع می باشد", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (error instanceof TimeoutError) {

                    Snackbar.make(findViewById(android.R.id.content), "TimeoutError", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if ((error instanceof ServerError) || (error instanceof AuthFailureError)) {

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(cartitem.this);
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

    private void parseJson2(JSONObject response) throws JSONException {

        String z = response.getString("orderTotal");

        if (z == "0.0")


        {

            Snackbar.make(findViewById(android.R.id.content), " سبد خرید شما خالی می باشد", Snackbar.LENGTH_LONG).show();


        } else if (z != "0.0")

        {


            buildDialog2(R.style.DialogTheme, "خرید شما با موفقیت ثبت شد.");


        }

    }

    private void buildDialog2(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(type);


        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                // Do nothing
                dialog.dismiss();


                splashTread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            int waited = 0;
                            // Splash screen pause time
                            while (waited < 500) {
                                sleep(100);
                                waited += 100;
                            }


                        } catch (InterruptedException e) {
                            // do nothing
                        } finally {
                            Intent myIntent = new Intent(cartitem.this,
                                    MainActivity.class);
                            myIntent.putExtra("maincatsubcat", h
                            );

                            myIntent.putExtra("z", 1);

                            startActivity(myIntent);

                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

                            finishAffinity();


                        }

                    }
                };
                splashTread.start();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();


    }
}
