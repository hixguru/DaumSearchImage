package kr.hwanik.DaumSearchImage.dagger.module;

import dagger.Module;
import dagger.Provides;
import kr.hwanik.DaumSearchImage.dagger.scope.ActivityScope;
import kr.hwanik.DaumSearchImage.network.DaumAPI;
import kr.hwanik.DaumSearchImage.repository.ItemRepository;
import kr.hwanik.DaumSearchImage.repository.ItemRepositoryImpl;
import kr.hwanik.DaumSearchImage.viewmodel.ItemViewModel;

/**
 * Created by hwanik on 2017. 4. 18..
 */

@Module
public class MainModule {

    @Provides
    @ActivityScope
    ItemRepository provideItemRepository(DaumAPI api) {
        return new ItemRepositoryImpl(api);
    }

    @Provides
    @ActivityScope
    ItemViewModel provideViewModel(ItemRepository repository) {
        return new ItemViewModel(repository);
    }
}
