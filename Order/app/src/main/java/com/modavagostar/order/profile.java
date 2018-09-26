package com.modavagostar.order;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.service.GetBrandService;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {
    TextView name, famil, kod, pass, phone;
    SharedpreferenceHelper share;
    Button passs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        forceRTLIfSupported();

        name = (TextView) findViewById(R.id.name);

//        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/bnaz.ttf");
//        name.setTypeface(face);


        kod = (TextView) findViewById(R.id.kodmeli);
//        pass = (TextView) findViewById(R.id.pass);
        phone = (TextView) findViewById(R.id.phone);

        passs = (Button) findViewById(R.id.button2);


        share = new SharedpreferenceHelper(this);

        String n = share.getStoredname();
        String nat = share.getStorednational();
//        String pas = share.getStoredpass();
        String pho = share.getStoredphone();
        String z = " ";

        name.setText(z + "نام :" + n);
        kod.setText(z + "کد ملی :" + nat);
//        pass.setText(z+"رمز عبور :"+pas);
        phone.setText(z + "تلفن :" + pho);


        passs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(profile.this);


                dialog.setContentView(R.layout.popeditpass);

                dialog.setCancelable(true);
//                final EditText name =(EditText)dialog.findViewById(R.id.peigiricodc1);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
//            TextView tozih=(TextView) dialog.findViewById(R.id.toto);

//                final String s1 = name.getText().toString();
                final EditText name2 = (EditText) dialog.findViewById(R.id.peigiricodc2);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
//            TextView tozih=(TextView) dialog.findViewById(R.id.toto);
                final String s2 = name2.getText().toString();


                Button close = (Button) dialog.findViewById(R.id.button);
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
                        try {
                            sendToServer(name2.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        dialog.cancel();


                    }
                });


                dialog.show();

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    private void sendToServer(String s) throws JSONException, UnsupportedEncodingException {

        final String urlLogin = Services.pass;
        final int reqType = Request.Method.POST;


        String zz = String.valueOf(share.getStoredId());


        JSONObject jsonObject_one = new JSONObject();

        try {
            jsonObject_one.put("mobileUserId", zz);

            jsonObject_one.put("nationalCode", share.getStorednational());
            jsonObject_one.put("newPassword", s);


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
                    Snackbar.make(findViewById(android.R.id.content), "ارتباط با شبکه قطع می باشد", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (error instanceof TimeoutError) {

                    Snackbar.make(findViewById(android.R.id.content), "TimeoutError", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if ((error instanceof ServerError) || (error instanceof AuthFailureError)) {

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(profile.this);
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
        boolean res = response.getBoolean("result");
        if (res)

        {
            Snackbar.make(findViewById(android.R.id.content), "رمز عبور با موفقیت تغییر یافت.", Snackbar.LENGTH_SHORT).show();


        }

        if (!res) {
            Snackbar.make(findViewById(android.R.id.content), "کاربر مورد نظر یافت نشد.", Snackbar.LENGTH_SHORT).show();


        }


    }
}