package kr.hwanik.lezin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import kr.hwanik.lezin.adapter.RecyclerViewAdapter;
import kr.hwanik.lezin.presenter.MainContract;
import kr.hwanik.lezin.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.et_input) EditText etInput;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private MainContract.Presenter presenter;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        adapter = new RecyclerViewAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        presenter = new MainPresenterImpl(this, adapter);

        RxTextView.textChanges(etInput)
            .throttleWithTimeout(1000, TimeUnit.MILLISECONDS)
            .filter(input -> input.length() >= 2)
            .subscribe(
                input -> presenter.onInputChange(input),
                error -> showErrorOnSearch()
            );
    }

    @Override
    public void showErrorOnSearch() {
        Toast.makeText(this, R.string.error_on_search, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoResult() {
        Toast.makeText(this, R.string.no_result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
