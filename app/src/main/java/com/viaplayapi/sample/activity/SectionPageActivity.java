package com.viaplayapi.sample.activity;

import android.arch.lifecycle.Observer;
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
import com.viaplayapi.sample.data.Section;
import com.viaplayapi.sample.viewmodel.PageViewModel;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class SectionPageActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    public static final String EXTRA_SECTION = "EXTRA_SECTION";
    private static final String TAG = SectionPageActivity.class.getSimpleName();
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
        setTitle("Section Page");

        // set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        titleTx = findViewById(R.id.title);

        descriptionTx = findViewById(R.id.description);

        viewModel = ViaPlayApplication.obtainViewModel(this);
        subscribe();
        getSectionPage(((Section) getIntent().getSerializableExtra(EXTRA_SECTION)));
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
            Toast.makeText(SectionPageActivity.this, "There is issue with n/w connection, no offline data is availabel", Toast.LENGTH_LONG).show();
        }
    }

    public void getSectionPage(Section section) {

        viewModel.getSectionPageFromService(section);
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

    @Override
    public void onItemClick(View view, int position) {
        getSectionPage(viewModel.getPageMutableLiveData().getValue().getLinks().sections.get(position));
    }
}

