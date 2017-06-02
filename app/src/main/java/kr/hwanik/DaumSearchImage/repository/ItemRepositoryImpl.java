package kr.hwanik.DaumSearchImage.repository;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import javax.inject.Inject;
import kr.hwanik.DaumSearchImage.model.Item;
import kr.hwanik.DaumSearchImage.network.DaumAPI;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static kr.hwanik.DaumSearchImage.util.Constants.DAUM_API_KEY;
import static kr.hwanik.DaumSearchImage.util.Constants.JSON_TYPE_OUTPUT;

/**
 * Created by hwanik on 2017. 6. 1..
 */

public class ItemRepositoryImpl implements ItemRepository {

    private DaumAPI api;
    private final MutableLiveData<List<Item>> liveData;

    @Inject
    public ItemRepositoryImpl(DaumAPI api) {
        this.api = api;
        liveData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<List<Item>> getItem(String input) {
        api.getImages(DAUM_API_KEY, input, JSON_TYPE_OUTPUT)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(response -> liveData.setValue(response.channel.getItem()),
                Throwable::printStackTrace);

        return liveData;
    }
}
