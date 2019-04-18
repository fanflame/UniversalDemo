package com.fanyiran.demo_baseadapter_lib;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanqiang on 2019/4/16.
 */
public abstract class RvBaseAdapter<T> extends RecyclerView.Adapter<RvViewHolder> {
    private List<T> baseDataList;
    private RvListener rvListener;
    private View.OnClickListener onClickListener;

    public RvBaseAdapter(List<T> baseDataList) {
        this.baseDataList = baseDataList;
        if (this.baseDataList == null) {
            this.baseDataList = new ArrayList<>();
        }
    }

    public void addItemType(ItemType itemType) {
        ItemManager.getInstance().addItems(itemType);
    }

    @Override
    public int getItemViewType(int position) {
        return ItemManager.getInstance().getType(baseDataList.get(position),position);
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layout = ItemManager.getInstance().getLayout(viewType);
        View inflate = View.inflate(viewGroup.getContext(), layout, null);
        return new RvViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder rvViewHolder, int position) {
        int viewType = getItemViewType(position);
        ItemType item = ItemManager.getInstance().getItemType(viewType);
        rvViewHolder.getItemView().setTag(R.id.baseapdater_tag_item_position,position);
        rvViewHolder.getItemView().setTag(R.id.baseapdater_tag_item_data,baseDataList.get(position));
        if (item.openClick()) {
            rvViewHolder.getItemView().setOnClickListener(getOnClickListener());
        } else {
            rvViewHolder.getItemView().setOnClickListener(null);
        }

        item.fillContent(rvViewHolder,position,baseDataList.get(position));
    }

    private View.OnClickListener getOnClickListener() {
        if (onClickListener == null) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag(R.id.baseapdater_tag_item_position);
                    T baseData = (T) v.getTag(R.id.baseapdater_tag_item_data);
                    if (rvListener != null) {
                        rvListener.onClick(baseData,position);
                    }
                }
            };
        }
        return onClickListener;
    }

    @Override
    public int getItemCount() {
        return baseDataList == null ? 0 : baseDataList.size();
    }

    public void setRvListener(RvListener rvListener) {
        this.rvListener = rvListener;
    }
}
