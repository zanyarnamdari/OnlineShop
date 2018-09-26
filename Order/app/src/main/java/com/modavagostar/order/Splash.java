package com.modavagostar.order;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.graphics.PixelFormat;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.android.volley.toolbox.RequestFuture;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.utils.AppStatus;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Splash extends AppCompatActivity {
    SharedpreferenceHelper share;
    String h;
    int z = 1;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /**
     * Called when the activity is first created.
     */
    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Services.sliderimage.clear();
        Services.pro.clear();


        share = new SharedpreferenceHelper(this);
//         h= share.getmain();


        if (share.getStoredId() == 0) {

            h = share.getmain();
            Services.sliderimage.clear();
            Services.pro.clear();

        }
        if (share.getStoredId() != 0) {

            try {
                send1(share.getStoredId());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }


        StartAnimations();
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

//                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(Splash.this);
//                    sessionManager.logoutUser();
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

        SharedpreferenceHelper session = new SharedpreferenceHelper(Splash.this);


        h = response.toString();


        try {
            send();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


    public void send() throws JSONException, UnsupportedEncodingException {

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


                            parseJson(response);
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

//                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(Splash.this);
//                    sessionManager.logoutUser();

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


        for (int i = 0; i < response.length(); i++) {
            JSONObject jsonObject = null;
            try {

                JSONObject c = (JSONObject) response.get(Integer.parseInt(String.valueOf(i)));

                String proid = c.getString("title");

                long imgff = c.getLong("productId");


                Services.sliderimage.add(i, imgff);
                Services.pro.add(i, proid);


            } catch (JSONException e) {
                e.printStackTrace();

            }


        }


    }

    private void StartAnimations() {


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.left_enter);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 4000) {
                        sleep(100);
                        waited += 100;
                    }


                    if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

                        checkSharedPerf();

                    } else {

                        startActivity(new Intent(getApplicationContext(), Offline.class));
                        finish();

                    }


                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splash.this.finish();


                }

            }
        };
        splashTread.start();

    }

    private void checkSharedPerf() {
        SharedpreferenceHelper sharedpreferenceHelper = new SharedpreferenceHelper(getApplicationContext());

        if (sharedpreferenceHelper.isLoggedIn()) {
            Intent myIntent = new Intent(Splash.this,
                    MainActivity.class);
            myIntent.putExtra("maincatsubcat", h);
            myIntent.putExtra("z", z);

            startActivity(myIntent);

//        {startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        } else {
            //
            startActivity(new Intent(getApplicationContext(), MaterialDesignLogInForm.class));
            finish();
        }
    }
}