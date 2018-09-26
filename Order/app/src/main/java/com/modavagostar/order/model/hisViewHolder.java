package com.modavagostar.order.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.modavagostar.order.R;
import com.travijuu.numberpicker.library.NumberPicker;

/**
 * Created by Xaniar on 3/16/2018.
 */


public class hisViewHolder extends RecyclerView.ViewHolder {

    TextView title,title2,title3;

//    NumberPicker np;des

    public hisViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.textView);

        title2 = (TextView) itemView.findViewById(R.id.textView2);

        title3 = (TextView) itemView.findViewById(R.id.textView3);

    }

}
