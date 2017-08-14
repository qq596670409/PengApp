package me.peng.pengapp.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import me.peng.pengapp.R;

/**
 * Created by Administrator on 2017/8/3.
 */

public class TestActivity extends AppCompatActivity {
    private static final String IMAGE_URL = "imageUrl";
    public static void startActivity(Context context, String imageUrl, int position){
        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra(IMAGE_URL, imageUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        ImageView mIvVideo = (ImageView) findViewById(R.id.test_iv_video);
        Glide.with(this)
                .load("http://p3.pstatp.com/large/1fd90000bdf1766aaa29")
                .into(mIvVideo);
    }
}
