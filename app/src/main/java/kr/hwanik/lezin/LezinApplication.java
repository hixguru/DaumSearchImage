package kr.hwanik.lezin;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by hwanik on 2017. 4. 10..
 */

public class LezinApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
