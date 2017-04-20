package kr.hwanik.DaumSearchImage;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import kr.hwanik.DaumSearchImage.dagger.component.AppComponent;
import kr.hwanik.DaumSearchImage.dagger.component.DaggerAppComponent;
import kr.hwanik.DaumSearchImage.dagger.module.AppModule;

/**
 * Created by hwanik on 2017. 4. 10..
 */

public class MyApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        component = DaggerAppComponent.builder()
            .appModule(new AppModule())
            .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
