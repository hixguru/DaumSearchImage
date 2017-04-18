package kr.hwanik.DaumSearchImage.presenter;

/**
 * Created by hwanik on 2017. 4. 8..
 */

public interface MainContract {

    interface Presenter {

        void onInputChange(CharSequence input);

    }

    interface View {

        void showErrorOnSearch();

        void showNoResult();

        void hideKeyboard();

    }
}
