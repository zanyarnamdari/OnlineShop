package com.modavagostar.order;

/**
 * Created by HP on 2018/02/02.
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.modavagostar.order.model.User;
import com.modavagostar.order.service.Gethistori;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MaterialDesignLogInForm extends AppCompatActivity {


    int z = 0;
    Context context;
    Activity ctx2;
    String maincategory;
    TextInputLayout name_layout, national_layout, mobile_layout, famil_layout;
    EditText nationalcode, name, famil, mobile, txtemail, txtname;
    Button buttonlogin, btn1, btn2, btnSignUp, btnClear;
    MaterialDesignLogInForm ctx;
    SharedpreferenceHelper share;

    //    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);


        Services.sliderimage.clear();
        Services.pro.clear();


        txtemail = (EditText) findViewById(R.id.txtemail);
        txtname = (EditText) findViewById(R.id.txtname);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s2 = txtemail.getText().toString();
                String s1 = txtname.getText().toString();


                if (TextUtils.isEmpty(s1)) {


                    txtname.setError("کد ملی را وارد کنید");
                    return;

                }
                if (TextUtils.isEmpty(s2)) {
                    txtname.setError(null);

                    txtemail.setError("رمز عبور را وارد کنید");
                    return;


                } else {


                    txtemail.setError(null);


                    User user = new User();
                    user.setNationalCode(txtname.getText().toString().trim());
                    user.setPassword(txtemail.getText().toString().trim());


                    try {
                        sendToServer(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }


            }
        });


//        btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txtemail.setText("");
//                txtname.setText("");
//                txtname.setError(null);
//                txtemail.setError(null);
//
//
//
//            }
//        });
    }


    public void send(final String zzz) throws JSONException, UnsupportedEncodingException {

        String url = Services.slider;
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


                    Toast.makeText(getApplicationContext(), "اینترنت همراه گوشی شما خاموش می باشد", Toast.LENGTH_SHORT).show();
                    // progressDialog.dismiss();
                    return;
                }
                if (error instanceof TimeoutError) {


                    Toast.makeText(getApplicationContext(), "ارتباط با سرور قطع می باشد", Toast.LENGTH_SHORT).show();
                    // context.getDialog().dismiss();
                    return;
                }


                if ((error instanceof ServerError) || (error instanceof AuthFailureError)) {

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(MaterialDesignLogInForm.this);
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
                headers.put("tokens", zzz);

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


        for (int i = 0; i < response.length(); i++) {
            JSONObject jsonObject = null;
            try {

                JSONObject c = (JSONObject) response.get(Integer.parseInt(String.valueOf(i)));

                String proid = c.getString("title");

                long imgff = c.getLong("productId");


                Services.sliderimage.add(i, imgff);
                Services.pro.add(i, proid);
//                Toast.makeText(getApplicationContext(), String.valueOf(proid), Toast.LENGTH_SHORT).show();
//
//                Toast.makeText(getApplicationContext(), String.valueOf(imgff), Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();

            }


        }


        SharedpreferenceHelper session2 = new SharedpreferenceHelper(MaterialDesignLogInForm.this);
//String mm= String.valueOf(z);
//        Toast.makeText(getApplicationContext(), maincategory, Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(MaterialDesignLogInForm.this,
                MainActivity.class);
//        Intent myIntent = new Intent(MaterialDesignLogInForm.this,
//                histori_activity.class);
        myIntent.putExtra("maincatsubcat", session2.getmain());

        myIntent.putExtra("z", z);

        startActivity(myIntent);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        finishAffinity();


    }

    public void send1(String m) throws JSONException, UnsupportedEncodingException {
        share = new SharedpreferenceHelper(this);

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


                            parseJson(response);
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

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(MaterialDesignLogInForm.this);
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

        SharedpreferenceHelper session = new SharedpreferenceHelper(MaterialDesignLogInForm.this);


        session.main(response.toString());

        maincategory = response.toString();
        z = 1;


        try {
            send(session.gettoken());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    public void sendToServer(User user) throws JSONException, UnsupportedEncodingException {

        final String urlLogin = Services.LOGIN;
        final int reqType = Request.Method.POST;


        JSONObject jsonObject_one = new JSONObject();

        try {

            jsonObject_one.put("nationalCode", user.getNationalCode());
            jsonObject_one.put("password", user.getPassword());


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

                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "ارتباط با شبکه قطع می باشد!", Snackbar.LENGTH_LONG)
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
                    snackbar.show();
                    return;
                }

                if (error instanceof TimeoutError) {

                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Time Out!", Snackbar.LENGTH_LONG)
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
                    snackbar.show();
                    return;
                }

                if ((error instanceof ServerError) || (error instanceof AuthFailureError)) {

                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "خطا از سرور!", Snackbar.LENGTH_LONG)
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
                    snackbar.show();
                    return;
                }

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };


        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//         Log.e("Body",new String(jsonObjReq.getBody(), "UTF-8"));
        String tag_json_arry = "json_array_req";
// Adding request to request queue
        // mRequestQueue.add(jsonObjReq);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_arry);
    }


    public void parseJson2(JSONObject response) throws JSONException {

        boolean res = response.getBoolean("result");

        if (res) {


            String m = response.getString("token");


            try {


                SharedpreferenceHelper session2 = new SharedpreferenceHelper(MaterialDesignLogInForm.this);


                session2.token(m);


                decode(m);


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        if (!res) {

            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "کد ملی یا رمز اشتباه می باشد!", Snackbar.LENGTH_LONG)
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
            snackbar.show();
        }

    }

    private void decode(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
//            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
        } catch (UnsupportedEncodingException e) {
            //Error
        }
    }

    private String getJson(String strEncoded) throws UnsupportedEncodingException, JSONException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);

        String a = new String(decodedBytes);


        SharedpreferenceHelper session = new SharedpreferenceHelper(MaterialDesignLogInForm.this);
//
        User user = new User();
        JSONObject mainObject = new JSONObject(a);
        String uniName = mainObject.getString("name");
        String id = mainObject.getString("userId");
        String phone = mainObject.getString("phone");
        String national = mainObject.getString("nationalCode");
        user.setName(uniName);
        user.setNationalCode(national);
        user.setId(Long.parseLong(id));
        user.setPhone(phone);


        session.createLoginSession(user);

        String m = id;
        try {
            send1(m);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return new String(decodedBytes, "UTF-8");
    }


}