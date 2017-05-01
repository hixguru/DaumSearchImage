package kr.hwanik.DaumSearchImage.dagger.component;

import dagger.Component;
import kr.hwanik.DaumSearchImage.dagger.module.AppModule;
import kr.hwanik.DaumSearchImage.network.DaumAPI;

/**
 * Created by hwanik on 2017. 4. 20..
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    DaumAPI api();  // dependencies에서 의존하고 있는 다른 Component들에게 명시된 인스턴스만 사용가능하게 할 수 있다.
}
