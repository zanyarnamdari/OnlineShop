package com.modavagostar.order.expand;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;
import com.modavagostar.order.BrandActivity;
import com.modavagostar.order.R;
import com.modavagostar.order.model.globaluser;


/**
 * Created by janisharali on 24/08/16.
 */
@Layout(R.layout.feed_item)
public class InfoView {
    private ExpandablePlaceHolderView mExpandableView;

    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;
    @View(R.id.asd)
    private LinearLayout asd;


    @View(R.id.titleTxt)


    private TextView titleTxt;

    private Info mInfo;
    private Context mContext;

    public InfoView(Context context, Info info) {
        mContext = context;
        mInfo = info;
    }


    @Resolve
    private void onResolved() {

        if (!mInfo.getLogical()) {

            titleTxt.setText(mInfo.getTitle());


        }


//        String url= Services.urlimage+mInfo.getSubid();
//        Glide.with(mContext).load(url).into(imageViewa);


        asd.setOnClickListener(
                new android.view.View.OnClickListener() {

                    @Override
                    public void onClick(android.view.View v) {


                        String f = String.valueOf(mInfo.getSubid());
//                        globaluser.subtitle=mInfo.getTitle();

//                        Toast.makeText(mContext, f, Toast.LENGTH_SHORT).show();


                        if (!mInfo.getLogical()) {

                            String xx = mInfo.getTitle();


                            Intent intent = new Intent(mContext, BrandActivity.class);

                            intent.putExtra("subname", xx);
                            intent.putExtra("Subid", f);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            mContext.startActivity(intent);

                        }


                    }


                });


    }


}
