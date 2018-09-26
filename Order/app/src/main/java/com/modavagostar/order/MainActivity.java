package com.modavagostar.order;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mindorks.placeholderview.Utils.dpToPx;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context mContext;
    SwipeRefreshLayout mSwipeRefreshLayout;
    SharedpreferenceHelper share;
    String zszszs;
    MainActivity ctx;
    ArrayList<String> urlpics, urlpics2;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    private ExpandablePlaceHolderView mExpandableView;
    String h;
    MainActivity context;
    ViewPager viewPager;

    private LinkedHashMap<String, GroupItemsInfo> songsList = new LinkedHashMap<String, GroupItemsInfo>();
    private ArrayList<GroupItemsInfo> deptList = new ArrayList<GroupItemsInfo>();

    private MyExpandableListAdapter myExpandableListAdapter;
    private ExpandableListView simpleExpandableListView;
    private AdapterViewFlipper simpleAdapterViewFlipper;
    int[] fruitImages = {R.drawable.a, R.drawable.b, R.drawable.c};     // array of images
    String fruitNames[] = {"Apple", "Pine Apple", "Litchi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        share = new SharedpreferenceHelper(this);


        try {
            send1(share.getStoredId());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        h = String.valueOf(getIntent().getSerializableExtra("maincatsubcat"));
        int z = Integer.parseInt(String.valueOf(getIntent().getSerializableExtra("z")));

//اسلایدر

        if (Services.sliderimage.size() > 0) {
            urlpics = new ArrayList<>();
            urlpics2 = new ArrayList<>();


            SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider2);


            for (int zza = 0; zza < Services.sliderimage.size(); zza++)

            {
                long dd = Services.sliderimage.get(zza);
                String ee = Services.pro.get(zza);


                urlpics.add("http://185.172.68.32:8096/v1/product/returnProductImageSlider/" + dd);
                urlpics2.add(String.valueOf(dd));


            }


            for (int i = 0; i < Services.sliderimage.size(); i++) {

                String dd = Services.pro.get(i);
                zszszs = dd;
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView
                        .image(urlpics.get(i))
                        .setScaleType(BaseSliderView.ScaleType.Fit)

                ;


                sliderShow.addSlider(textSliderView);

                sliderShow.setPresetTransformer(SliderLayout.Transformer.DepthPage);

                sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                sliderShow.setCustomAnimation(new DescriptionAnimation());
                sliderShow.setDuration(4000);


                final int finalI = i;
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {


                        Intent intent = new Intent(getBaseContext(), Returnpro.class);
                        intent.putExtra("proid", urlpics2.get(finalI)
                        );
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getBaseContext().startActivity(intent);


                    }


                });


            }
            ;


        }
//
        if (Services.sliderimage.size() == 0) {
            urlpics = new ArrayList<>();

            SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider2);


            urlpics.add("android.resource://com.modavagostar.order/" + R.drawable.a);

            urlpics.add("android.resource://com.modavagostar.order/" + R.drawable.b);
            urlpics.add("android.resource://com.modavagostar.order/" + R.drawable.c);


            for (int i = 0; i < urlpics.size(); i++) {
//
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView
                        .image(urlpics.get(i))

                        .setScaleType(BaseSliderView.ScaleType.Fit);


                sliderShow.addSlider(textSliderView);

                sliderShow.setPresetTransformer(SliderLayout.Transformer.DepthPage);

                sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                sliderShow.setCustomAnimation(new DescriptionAnimation());
                sliderShow.setDuration(4000);


            }



        }


        if (z == 1) {

            try {
                JSONArray jsonArr = new JSONArray(h);
                for (int i = 0; i < jsonArr.length(); i++) {


                    JSONObject c = (JSONObject) jsonArr.get(Integer.parseInt(String.valueOf(i)));

                    String title = c.getString("mainCategoryTitle");

                    JSONArray xc = (JSONArray) c.get("subCategoryList");


                    for (int ll = 0; ll < xc.length(); ll++) {

                        JSONObject cc = (JSONObject) xc.get(Integer.parseInt(String.valueOf(ll)));


                        Boolean log = cc.getBoolean("logicalDelete");

                        String title2 = cc.getString("subCategoryTitle");
                        String idd = cc.getString("subCategoryId");

                        if (!log)
                            addProduct(title, title2, idd);
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            //get reference of the ExpandableListView
            simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
            // create the adapter by passing your ArrayList data
            myExpandableListAdapter = new MyExpandableListAdapter(MainActivity.this, deptList);
            // attach the adapter to the expandable list view
            simpleExpandableListView.setAdapter(myExpandableListAdapter);

            // setOnChildClickListener listener for child row click or song name click
            simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    //get the group header
                    GroupItemsInfo headerInfo = deptList.get(groupPosition);
                    //get the child info
                    ChildItemsInfo detailInfo = headerInfo.getSongName().get(childPosition);
                    //display it or do something with it

//                    Toast.makeText(getBaseContext(),              detailInfo.getId()
//                            ,
//                            Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(getBaseContext(), BrandActivity.class);

                    intent.putExtra("subname", detailInfo.getName());
                    intent.putExtra("Subid", detailInfo.getId());

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getBaseContext().startActivity(intent);


                    return false;
                }
            });
            // setOnGroupClickListener listener for group Song List click
            simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    //get the group header
                    GroupItemsInfo headerInfo = deptList.get(groupPosition);
                    //display it or do something with it
//                    Toast.makeText(getBaseContext(), " List Name :: " + headerInfo.getName(),
//                            Toast.LENGTH_LONG).show();

                    return false;
                }
            });

        }


        if (z == 0) {

            Toast.makeText(getApplicationContext(), "ارتباط با سرور قطع می باشد", Toast.LENGTH_SHORT).show();


        }
        //


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(MainActivity.this, cartitem.class);
                startActivity(k);
