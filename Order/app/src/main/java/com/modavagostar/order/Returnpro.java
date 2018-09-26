package com.modavagostar.order;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.app.GlideApp;
import com.modavagostar.order.service.Addcartitem1;
import com.modavagostar.order.service.Addcartitem4;
import com.modavagostar.order.service.Delcart;
import com.modavagostar.order.service.Delcart4;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;
import com.travijuu.numberpicker.library.NumberPicker;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Returnpro extends AppCompatActivity {
    TextView titlez;
    TextView title2;
    TextView title3;
    String h;
    ImageView imageView;
    TextView pricez;
    Button plass, plass2;
    Button miness;
    NumberPicker numberPicker;
    Delcart4 delcartitem1;

    Addcartitem4 addcartitem1;
    SharedpreferenceHelper share;

    private AVLoadingIndicatorView progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnpro);

        forceRTLIfSupported();
        h = String.valueOf(getIntent().getSerializableExtra("proid"));

        share = new SharedpreferenceHelper(getApplicationContext());


        progressBar = (AVLoadingIndicatorView) findViewById(R.id.z);


        progressBar.show();


        try {
            send1(h);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//
////            SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(getApplicationContext());
////            sessionManager.logoutUser();
//            return true;
//
        if (id == R.id.action_viewcart2) {
            Intent intent = new Intent(this, cartitem.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

//            SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(getApplicationContext());
//            sessionManager.logoutUser();
            return true;
        }
        if (id == R.id.action_search2) {

            final Dialog dialog = new Dialog(Returnpro.this);


            dialog.setContentView(R.layout.popsearch);

            dialog.setCancelable(true);
            final EditText name = (EditText) dialog.findViewById(R.id.peigiricodc);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
//            TextView tozih=(TextView) dialog.findViewById(R.id.toto);
            name.setText("");

            final String s2 = name.getText().toString();


            Button close = (Button) dialog.findViewById(R.id.button);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getBaseContext(), searchproduct.class);
                    //   intent.putExtra("houseId",h.getHouseId());
                    intent.putExtra("keyword", name.getText().toString());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getBaseContext().startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);

                    dialog.cancel();


                }
            });


            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;

            dialog.show();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void send1(String m) throws JSONException, UnsupportedEncodingException {

        String url = Services.returnpro + m;
        JSONArray obj = null;


        JsonObjectRequest jsonObjReq = null;


        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        progressBar.hide();


                        if (response != null) {


                            try {
                                parseJson4(response);
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

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(Returnpro.this);
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

    private void parseJson4(JSONObject response) throws JSONException {


        String title = response.getString("title");

        String price = response.getString("price");
        String sellPrice = response.getString("sellPrice");
        final String description = response.getString("description");
        String inBoxNumber = response.getString("inBoxNumber");

        JSONArray xc = (JSONArray) response.get("productImages");


        JSONObject kn = (JSONObject) xc.get(0);

        String z = kn.getString("imageId");


        String urlImage = Services.IMAGEPREVIEWURL + z;


        titlez = (TextView) findViewById(R.id.textViewTitle);

        titlez.setText(title);

        title2 = (TextView) findViewById(R.id.textViewTitle2);

        title2.setText(" ق.مصرف کننده : " + sellPrice + " تومان ");


        title3 = (TextView) findViewById(R.id.tedad);
        title3.setText("تعداد کالا در هر کارتن : " + inBoxNumber);

        pricez = (TextView) findViewById(R.id.textViewPrice1);
        pricez.setText(" ق.خرید : " + price + " تومان ");
        imageView = (ImageView) findViewById(R.id.imageViewRecycler);

        plass2 = (Button) findViewById(R.id.plas2);

        plass = (Button) findViewById(R.id.plas);
        miness = (Button) findViewById(R.id.mines);

        numberPicker = (NumberPicker) findViewById(R.id.number_picker_default);
        numberPicker.setMax(100);
        numberPicker.setMin(1);
        numberPicker.setUnit(1);
        numberPicker.setValue(1);

        GlideApp.with(Returnpro.this).load(Headers2.getUrlWithHeaders(urlImage, share.gettoken()))
                .thumbnail(0.5f)
                .placeholder(R.drawable.tenor)

                .fallback(new ColorDrawable(Color.GRAY))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);


        plass2.findViewById(R.id.plas2).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(Returnpro.this);

                        dialog.setContentView(R.layout.poptakhfifat);

                        dialog.setCancelable(true);
//                        TextView name =(TextView)dialog.findViewById(R.id.textTitle11);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
                        TextView tozih = (TextView) dialog.findViewById(R.id.toto);
                        tozih.setText(description);


                        dialog.show();
                    }


                });


        miness.setVisibility(View.INVISIBLE);
        miness.findViewById(R.id.mines).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        miness.setVisibility(View.INVISIBLE);
                        plass.setVisibility(View.VISIBLE);


                        delcartitem1 = new Delcart4(Returnpro.this);

                        try {
                            delcartitem1.delcart1(Integer.parseInt(h));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                    }


                });


        plass.findViewById(R.id.plas).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        miness.setVisibility(View.VISIBLE);
                        plass.setVisibility(View.INVISIBLE);

//                        Toast.makeText(context, "????? ???? ??? ?? ??? ???? ????? ??.", Toast.LENGTH_SHORT).show();

                        addcartitem1 = new Addcartitem4(Returnpro.this);

                        try {


                            addcartitem1.addcart1(Integer.parseInt(h), String.valueOf(numberPicker.getValue()));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                    }


                });


    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}
