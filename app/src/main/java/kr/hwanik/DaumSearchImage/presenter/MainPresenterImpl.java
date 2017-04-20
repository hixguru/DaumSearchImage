package kr.hwanik.DaumSearchImage.presenter;

import android.util.Log;
import javax.inject.Inject;
import kr.hwanik.DaumSearchImage.adapter.RecyclerViewAdapter;
import kr.hwanik.DaumSearchImage.network.DaumAPI;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static kr.hwanik.DaumSearchImage.Util.Constants.DAUM_API_KEY;

/**
 * Created by hwanik on 2017. 4. 8..
 */

public class MainPresenterImpl implements MainContract.Presenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private final String JSON_TYPE_OUTPUT = "json";
    @Inject MainContract.View view;
    @Inject RecyclerViewAdapter adapter;
    @Inject DaumAPI api;

    @Inject
    public MainPresenterImpl(MainContract.View view, RecyclerViewAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
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

                adapter.addAll(response.channel.getItem());
                adapter.refresh();
            }, error -> {
                Log.e(TAG, "onInputChange: error > " + error.getMessage());
                view.showErrorOnSearch();
            });
    }
}
