package kr.hwanik.DaumSearchImage.dagger.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kr.hwanik.DaumSearchImage.MainActivity;
import kr.hwanik.DaumSearchImage.adapter.RecyclerViewAdapter;
import kr.hwanik.DaumSearchImage.presenter.MainContract;
import kr.hwanik.DaumSearchImage.presenter.MainPresenterImpl;

/**
 * Created by hwanik on 2017. 4. 18..
 */

@Module public class MainModule {

    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @Singleton
    RecyclerViewAdapter provideAdapter() {
        return new RecyclerViewAdapter(mainActivity);
    }

    @Provides
    MainContract.View provideView() {
        return mainActivity;
    }

    @Provides
    MainContract.Presenter providePresenter(MainPresenterImpl presenter) {
        return presenter;
    }
}
