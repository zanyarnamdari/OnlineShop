package com.modavagostar.order.model;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.modavagostar.order.R;
import com.modavagostar.order.app.GlideApp;
import com.modavagostar.order.cartitem;
import com.modavagostar.order.service.Addcartitem1;
import com.modavagostar.order.service.Addcartitem2;
import com.modavagostar.order.service.Delcart;
import com.modavagostar.order.service.Delcart2;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;
import com.squareup.picasso.Picasso;
import com.travijuu.numberpicker.library.NumberPicker;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Xaniar on 3/16/2018.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder> {

    RecyclerView recyclerView;
    private List<cartlist> list;
    cartitem context;
    private Activity activity;
    Addcartitem2 addcartitem1;
    Delcart2 delcartitem1;
    SharedpreferenceHelper share;


    public CartRecyclerAdapter(cartitem context, List<cartlist> list, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.list = list;
        this.context = context;

//        setListener();
    }



    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cart, parent, false);

        return new CartViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {

        holder.numberPicker.setMax(100);
        holder.numberPicker.setMin(1);
        holder.numberPicker.setUnit(1);
        holder.numberPicker.setValue(1);









        final cartlist product = list.get(position);
        holder.title.setText(product.getTitle());
        String pric = String.valueOf(product.getPrice());
        holder.price.setText(" ق.خرید : "+pric+" تومان ");
        String q = String.valueOf(product.getQty());

        holder.qty.setText("تعداد کارتن : "+q);
        holder.title3.setText("تعداد کالا در هر کارتن : "+String.valueOf((product.getInBoxNumber())));




        String urlImage = Services.IMAGEPREVIEWURL + product.getImageId();
//
//        Glide.with(context).load(urlImage)
//                .thumbnail(0.5f)
//                .crossFade()
//                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.tenor)
//                .into(holder.imageView);




        share = new SharedpreferenceHelper(context);


        GlideApp.with(context).load(Headers.getUrlWithHeaders(urlImage,share.gettoken()))

                .thumbnail(0.5f)
                . placeholder(R.drawable.tenor)

                .fallback(new ColorDrawable(Color.GRAY))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(holder.imageView);




        holder.plass.findViewById(R.id.plas).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {



                        addcartitem1 = new Addcartitem2(context);

                        try {
//


                                addcartitem1.addcart1(product.getProductId(),String.valueOf(holder.numberPicker.getValue()));
//                           else if(holder.checkBox1.isChecked())
//                                addcartitem1.addcart1(product.getProductId(),String.valueOf(holder.numberPicker.getValue()*product.getPrice()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }




                    }


                });



        holder.miness.findViewById(R.id.mines).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {







                        delcartitem1 = new Delcart2(context);

                        try {
                            delcartitem1.delcart1(product.getProductId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                    }


                });














    }










    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }
}
