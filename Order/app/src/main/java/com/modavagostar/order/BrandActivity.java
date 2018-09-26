package com.modavagostar.order;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.modavagostar.order.service.GetBrandService;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BrandActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GetBrandService getBrandService;
    SharedpreferenceHelper share;
    String h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        forceRTLIfSupported();


        int h = Integer.parseInt(String.valueOf(getIntent().getSerializableExtra("Subid")));
        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

//        findViewById(R.id.material_design_ball_scale_ripple_loader).setVisibility(View.VISIBLE);


        share = new SharedpreferenceHelper(BrandActivity.this);


        try {
            send1(share.getStoredId());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String hh = String.valueOf(getIntent().getSerializableExtra("subname"));

        Button z = (Button) findViewById(R.id.titlesub2);
        z.setText(hh);
        /*RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager1);*/

        getBrandService = new GetBrandService(this, recyclerView);


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);
        anim.reset();
        @SuppressLint("CutPasteId") RecyclerView iv = (RecyclerView) findViewById(R.id.myRecyclerView);
        iv.clearAnimation();
        iv.startAnimation(anim);


        try {
            getBrandService.getAllBrands(h);
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

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(BrandActivity.this);
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

        SharedpreferenceHelper session = new SharedpreferenceHelper(BrandActivity.this);


        h = response.toString();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {

            Intent myIntent = new Intent(BrandActivity.this,
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


            return true;
        }

        if (id == R.id.action_viewcart) {
            Intent intent = new Intent(this, cartitem.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);

            finish();

//            SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(getApplicationContext());
//            sessionManager.logoutUser();
            return true;
        }
        if (id == R.id.action_search) {

            final Dialog dialog = new Dialog(BrandActivity.this);


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
}
