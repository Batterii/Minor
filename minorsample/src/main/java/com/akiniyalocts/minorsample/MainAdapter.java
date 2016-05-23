package com.akiniyalocts.minorsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonykiniyalocts on 5/23/16.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private final LayoutInflater inflater;

    private final List<String> items;

    public MainAdapter(Context context){
        inflater = LayoutInflater.from(context);
        items = new ArrayList<>();

        addItems();
    }

    private void addItems() {
        for(int i = 0; i <= 100; i++){
            items.add(String.format("I am item number %d", i));
        }
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(inflater.inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MainViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.main_text);
        }
    }
}
