package kr.hwanik.DaumSearchImage.adapter;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.ArrayList;
import java.util.List;
import kr.hwanik.DaumSearchImage.R;
import kr.hwanik.DaumSearchImage.databinding.ImageListItemBinding;
import kr.hwanik.DaumSearchImage.model.Item;

/**
 * Created by hwanik on 2017. 4. 10..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Item> items;

    public RecyclerViewAdapter() {
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
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<Item> addedItems) {
        items.clear();
        items.addAll(addedItems);
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageListItemBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public void bind(Item item) {
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getImage()))
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();

            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(binding.ivRetrievedImage.getController())
                .setImageRequest(request)
                .build();
            binding.ivRetrievedImage.setController(controller);
            binding.setUser(item);
        }
    }
}
