package kr.hwanik.DaumSearchImage.presenter;

/**
 * Created by hwanik on 2017. 4. 8..
 */

public interface MainPresenter {

    void onInputChange(CharSequence input);

    interface View {

        void showErrorOnSearch();

        void showNoResult();

        void hideKeyboard();

        void scrollTop();
    }
}
