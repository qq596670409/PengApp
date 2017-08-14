package me.peng.pengapp.meizi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.peng.pengapp.R;
import me.peng.pengapp.common.net.VolleyHelper;
import me.peng.pengapp.common.net.VolleyResponseCallback;
import me.peng.pengapp.meizi.api.MeiziApi;
import me.peng.pengapp.meizi.bean.MeiziBean;
import me.peng.pengapp.meizi.utils.GsonHelper;

/**
 * Created by Administrator on 2017/8/13.
 */

public class MeiziFragment extends Fragment {

    @BindView(R.id.meizi_rv_show_meizi)
    RecyclerView mRvShowMeizi;
    @BindView(R.id.meizi_refresh)
    SwipeRefreshLayout mRefresh;

    List<MeiziBean> meiziBeanList = new ArrayList<>();
    private static String response = "";

    private Unbinder unbinder;

    public static MeiziFragment newInstance() {
        return new MeiziFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        refreshMeizi();
        return view;
    }

    private void initView() {

        VolleyHelper.sendHttpGet(getActivity(), MeiziApi.getMeiziApi(), new VolleyResponseCallback() {
            @Override
            public void onSuccess(String s) {
                response = s;
                meiziBeanList = GsonHelper.getMeiziBean(response);
                mRvShowMeizi.setLayoutManager(new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL));
                Collections.shuffle(meiziBeanList);
                mRvShowMeizi.setAdapter(new MeiziAdapter(meiziBeanList, MeiziFragment.this));
            }

            @Override
            public void onError(VolleyError error) {
                Logger.d(error);
            }
        });
    }

    private void refreshMeizi() {
        mRefresh.setColorSchemeResources(R.color.colorPrimary);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
                mRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
