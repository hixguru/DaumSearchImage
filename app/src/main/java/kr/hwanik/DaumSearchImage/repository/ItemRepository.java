package kr.hwanik.DaumSearchImage.repository;

import android.arch.lifecycle.LiveData;
import java.util.List;
import kr.hwanik.DaumSearchImage.model.Item;

/**
 * Created by hwanik on 2017. 6. 1..
 */

public interface ItemRepository {

    LiveData<List<Item>> getItem(String input);

}
