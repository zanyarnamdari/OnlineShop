package com.modavagostar.order.model;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.ULocale;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.modavagostar.order.R;
import com.modavagostar.order.app.GlideApp;
import com.modavagostar.order.cartitem;
import com.modavagostar.order.histori_activity;
import com.modavagostar.order.service.Addcartitem2;
import com.modavagostar.order.service.Delcart2;
import com.modavagostar.order.utils.Services;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;


/**
 * Created by Xaniar on 3/16/2018.
 */

public class hisRecyclerAdapter extends RecyclerView.Adapter<hisViewHolder> {

    RecyclerView recyclerView;
    private List<historilist> list;
    private histori_activity context;
    private Activity activity;


    public hisRecyclerAdapter(histori_activity context, List<historilist> list, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.list = list;
        this.context = context;

//        setListener();
    }



    @Override
    public hisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowhis, parent, false);

        return new hisViewHolder(itemView);

    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final hisViewHolder holder, int position) {









        final historilist product = list.get(position);






        long val = product.getOrderDate();
        Date date=new Date(val);




//        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
//        String dateText = df2.format(date);



        PersianDate pdate = new PersianDate(val);
        PersianDateFormat pdformater1 = new PersianDateFormat("Y/m/d");
        String dateText=   pdformater1.format(pdate);






        holder.title.setText(" تاریخ خرید : "+dateText);
if (product.isOrderSubmit())

        holder.title2.setText("ثبت شده");
else if (!product.isOrderSubmit())

    holder.title2.setText("ثبت نشده");


        holder.title3.setText(" جمع خرید : "+String.valueOf((product.getOrderTotal())));







    }










    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }
}
