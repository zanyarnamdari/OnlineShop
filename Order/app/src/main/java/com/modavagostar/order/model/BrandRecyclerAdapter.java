package com.modavagostar.order.model;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.modavagostar.order.BrandActivity;
import com.modavagostar.order.ProductActivity;
import com.modavagostar.order.R;
import com.modavagostar.order.app.GlideApp;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import java.util.List;

/**
 * Created by HP on 2018/03/10.
 */

public class BrandRecyclerAdapter extends RecyclerView.Adapter<BrandViewHolder> {

    RecyclerView recyclerView;
    private List<Brand> list;
    BrandActivity context;
    SharedpreferenceHelper share;


    public BrandRecyclerAdapter(BrandActivity context, List<Brand> list, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.list = list;
        this.context = context;

        setListener();
    }



    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_brand, parent, false);

        return new BrandViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(BrandViewHolder holder, int position) {

        final Brand brand = list.get(position);
        holder.title.setText(brand.getTitle());



        share = new SharedpreferenceHelper(context);



        String urlImage = Services.brandimage+ brand.getImageId();





        GlideApp.with(context).load(Headers.getUrlWithHeaders(urlImage,share.gettoken()))
        .apply(RequestOptions.circleCropTransform())
                .thumbnail(0.5f)
                . placeholder(R.drawable.tenor)

                .fallback(new ColorDrawable(Color.GRAY))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(holder.imageView);

















    }


    public void setListener(){
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
//                        Brand brand = new Brand();
                        Brand brandid =  list.get(i);

                        int x=   brandid.getId();
                        String xx=   brandid.getTitle();

//                        String xx = String.valueOf(x);
//                        Toast.makeText(context, xx, Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(context, ProductActivity.class)  ;
                        //   intent.putExtra("houseId",h.getHouseId());
                        intent.putExtra("brandname", xx);

                        intent.putExtra("brandid", x);
//                        context.startActivity(intent);

                        Activity activity = (Activity) context;
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    }
                })
        );


    }


    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }
}
