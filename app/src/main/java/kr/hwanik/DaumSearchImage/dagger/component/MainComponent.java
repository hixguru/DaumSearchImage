package kr.hwanik.DaumSearchImage.dagger.component;

import dagger.Component;
import kr.hwanik.DaumSearchImage.MainActivity;
import kr.hwanik.DaumSearchImage.dagger.module.MainModule;
import kr.hwanik.DaumSearchImage.dagger.scope.ActivityScope;

/**
 * Created by hwanik on 2017. 4. 18..
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
