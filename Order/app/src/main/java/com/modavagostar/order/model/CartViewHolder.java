package com.modavagostar.order.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.modavagostar.order.R;
import com.travijuu.numberpicker.library.NumberPicker;

/**
 * Created by Xaniar on 3/16/2018.
 */


public class CartViewHolder extends RecyclerView.ViewHolder {

    TextView title,title3;
//    ImageView imageView;
ImageView imageView;

    TextView des;
    TextView qty;

    TextView price;
    NumberPicker numberPicker;
    Button plass;
    Button miness;
//    NumberPicker np;des

    public CartViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.textViewTitle);
        price   = (TextView) itemView.findViewById(R.id.textViewPrice1);
        qty   = (TextView) itemView.findViewById(R.id.textViewPrice);

        imageView = (ImageView) itemView.findViewById(R.id.imageView3);

        plass = (Button) itemView.findViewById(R.id.plas);
        miness = (Button) itemView.findViewById(R.id.mines);

        numberPicker = (NumberPicker) itemView.findViewById(R.id.number_picker_default);
        title3 = (TextView) itemView.findViewById(R.id.tedad);



    }

}
