package kr.hwanik.DaumSearchImage.dagger.component;

import dagger.Component;
import kr.hwanik.DaumSearchImage.dagger.module.AppModule;
import kr.hwanik.DaumSearchImage.network.DaumAPI;

/**
 * Created by hwanik on 2017. 4. 20..
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    DaumAPI daumApi();
}
