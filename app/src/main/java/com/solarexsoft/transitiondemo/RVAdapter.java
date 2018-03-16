package com.solarexsoft.transitiondemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by houruhou on 15/03/2018.
 */

public class RVAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public static final String TAG = RVAdapter.class.getSimpleName();
    List<String> mData;
    Context mContext;
    OnItemClickListener mOnItemClickListener;

    public RVAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final String url = mData.get(position);
        Log.d(TAG, url);
        GlideApp.with(mContext).load(url).placeholder(R.mipmap.ic_launcher).into(holder.iv_item);
        if (mOnItemClickListener != null) {
            holder.iv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position, url);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String url);
    }
}
