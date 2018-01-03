package com.viaplayapi.sample.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.viaplayapi.sample.R;
import com.viaplayapi.sample.ViaPlayApplication;
import com.viaplayapi.sample.data.Page;
import com.viaplayapi.sample.viewmodel.PageViewModel;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    MyRecyclerViewAdapter adapter;
    TextView titleTx;
    TextView descriptionTx;
    RecyclerView recyclerView;
    PageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        setTitle("Root Page");
        // set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        titleTx = findViewById(R.id.title);
        descriptionTx = findViewById(R.id.description);

        viewModel = ViaPlayApplication.obtainViewModel(this);
        subscribe();
        getRootPage();
    }

    private void updateUI(Page page) {
        if (page != null) {
            titleTx.setText(page.getTitle());
            descriptionTx.setText(page.getDescription());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecyclerViewAdapter(this, page.getLinks().sections);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(MainActivity.this, "There is issue with n/w connection, no offline data is availabel", Toast.LENGTH_LONG).show();
        }
    }

    private void subscribe() {
        final Observer<Page> pageObserver = new Observer<Page>() {
            @Override
            public void onChanged(@Nullable Page page) {
                Log.d(TAG, "On UI update");
                updateUI(page);
            }
        };
        viewModel.getPageMutableLiveData().observe(this, pageObserver);
    }

    public void getRootPage() {
        viewModel.getRootFromService();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, SectionPageActivity.class);
        intent.putExtra(SectionPageActivity.EXTRA_SECTION, viewModel.getPageMutableLiveData().getValue().getLinks().sections.get(position));
        startActivity(intent);
    }

}
