package kr.hwanik.DaumSearchImage.dagger.module;

import dagger.Module;
import dagger.Provides;
import kr.hwanik.DaumSearchImage.MainActivity;
import kr.hwanik.DaumSearchImage.adapter.RecyclerViewAdapter;
import kr.hwanik.DaumSearchImage.adapter.model.AdapterModel;
import kr.hwanik.DaumSearchImage.adapter.view.AdapterView;
import kr.hwanik.DaumSearchImage.model.Item;
import kr.hwanik.DaumSearchImage.presenter.MainContract;
import kr.hwanik.DaumSearchImage.presenter.MainPresenterImpl;

/**
 * Created by hwanik on 2017. 4. 18..
 */

@Module
public class MainModule {

    private RecyclerViewAdapter<Item> adapter;
    private MainContract.View view;

    public MainModule(MainActivity mainActivity, RecyclerViewAdapter<Item> adapter) {
        this.adapter = adapter;
        this.view = mainActivity;
    }

    @Provides
    AdapterModel<Item> provideAdapterModel() {
        return adapter;
    }

    @Provides
    AdapterView provideAdapterView() {
        return adapter;
    }

    @Provides
    MainContract.View provideView() {
        return view;
    }

    @Provides
    MainContract.Presenter providePresenter(MainPresenterImpl presenter) {
        return presenter;
    }
}
