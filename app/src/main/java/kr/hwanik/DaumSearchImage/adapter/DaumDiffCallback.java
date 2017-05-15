package kr.hwanik.DaumSearchImage.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import java.util.List;
import kr.hwanik.DaumSearchImage.model.Item;

/**
 * Created by hwanik on 2017. 5. 15..
 */

public class DaumDiffCallback extends DiffUtil.Callback {

    private final List<Item> oldItemList;
    private final List<Item> newItemList;

    public DaumDiffCallback(List<Item> oldItemList, List<Item> newItemList) {
        this.oldItemList = oldItemList;
        this.newItemList = newItemList;
    }

    @Override
    public int getOldListSize() {
        return oldItemList.size();
    }

    @Override
    public int getNewListSize() {
        return newItemList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemList.get(oldItemPosition).getLink()
                            .equals(newItemList.get(newItemPosition).getLink());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Item oldItem = oldItemList.get(oldItemPosition);
        Item newItem = newItemList.get(newItemPosition);
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
