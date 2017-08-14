package me.peng.pengapp.duanzi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.peng.pengapp.R;
import me.peng.pengapp.common.net.VolleyHelper;
import me.peng.pengapp.common.net.VolleyResponseCallback;
import me.peng.pengapp.duanzi.api.DuanziApi;
import me.peng.pengapp.duanzi.bean.DuanziBean;
import me.peng.pengapp.duanzi.utils.GsonHelper;

/**
 * 段子的fragment
 * Created by Administrator on 2017/8/12.
 */

public class DuanziFragment extends Fragment {
    @BindView(R.id.duanzi_rv_show_duanzi)
    RecyclerView mRvShowDuanzi;
    @BindView(R.id.duanzi_refresh)
    SwipeRefreshLayout mRefresh;
    private Unbinder unbinder;

    public static DuanziFragment newInstance(){
        return new DuanziFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_duanzi, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initRefresh();
        return view;
    }

    private void initRefresh() {
        mRefresh.setColorSchemeResources(R.color.colorPrimary);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
                mRefresh.setRefreshing(false);
            }
        });
    }

    private void initView() {
        VolleyHelper.sendHttpGet(getActivity(), DuanziApi.GET_DUANZI, new VolleyResponseCallback() {
            @Override
            public void onSuccess(String response) {
                List<DuanziBean> mDuanziBeanList = GsonHelper.getDuanziBeanList(response);
                mDuanziBeanList.remove(3);
                mRvShowDuanzi.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRvShowDuanzi.setAdapter(new DuanziAdapter(DuanziFragment.this, mDuanziBeanList));
            }

            @Override
            public void onError(VolleyError error) {
                Logger.d(error);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
