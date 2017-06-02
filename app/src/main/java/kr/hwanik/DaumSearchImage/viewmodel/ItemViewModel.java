package kr.hwanik.DaumSearchImage.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import javax.inject.Inject;
import kr.hwanik.DaumSearchImage.model.Item;
import kr.hwanik.DaumSearchImage.repository.ItemRepository;

/**
 * Created by hwanik on 2017. 6. 1..
 */

public class ItemViewModel extends ViewModel {

    private ItemRepository itemRepository;

    @Inject
    public ItemViewModel(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public LiveData<List<Item>> getItems(String input) {
        return itemRepository.getItem(input);
    }
}
