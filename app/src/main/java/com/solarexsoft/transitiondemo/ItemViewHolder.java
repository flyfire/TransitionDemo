package com.solarexsoft.transitiondemo;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by houruhou on 15/03/2018.
 */

public class ItemViewHolder extends ViewHolder {
    ImageView iv_item;
    public ItemViewHolder(View itemView) {
        super(itemView);
        iv_item = itemView.findViewById(R.id.iv_item);
    }
}
