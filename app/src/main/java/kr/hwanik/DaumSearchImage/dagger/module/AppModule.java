package kr.hwanik.DaumSearchImage.dagger.module;

import dagger.Module;
import dagger.Provides;
import kr.hwanik.DaumSearchImage.dagger.scope.AppScope;
import kr.hwanik.DaumSearchImage.network.DaumAPI;
import kr.hwanik.DaumSearchImage.network.RetrofitCreator;
import retrofit2.Retrofit;

/**
 * Created by hwanik on 2017. 4. 20..
 */

@Module
public class AppModule {

    @Provides
    @AppScope
    DaumAPI provideApi(Retrofit retrofit) {
        return retrofit.create(DaumAPI.class);
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit() {
        return RetrofitCreator.create();
    }
}
