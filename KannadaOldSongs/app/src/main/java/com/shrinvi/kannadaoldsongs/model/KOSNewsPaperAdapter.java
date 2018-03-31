package com.shrinvi.kannadaoldsongs.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shrinvi.kannadaoldsongs.analytics.KOSGoogleAnalytics;
import com.shrinvi.kannadaoldsongs.analytics.KOSGAEventsData;
import com.shrinvi.kannadaoldsongs.R;
import com.shrinvi.kannadaoldsongs.ui.KOSBrowserActivity;

import java.util.List;

public class KOSNewsPaperAdapter extends RecyclerView.Adapter<KOSNewsPaperAdapter.NewsViewHolder> {

    private List<KOSNewsPaper> mNewsPapers;
    private Context mContext;

    public KOSNewsPaperAdapter(List<KOSNewsPaper> newsPapers, Context context) {
        this.mNewsPapers = newsPapers;
        mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_paper_item_template, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final KOSNewsPaper currentNewsPaper = mNewsPapers.get(position);
        holder.mNameTv.setText(currentNewsPaper.getName());
        holder.mIconIv.setImageResource(currentNewsPaper.getIconId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (KOSUtils.isConnected(mContext)) {
                    launchBrowser(currentNewsPaper.getName(), currentNewsPaper.getUrl());
                } else {
                    KOSUtils.showDialog(mContext, mContext.getString(R.string.error_not_connected), mContext.getString(R.string.alert_button_label));
                }
            }
        });
    }

    private void launchBrowser(String title, String url) {
        KOSGoogleAnalytics.sendCustomEvent(KOSGAEventsData.CUSTOM_EVENT_CATEGORY_LAUNCH_NEWS, title);
        Intent browserIntent = new Intent(mContext, KOSBrowserActivity.class);
        browserIntent.putExtra(KOSConstants.INTENT_EXTRA_URL, url);
        browserIntent.putExtra(KOSConstants.INTENT_EXTRA_TITLE, title);
        mContext.startActivity(browserIntent);
    }

    @Override
    public int getItemCount() {
        if (mNewsPapers != null) {
            return mNewsPapers.size();
        }
        return 0;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView mNameTv;
        View mItemView;
        ImageView mIconIv;

        NewsViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mNameTv = mItemView.findViewById(R.id.np_name_tv);
            mIconIv = mItemView.findViewById(R.id.icon_iv);

        }
    }
}
