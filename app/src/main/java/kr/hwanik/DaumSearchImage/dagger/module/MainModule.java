package kr.hwanik.DaumSearchImage.dagger.module;

import dagger.Module;
import dagger.Provides;
import kr.hwanik.DaumSearchImage.MainActivity;
import kr.hwanik.DaumSearchImage.adapter.RecyclerViewAdapter;
import kr.hwanik.DaumSearchImage.adapter.model.AdapterModel;
import kr.hwanik.DaumSearchImage.adapter.view.AdapterView;
import kr.hwanik.DaumSearchImage.presenter.MainPresenter;
import kr.hwanik.DaumSearchImage.presenter.MainPresenterImpl;

/**
 * Created by hwanik on 2017. 4. 18..
 */

@Module
public class MainModule {

    private RecyclerViewAdapter adapter;
    private MainPresenter.View view;

    public MainModule(MainActivity mainActivity, RecyclerViewAdapter adapter) {
        this.adapter = adapter;
        this.view = mainActivity;
    }

    @Provides
    AdapterModel provideAdapterModel() {
        return adapter;
    }

    @Provides
    AdapterView provideAdapterView() {
        return adapter;
    }

    @Provides
    MainPresenter.View provideView() {
        return view;
    }

    @Provides
    MainPresenter providePresenter(MainPresenterImpl presenter) {
        return presenter;
    }
}