//                                       overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

//
//                bmb = (BoomMenuButton) findViewById(R.id.bmb1);
//        assert bmb != null;
//        bmb.addBuilder(BuilderManager.getHamButtonBuilder("سبد خرید", " ")
//                .normalImageRes(R.drawable.basketz));
//        bmb.addBuilder(BuilderManager.getHamButtonBuilder("تاریخچه ی خرید", " ")
//                .normalImageRes(R.drawable.shopz));
//        bmb.addBuilder(BuilderManager.getHamButtonBuilder("جستجو", "")
//                .normalImageRes(R.drawable.searchz));
//        bmb.addBuilder(BuilderManager.getHamButtonBuilder("پروفایل", "  ")
//                .normalImageRes(R.drawable.profilez));
//
//        bmb.addBuilder(BuilderManager.getHamButtonBuilder(" درباره ما", " ")
//                .normalImageRes(R.drawable.aboatz));
//
//
//        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
//            @Override
//            public void onClicked(int index, BoomButton boomButton) {
//                super.onClicked(index, boomButton);
//                changeBoomButton(index);
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private int addProduct(String songsListName, String songName, String id) {

        int groupPosition = 0;

        //check the hashmap if the group already exists
        GroupItemsInfo headerInfo = songsList.get(songsListName);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GroupItemsInfo();
            headerInfo.setName(songsListName);
            songsList.put(songsListName, headerInfo);
            deptList.add(headerInfo);
        }

        // get the children for the group
        ArrayList<ChildItemsInfo> productList = headerInfo.getSongName();
        // size of the children list
        int listSize = productList.size();
        // add to the counter
        listSize++;

        // create a new child and add that to the group
        ChildItemsInfo detailInfo = new ChildItemsInfo();
        detailInfo.setId(id);

        detailInfo.setName(songName);
        productList.add(detailInfo);
        headerInfo.setPlayerName(productList);

        // find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
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

                    SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(MainActivity.this);
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

        SharedpreferenceHelper session = new SharedpreferenceHelper(MainActivity.this);


        h = response.toString();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "برای خروج دو بار کلیک کنید",
                        Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();
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

        int id = item.getItemId();


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

            final Dialog dialog = new Dialog(MainActivity.this);


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

    //drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.buy) {
            Intent intent = new Intent(this, cartitem.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
        } else if (id == R.id.search) {
            final Dialog dialog = new Dialog(MainActivity.this);


            dialog.setContentView(R.layout.popsearch);

            dialog.setCancelable(true);
            final EditText name3 = (EditText) dialog.findViewById(R.id.peigiricodc);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
//            TextView tozih=(TextView) dialog.findViewById(R.id.toto);
            name3.setText("");

            final String s2 = name3.getText().toString();


            Button close = (Button) dialog.findViewById(R.id.button);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getBaseContext(), searchproduct.class);
                    //   intent.putExtra("houseId",h.getHouseId());
                    intent.putExtra("keyword", name3.getText().toString());
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


        } else if (id == R.id.profile) {
            Intent myIntent = new Intent(MainActivity.this,
                    profile.class);
            startActivity(myIntent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        } else if (id == R.id.histori) {
            Intent myIntent = new Intent(MainActivity.this,
                    histori_activity.class);
            startActivity(myIntent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        } else if (id == R.id.share) {
            shareIt();

        } else if (id == R.id.about) {
            Intent myIntent = new Intent(MainActivity.this,
                    About.class);
            startActivity(myIntent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


        } else if (id == R.id.exit) {

            buildDialog(R.style.DialogTheme, "آیا می خواهید از برنامه خارج شوید؟");


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void shareIt() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "مداوا گستر آبیدر");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "برای دانلود برنامه روی لینک زیر کلیک کنید https://modavagostar.com ");
        startActivity(Intent.createChooser(sharingIntent, "اشتراک گذاری با..."));
    }

    private void buildDialog(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(type);
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
                SharedpreferenceHelper sessionManager = new SharedpreferenceHelper(getApplicationContext());
                sessionManager.logoutUser();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();


    }


}
