package com.modavagostar.order.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.modavagostar.order.R;

/**
 * Created by HP on 2018/03/10.
 */

public class BrandViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    ImageView imageView;

    public BrandViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.textViewTitle);
//        price = (TextView) itemView.findViewById(R.id.textViewPrice);
        imageView = (ImageView) itemView.findViewById(R.id.imageViewRecycler);
    }

}
