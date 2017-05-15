package kr.hwanik.DaumSearchImage.dagger.scope;

/**
 * Created by hwanik on 2017. 5. 15..
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
