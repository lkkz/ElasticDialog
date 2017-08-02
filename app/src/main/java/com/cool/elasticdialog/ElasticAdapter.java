package com.cool.elasticdialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cool on 2017/8/2.
 */

public class ElasticAdapter extends RecyclerView.Adapter<ElasticAdapter.ElasticViewHolder> {

    private Context mContext;
    private String[] mTitles;

    public ElasticAdapter(Context context, String[] titles) {
        this.mContext = context;
        this.mTitles = titles;
    }

    @Override
    public ElasticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_text,parent,false);
        return new ElasticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElasticViewHolder holder, int position) {
        String text = mTitles[position];
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    class ElasticViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public ElasticViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
