package com.company.templateapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.templateapplication.adapter.DummyAdapter;
import com.company.templateapplication.common.Constant;
import com.company.templateapplication.entity.Dummy;
import com.company.templateapplication.viewModel.DummyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DummyViewModel dummyViewModel;

    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.dummy_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final DummyAdapter adapter = new DummyAdapter();
        recyclerView.setAdapter(adapter);

        dummyViewModel = ViewModelProviders.of(this).get(DummyViewModel.class);
        dummyViewModel.getAllDummies().observe(this, new Observer<List<Dummy>>() {
            @Override
            public void onChanged(List<Dummy> dummies) {
                adapter.submitList(dummies);
            }
        });

        final FloatingActionButton addButton = findViewById(R.id.fab_add_dummy);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = new Random().nextInt(1000);
                dummyViewModel.insert(new Dummy("Insert Name " + x, x));
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Dummy dummy = adapter.getDummy(viewHolder.getAdapterPosition());
                dummyViewModel.delete(dummy);
                Toast.makeText(MainActivity.this, dummy.getName() + "has been deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListerne(new DummyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Dummy dummy) {
                Toast.makeText(MainActivity.this, dummy.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, Constant.DOUBLE_BACK_DELAY_MS);
    }
}
