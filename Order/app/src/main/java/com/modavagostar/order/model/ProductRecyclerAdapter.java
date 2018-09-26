//package com.modavagostar.order.model;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.target.Target;
//import com.modavagostar.order.ProductActivity;
//import com.modavagostar.order.R;
//import com.modavagostar.order.service.Addcartitem1;
//import com.modavagostar.order.service.Delcart;
//import com.modavagostar.order.utils.Services;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONException;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by HP on 2018/03/11.
// */
//
//public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductViewHolder> {
//
//    RecyclerView recyclerView;
//    private List<Product> list;
//    ProductActivity context;
//    private Activity activity;
//    Addcartitem1 addcartitem1;
//    Delcart delcartitem1;
//
//    public ProductRecyclerAdapter(ProductActivity context, List<Product> list, RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//        this.list = list;
//        this.context = context;
//
//        setListener();
//    }
//
////    public ProductRecyclerAdapter(Context c, ArrayList<Product> products) {
////    }
//
//
//    @Override
//    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.row_product, parent, false);
//
//        return new ProductViewHolder(itemView);
//
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(final ProductViewHolder holder, int position) {
//
//        final Product product = list.get(position);
//        holder.title.setText(product.getTitle());
//        String pric = String.valueOf(product.getPrice());
//        holder.price.setText(pric+" تومان ");
//
//        String urlImage = Services.IMAGEPREVIEWURL + product.getImageId();
////        String urlImage = "http://kurdtoday.ir/file/img/news/1460437446-113kurdtoday.jpg";
//
//        Glide.with(context).load(urlImage)
//                .thumbnail(0.5f)
//                .crossFade()
//                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.zz)
//                .into(holder.imageView);
//
//
//
//        holder.miness.setVisibility(View.INVISIBLE);
//
//
//
//        holder.plass.findViewById(R.id.plas).setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        holder.miness.setVisibility(View.VISIBLE);
//                        holder.plass.setVisibility(View.INVISIBLE);
//
////                        Toast.makeText(context, "????? ???? ??? ?? ??? ???? ????? ??.", Toast.LENGTH_SHORT).show();
//
//                        addcartitem1 = new Addcartitem1(context);
//
//                        try {
//                            addcartitem1.addcart1(product.getProductId());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//
//
//
//                    }
//
//
//                });
//
//
//
//        holder.miness.findViewById(R.id.mines).setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//
//                        holder.miness.setVisibility(View.INVISIBLE);
//                        holder.plass.setVisibility(View.VISIBLE);
//
//
//
//
//                        delcartitem1 = new Delcart(context);
//
//                        try {
//                            delcartitem1.delcart1(product.getProductId());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//
//                });
//
//
//
//        holder.imageView.findViewById(R.id.imageViewRecycler).setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        final Dialog dialog = new Dialog(context);
//
//                        dialog.setContentView(R.layout.pop);
//
//                        dialog.setCancelable(true);
////                        TextView name =(TextView)dialog.findViewById(R.id.textTitle11);
////                        TextView price=(TextView)dialog.findViewById(R.id.textprice11);
//                        ImageView as=(ImageView) dialog.findViewById(R.id.assa);
//                        TextView tozih=(TextView) dialog.findViewById(R.id.toto);
//                        tozih.setText(product.getDescription());
//
//                        TextView ted=(TextView) dialog.findViewById(R.id.tedad);
//                        String z= String.valueOf(product.getInStockNumber());
//
//                        ted.setText(z);
//
//                        Picasso.with(context).load(Services.IMAGEPREVIEWURL+ product
//                                .getImageId())
//                                .placeholder(R.drawable.zzz)
//                                .into(as);
//                        Button close=(Button) dialog.findViewById(R.id.button2aa);
//                        close.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog.cancel();
//                            }
//                        });
//
////                        String c=product.getProduct_Title();
//                        int d=product.getPrice();
//
////                        name.setText(c);
////                        price.setText(""+d);
////                        setDataToView(name,address);
//
//
//
//                        dialog.show();
//                    }
//
//
//
//
//
//                });
//
//
//        //number[icker
//
//
////        holder.np.setWrapSelectorWheel(true);
////        holder.np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
////            @Override
////            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
////                //Display the newly selected number from picker
////                holder.title.setText("Selected Number : " + newVal);
////            }
////        });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//
//
//
//
//
//
//
//
//    public void setListener(){
//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int i) {
//                        Product product =  list.get(i);
//
//                        int x=   product.getProductId();
//
//                        String xx = String.valueOf(product.getProductId());
////                        Toast.makeText(context, xx, Toast.LENGTH_SHORT).show();
//
////                        Intent intent=new Intent(context, ProductActivity.class)  ;
////                        //   intent.putExtra("houseId",h.getHouseId());
////                        intent.putExtra("product", x);
////                        context.startActivity(intent);
//
//                    }
//                })
//        );
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return (null != list ? list.size() : 0);
//    }
//}
