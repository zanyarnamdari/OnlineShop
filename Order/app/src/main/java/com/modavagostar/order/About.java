package com.modavagostar.order;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class About extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;

    Animation anim1, anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        ImageView iv2 = (ImageView) findViewById(R.id.imageViewAbout1);
        TextView iv32 = (TextView) findViewById(R.id.names22);

        TextView iv3 = (TextView) findViewById(R.id.names2);


        TextView ivfff = (TextView) findViewById(R.id.names5);


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.ban);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.imageViewAbout1);
        iv.clearAnimation();
        iv.startAnimation(anim);


        Animation anim222 = AnimationUtils.loadAnimation(this, R.anim.khvar);
        anim.reset();
        TextView iv42 = (TextView) findViewById(R.id.names22);
        iv42.clearAnimation();
        iv42.startAnimation(anim222);


        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.khvar);
        anim.reset();
        TextView iv4 = (TextView) findViewById(R.id.names2);
        iv4.clearAnimation();
        iv4.startAnimation(anim2);


        Animation anim5 = AnimationUtils.loadAnimation(this, R.anim.khvar);
        anim.reset();
        TextView iv55 = (TextView) findViewById(R.id.names5);
        iv55.clearAnimation();
        iv55.startAnimation(anim5);


//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
//
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });


    }

}
