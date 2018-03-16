package com.solarexsoft.transitiondemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yanzhenjie.permission.AndPermission;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    RecyclerView rv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndPermission.with(this)
                .permission(Constants.PERMISSIONS)
                .start();
        rv_main = findViewById(R.id.rv_main);
        List<String> urls = Arrays.asList(Constants.imageThumbUrls);
        RVAdapter adapter = new RVAdapter(this, urls);
        rv_main.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_main.setAdapter(adapter);
        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String url) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(Constants.KEY_URL, url);
                intent.putExtra(Constants.KEY_POSITION, position);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, new Pair<View,String>(view, "transition:image"));
                MainActivity.this.startActivity(intent, options.toBundle());
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideBottomUIMenu(this);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        int position = data.getIntExtra(Constants.KEY_POSITION, 0);
        rv_main.smoothScrollToPosition(position);
    }
}
