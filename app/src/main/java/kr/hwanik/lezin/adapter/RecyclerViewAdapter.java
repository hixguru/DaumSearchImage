package kr.hwanik.lezin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import kr.hwanik.lezin.R;
import kr.hwanik.lezin.adapter.model.AdapterModel;
import kr.hwanik.lezin.adapter.view.AdapterView;
import kr.hwanik.lezin.model.Item;
import kr.hwanik.lezin.presenter.MainContract;

/**
 * Created by hwanik on 2017. 4. 10..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
    implements AdapterModel, AdapterView {

    private MainContract.View view;
    private List<Item> items;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivRetrievedImage
            .setImageURI(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void addAll(List<Item> addedItems) {
        items.clear();
        items.addAll(addedItems);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
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
