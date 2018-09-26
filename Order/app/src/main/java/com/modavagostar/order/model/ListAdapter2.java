package com.modavagostar.order.model;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.modavagostar.order.ProductActivity;
import com.modavagostar.order.R;
import com.modavagostar.order.app.GlideApp;
import com.modavagostar.order.searchproduct;
import com.modavagostar.order.service.Addcartitem1;
import com.modavagostar.order.service.Addcartitem3;
import com.modavagostar.order.service.Delcart;
import com.modavagostar.order.service.Delcart3;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;
import com.squareup.picasso.Picasso;
import com.travijuu.numberpicker.library.NumberPicker;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Somee on 17/12/2017.
 */
//this guys kinda like arranges everything, just like the guy that sorts a bunch of forms filled by clients

public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.MyViewHolder> {

    private List<Product> listUnitList;
    searchproduct context;
    Addcartitem3 addcartitem1;
    Delcart3 delcartitem1;
    SharedpreferenceHelper share;

    //create a class accessible from othe activities
    public ListAdapter2(searchproduct context, List<Product> listUnitList){
        this.context = context;
        this.listUnitList = listUnitList;

    }

    @Override
    public ListAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //this method specifies the layout resource to be used, which is list_unit.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product2,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ListAdapter2.MyViewHolder holder, int position) {

        holder.numberPicker.setMax(100);
        holder.numberPicker.setMin(1);
        holder.numberPicker.setUnit(1);
        holder.numberPicker.setValue(1);

        //this method helps to get the position, and set parameters, more like feeling a spreadsheet of different columns and rows from a form filled by a client
        final Product product = listUnitList.get(position);
        holder.title.setText(product.getTitle());
        holder.title2.setText(product.getTitle());
        holder.title3.setText("تعداد کالا در هر کارتن : "+String.valueOf((product.getInBoxNumber()))
        );
        String pric = String.valueOf(product.getPrice());
        String pric2 = String.valueOf(product.getSellPrice());

        holder.price.setText("ق.خرید : "+pric+" تومان ");
        holder.title2.setText("ق.مصرف کننده : "+pric2+" تومان ");

        String urlImage = Services.IMAGEPREVIEWURL + product.getImageId();

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






        holder.miness.setVisibility(View.INVISIBLE);



        holder.plass.findViewById(R.id.plas).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        holder.miness.setVisibility(View.VISIBLE);
                        holder.plass.setVisibility(View.INVISIBLE);

//                        Toast.makeText(context, "????? ???? ??? ?? ??? ???? ????? ??.", Toast.LENGTH_SHORT).show();

                        addcartitem1 = new Addcartitem3(context);

                        try {


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


                        holder.miness.setVisibility(View.INVISIBLE);
                        holder.plass.setVisibility(View.VISIBLE);




                        delcartitem1 = new Delcart3(context);

                        try {
                            delcartitem1.delcart1(product.getProductId());
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


                        holder.miness.setVisibility(View.INVISIBLE);
                        holder.plass.setVisibility(View.VISIBLE);




                        delcartitem1 = new Delcart3(context);

                        try {
                            delcartitem1.delcart1(product.getProductId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                    }


                });

        holder.plass2.findViewById(R.id.plas2).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context);

                        dialog.setContentView(R.layout.poptakhfifat);

                        dialog.setCancelable(true);
//                        TextView name =(TextView)dialog.findViewById(R.id.textTitle11);
//                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
                        TextView tozih=(TextView) dialog.findViewById(R.id.toto);
                        tozih.setText(product.getDescription());








                        dialog.show();
                    }





                });





    }

    @Override
    public int getItemCount() {
        return listUnitList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView title2;
        TextView title3;

        ImageView imageView;
        TextView price;
        Button plass,plass2;
        Button miness;
        NumberPicker numberPicker;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.textViewTitle);
            title2 = (TextView) itemView.findViewById(R.id.textViewTitle2);
            title3 = (TextView) itemView.findViewById(R.id.tedad);

            price   = (TextView) itemView.findViewById(R.id.textViewPrice1);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewRecycler);
            plass2 = (Button) itemView.findViewById(R.id.plas2);

            plass = (Button) itemView.findViewById(R.id.plas);
            miness = (Button) itemView.findViewById(R.id.mines);

            numberPicker = (NumberPicker) itemView.findViewById(R.id.number_picker_default);


        }
    }
}
