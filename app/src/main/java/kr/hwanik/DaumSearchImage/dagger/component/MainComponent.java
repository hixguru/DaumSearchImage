package kr.hwanik.DaumSearchImage.dagger.component;

import dagger.Component;
import javax.inject.Singleton;
import kr.hwanik.DaumSearchImage.MainActivity;
import kr.hwanik.DaumSearchImage.dagger.module.MainModule;

/**
 * Created by hwanik on 2017. 4. 18..
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
