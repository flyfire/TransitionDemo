package com.solarexsoft.transitiondemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by houruhou on 15/03/2018.
 */

public class DetailActivity extends Activity {
    ImageView iv;
    int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        iv = findViewById(R.id.iv);
        Intent intent = getIntent();
        String url = intent.getStringExtra(Constants.KEY_URL);
        position = intent.getIntExtra(Constants.KEY_POSITION, 0);
        Glide.with(this).load(url).into(iv);
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        //getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.fade));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideBottomUIMenu(this);
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_POSITION, position);
        setResult(RESULT_OK, intent);
        super.finishAfterTransition();
    }
}
