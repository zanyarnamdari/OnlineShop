package com.modavagostar.order;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.modavagostar.order.service.GetCart;
import com.modavagostar.order.service.Gethistori;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class histori_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    SharedpreferenceHelper share;
    Gethistori getcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_activity);

        forceRTLIfSupported();


        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView2);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(layoutManager);

//     RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Reverse Item
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);


        recyclerView.setLayoutManager(layoutManager);


        share = new SharedpreferenceHelper(this);


        getcart = new Gethistori(this, recyclerView);

        try {

            getcart.getAllcarts(share.getStoredId());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}
