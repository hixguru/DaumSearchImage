package kr.hwanik.DaumSearchImage.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import kr.hwanik.DaumSearchImage.R;
import kr.hwanik.DaumSearchImage.adapter.model.AdapterModel;
import kr.hwanik.DaumSearchImage.adapter.view.AdapterView;
import kr.hwanik.DaumSearchImage.model.Item;
import kr.hwanik.DaumSearchImage.presenter.MainContract;

/**
 * Created by hwanik on 2017. 4. 10..
 */

public class RecyclerViewAdapter<T extends Item>
    extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
    implements AdapterModel<T>, AdapterView {

    private MainContract.View view;
    private List<T> items;

    @Inject
    public RecyclerViewAdapter(MainContract.View view) {
        this.view = view;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        holder.ivRetrievedImage.setImageURI(items.get(position)
            .getImage());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivRetrievedImage.setImageURI(items.get(position)
            .getImage());
        Log.e("tta", "onBindViewHolder: call22 ");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void updateItemList(List<T> newList) {
        final DaumDiffCallback diffCallback = new DaumDiffCallback((List<Item>) items, (List<Item>) newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        items.clear();
        items.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public void refresh() {
        view.hideKeyboard();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_retrieved_image) SimpleDraweeView ivRetrievedImage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
