package me.peng.pengapp.meizi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.peng.pengapp.R;

/**
 * Created by Administrator on 2017/8/13.
 */

public class DetailFragment extends Fragment{

    public static final String IMAGE_URL = "imageUrl";

    @BindView(R.id.detail_pv_show_photo)
    PhotoView mPvShowPhoto;

    private Unbinder mUnbinder;

    public static DetailFragment newInstance(String imageUrl){
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        String imageUrl = bundle.getString(IMAGE_URL);
        Glide.with(this).load(imageUrl).into(mPvShowPhoto);
        mPvShowPhoto.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
