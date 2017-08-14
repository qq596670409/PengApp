package me.peng.pengapp.meizi.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.peng.pengapp.R;
import me.peng.pengapp.common.utils.Check;
import me.peng.pengapp.common.view.CommonPagerAdapter;
import me.peng.pengapp.common.view.GlideHelper;

/**
 * 查看大图的Activity
 * Created by Administrator on 2017/8/13.
 */

public class DetailActivity extends AppCompatActivity{
    private List<String> mImageUrlList;
    private List<Fragment> mFragments;
    private List<String> mCachePathList;

    private static final String IMAGE_URL_LIST = "imageUrlList";
    private static final String POSITION = "position";

    @BindView(R.id.detail_vp_show_photo)
    ViewPager mVpShowPhoto;

    public static void startActivity(Context context, ArrayList<String> imageUrlList, int position){
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IMAGE_URL_LIST, imageUrlList);
        bundle.putInt(POSITION, position);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        mImageUrlList = new ArrayList<>();
        mFragments = new ArrayList<>();
        mCachePathList = new ArrayList<>();
        int position = getIntent().getIntExtra(POSITION, -1);
        mImageUrlList = getIntent().getStringArrayListExtra(IMAGE_URL_LIST);
        initViewWithCache(position);
    }

    private void initViewWithCache(final int position) {
        Task.call(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                for (String imageUrl : mImageUrlList){
                    mCachePathList.add(GlideHelper.getImagePathFromCache(imageUrl, DetailActivity.this));
                }
                return mCachePathList;
            }
        },Task.BACKGROUND_EXECUTOR).continueWith(new Continuation<List<String>, Object>() {
            @Override
            public Object then(Task<List<String>> task) throws Exception {
                List<String> mCachePathList = task.getResult();
                if (!Check.isEmpty(mCachePathList)){
                    for (String cachePath : mCachePathList){
                        DetailFragment fragment = DetailFragment.newInstance(cachePath);
                        mFragments.add(fragment);
                    }
                    CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments);
                    mVpShowPhoto.setAdapter(adapter);
                    mVpShowPhoto.setCurrentItem(position);
                }
                return null;
            }
        },Task.UI_THREAD_EXECUTOR);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
