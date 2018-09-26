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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.model.ListAdapter2;
import com.modavagostar.order.model.Product;
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

public class searchproduct extends AppCompatActivity {
    int tedadkol;
    String zk;
    int dsds = 0;
    boolean tamam = false;
    boolean tamam2 = false;

    private RecyclerView productView;
    private List<Product> listUnitList = new ArrayList<>();
    private ListAdapter2 listAdapter;
    private String pListURL;
    private RequestQueue requestQueue;
    private int page = 0;
    private LinearLayoutManager layoutManager;
    private ProgressBar progressBarLoading;
    private AVLoadingIndicatorView progressBar;
    SharedpreferenceHelper share;
    String h;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchproduct);


        forceRTLIfSupported();


        String hh = String.valueOf(getIntent().getSerializableExtra("keyword"));

        Button z = (Button) findViewById(R.id.titlesub2);
        z.setText(" جستجوی: " + hh);


        //define views
        productView = findViewById(R.id.myRecyclerView2);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        productView.setLayoutManager(layoutManager);
        progressBarLoading = findViewById(R.id.progressBarLoading);
        //initialize adapter
        listAdapter = new ListAdapter2(searchproduct.this, listUnitList);
        share = new SharedpreferenceHelper(searchproduct.this);


        try {
            send1(share.getStoredId());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        //set adapter
        //hide loading bar
        progressBarLoading.setVisibility(View.INVISIBLE);
        productView.setAdapter(listAdapter);

        productView.addOnScrollListener(prOnScrollListener);


        pListURL = "http://185.172.68.32:8096/v1/product/searchProduct?page=";


        requestQueue = Volley.newRequestQueue(searchproduct.this);

        //getdata from server
        requestQueue.add(getDataFromServer(page));


//on create ends here
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

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(searchproduct.this);
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

        SharedpreferenceHelper session = new SharedpreferenceHelper(searchproduct.this);


        h = response.toString();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    private RecyclerView.OnScrollListener prOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (islastItemDisplaying(recyclerView)) {

                progressBarLoading.setVisibility(View.VISIBLE);


                getData();


                Log.i("ListActivity", "LoadMore");
            }

        }


    };

    private boolean islastItemDisplaying(RecyclerView recyclerView) {
        //check if the adapter item count is greater than 0
        if (recyclerView.getAdapter().getItemCount() != 0) {
            //get the last visible item on screen using the layoutmanager
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            //apply some logic here.
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }

        return false;
    }

    private void getData() {
        page = page + 1;

        //add to requestQueue
        requestQueue.add(getDataFromServer(page));

        //increment page number

        //remove any loading progress here
    }

    private JsonObjectRequest getDataFromServer(final int page) {


        progressBar = (AVLoadingIndicatorView) findViewById(R.id.z);

        progressBar.show();

        final String urlLogin = pListURL + page;
        final int reqType = Request.Method.POST;


//        Toast.makeText(getApplicationContext(),urlLogin, Toast.LENGTH_SHORT).show();


        JSONObject jsonObject_one = new JSONObject();

        try {
            String hh = String.valueOf(getIntent().getSerializableExtra("keyword"));
//            jsonObject_one.put("brandId", String.valueOf(h));
            jsonObject_one.put("keyword", hh);


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
                                parseData(response);
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

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(searchproduct.this);
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

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_arry);


        return jsonObjReq;


    }

    private void parseData(JSONObject response) throws JSONException {
        //create a forLoop
        for (int i = 0; i < response.length(); i++) {
            Product listUnit = new Product();
            JSONObject jsonObject = null;
            try {
//
                Log.i("Response: ", String.valueOf(jsonObject));


                JSONArray zz = response.getJSONArray("content");

                String zxzx = response.getString("totalPages");
                zk = zxzx;

                String zzaaaazz = response.getString("totalElements");
                tedadkol = Integer.parseInt(zzaaaazz);

                JSONObject c = (JSONObject) zz.get(Integer.parseInt(String.valueOf(i)));
                JSONArray xc = (JSONArray) c.get("productImages");
//                JSONObject ccc = (JSONObject) xc.get(Integer.parseInt(String.valueOf(i)));
                JSONObject kn = (JSONObject) xc.get(0);
                long imgff = kn.getLong("imageId");


                int userId = c.getInt("productId");
                if (userId > 0) {
                    tamam2 = true;

                }
                String title = c.getString("title");

                String des = c.getString("description");
//                int inStockNumber = c.getInt("inStockNumber");
                int price = c.getInt("price");
                int price2 = c.getInt("sellPrice");


                int inbox = c.getInt("inBoxNumber");


                if (price > 0) {
                    tamam = true;

                }
                listUnit.setPrice(price);
                listUnit.setProductId(userId);
                listUnit.setTitle(title);
                listUnit.setDescription(des);
                listUnit.setImageId(imgff);
                listUnit.setSellPrice(price2);
                listUnit.setInBoxNumber(inbox);
//                listUnit.setInStockNumber(inStockNumber);
            } catch (JSONException e) {
                e.printStackTrace();

            }
            if (zk != "0" && (tamam) && (tamam2))

                listUnitList.add(listUnit);

            tamam2 = false;

        }
        if ((zk != "0" && (tamam))) {
            //notify the adapter that some things has changed
            listAdapter.notifyDataSetChanged();
        }
        tamam = false;
        progressBarLoading.setVisibility(View.INVISIBLE);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_home) {

            Intent myIntent = new Intent(searchproduct.this,
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
//
            return true;
        }
        if (id == R.id.action_search) {

            final Dialog dialog = new Dialog(searchproduct.this);


            dialog.setContentView(R.layout.popsearch);

            dialog.setCancelable(true);
            final EditText name = (EditText) dialog.findViewById(R.id.peigiricodc);
//
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
















