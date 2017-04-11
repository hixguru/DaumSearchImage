package kr.hwanik.lezin.presenter;

import kr.hwanik.lezin.adapter.RecyclerViewAdapter;
import kr.hwanik.lezin.network.DaumAPI;
import kr.hwanik.lezin.network.RetrofitCreator;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static kr.hwanik.lezin.Util.Constants.DAUM_API_KEY;

/**
 * Created by hwanik on 2017. 4. 8..
 */

public class MainPresenterImpl implements MainContract.Presenter {

    private final String JSON_TYPE_OUTPUT = "json";
    private MainContract.View view;
    private RecyclerViewAdapter adapter;

    public MainPresenterImpl(MainContract.View view, RecyclerViewAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    @Override
    public void onInputChange(CharSequence input) {
        Retrofit retrofit = RetrofitCreator.create();
        DaumAPI api = retrofit.create(DaumAPI.class);

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
            }, error -> view.showErrorOnSearch());
    }
}
