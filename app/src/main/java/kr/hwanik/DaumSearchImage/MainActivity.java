package kr.hwanik.DaumSearchImage;

import android.arch.lifecycle.LifecycleActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding.widget.RxTextView;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kr.hwanik.DaumSearchImage.adapter.RecyclerViewAdapter;
import kr.hwanik.DaumSearchImage.dagger.component.DaggerMainComponent;
import kr.hwanik.DaumSearchImage.dagger.module.MainModule;
import kr.hwanik.DaumSearchImage.viewmodel.ItemViewModel;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends LifecycleActivity {

    @BindView(R.id.et_input) EditText etInput;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;
    @Inject ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerMainComponent.builder()
            .appComponent(((MyApplication) getApplicationContext()).getComponent())
            .mainModule(new MainModule())
            .build()
            .inject(this);

        adapter = new RecyclerViewAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        RxTextView.textChanges(etInput)
            .throttleWithTimeout(1000, TimeUnit.MILLISECONDS)
            .filter(input -> input.length() >= 2)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(input -> {
                itemViewModel.getItems(input.toString())
                    .observe(this, items -> {
                        if (items != null && items.size() == 0) {
                            showNoResult();
                            return;
                        }

                        adapter.addAll(items);
                        adapter.refresh();
                        hideKeyboard();
                        scrollTop();
                    });
            }, error -> showErrorOnSearch());
    }

    public void showErrorOnSearch() {
        Toast.makeText(this, R.string.error_on_search, Toast.LENGTH_SHORT)
            .show();
    }

    public void showNoResult() {
        Toast.makeText(this, R.string.no_result, Toast.LENGTH_SHORT)
            .show();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void scrollTop() {
        recyclerView.smoothScrollToPosition(0);
    }
}
