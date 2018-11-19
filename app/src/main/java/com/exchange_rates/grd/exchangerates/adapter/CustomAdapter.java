package com.exchange_rates.grd.exchangerates.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.model_sync.domain.pojo.Rate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private Context context;
    private List<Rate> dataList ;
    private List<Rate> tempList = new ArrayList<>();

    public CustomAdapter(Context context,List<Rate> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        TextView txtValue;
        TextView txtRound;
        TextView txtChange;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            txtValue = mView.findViewById(R.id.tvValue);
            txtRound = mView.findViewById(R.id.tvRound);
            txtChange = mView.findViewById(R.id.tvСhange);

            coverImage = mView.findViewById(R.id.thumbnails);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Log.v(LOG_TAG, " Change is "
                +dataList.get(position).getChange24h()
                +" Method is "
                +Thread.currentThread().getStackTrace()[2].getMethodName() );

        holder.txtTitle.setText(dataList.get(position).getSymbol());
       // holder.txtValue.setText(dataList.get(position).getAsk());
        holder.txtValue.setText(dataList.get(position).getBid());
        holder.txtRound.setText(dataList.get(position).getDigits() + " (round)");

        float change = Float.parseFloat(dataList.get(position).getChange24h());
        if(change >= 0){
            holder.txtChange.setTextColor(Color.parseColor("#009900"));
        }else{
            holder.txtChange.setTextColor(Color.rgb(255,0,0));
        }
        holder.txtChange.setText(dataList.get(position).getChange24h());
        holder.coverImage.setImageResource(R.drawable.chart_icon_sample_test);
        //Image
        PicassoClient.downloadImage(context,dataList.get(position).getImg(),holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<Rate> data) {
        tempList.clear();
        tempList.addAll(data);
        this.dataList = tempList;
        notifyDataSetChanged();
    }
}

 class PicassoClient {

    public static void downloadImage(Context c,String url,ImageView img)
    {
        if(url != null && url.length()>0) {
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder).into(img);
        }else{
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }

}

