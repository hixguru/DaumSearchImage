package kr.hwanik.DaumSearchImage.presenter;

import android.util.Log;
import javax.inject.Inject;
import kr.hwanik.DaumSearchImage.adapter.model.AdapterModel;
import kr.hwanik.DaumSearchImage.adapter.view.AdapterView;
import kr.hwanik.DaumSearchImage.model.Item;
import kr.hwanik.DaumSearchImage.network.DaumAPI;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static kr.hwanik.DaumSearchImage.util.Constants.DAUM_API_KEY;

/**
 * Created by hwanik on 2017. 4. 8..
 */

public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private final String JSON_TYPE_OUTPUT = "json";
    private MainPresenter.View view;
    private AdapterModel<Item> adapterModel;
    private AdapterView adapterView;
    @Inject DaumAPI api;

    @Inject
    public MainPresenterImpl(MainPresenter.View view, AdapterModel<Item> adapterModel, AdapterView adapterView) {
        this.view = view;
        this.adapterModel = adapterModel;
        this.adapterView = adapterView;
    }

    @Override
    public void onInputChange(CharSequence input) {
        api.getImages(DAUM_API_KEY, input, JSON_TYPE_OUTPUT)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(response -> {
                boolean isNoResult = response.channel.getResult().equals("0")
                    || response.channel.getTotalCount() == 0;

                if (isNoResult) {
                    view.showNoResult();
                    return;
                }

                adapterModel.addAll(response.channel.getItem());
                adapterView.refresh();
                view.scrollTop();
            }, error -> {
                Log.e(TAG, "onInputChange: error > " + error.getMessage());
                view.showErrorOnSearch();
            });
    }
}
