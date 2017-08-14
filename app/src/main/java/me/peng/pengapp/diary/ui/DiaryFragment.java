package me.peng.pengapp.diary.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.peng.pengapp.R;
import me.peng.pengapp.diary.bean.DiaryBean;
import me.peng.pengapp.diary.db.DiaryDatabaseHelper;
import me.peng.pengapp.diary.utils.GetDate;

/**
 * Created by Administrator on 2017/8/8.
 */

public class DiaryFragment extends Fragment {

    @BindView(R.id.main_iv_circle)
    ImageView mIvCircle;
    @BindView(R.id.main_tv_date)
    TextView mTvDate;
    @BindView(R.id.main_tv_content)
    TextView mTvContent;
    @BindView(R.id.item_ll_control)
    LinearLayout mLlControl;
    @BindView(R.id.item_first)
    LinearLayout mItemFirst;
    @BindView(R.id.main_rv_show_diary)
    RecyclerView mRvShowDiary;
    @BindView(R.id.main_ll_main)
    LinearLayout mLlMain;
    @BindView(R.id.main_fab_enter_edit)
    FloatingActionButton mFabEnterEdit;
    @BindView(R.id.main_rl_main)
    RelativeLayout mRlMain;

    private List<DiaryBean> mDiaryBeanList;
    private DiaryDatabaseHelper mDatabaseHelper;

    private Unbinder unbinder;

    private static final String DB_DIARY_NAME = "Diary.db";

    public static DiaryFragment newInstance(){
        return new DiaryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        unbinder = ButterKnife.bind(this, view);

        mDatabaseHelper = new DiaryDatabaseHelper(getActivity(), DB_DIARY_NAME, null, 1);
        getDiaryBeanList();
        initView();
        return view;
    }

    private void initView() {
        mTvDate.setText(GetDate.getDate());
        mRvShowDiary.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvShowDiary.setAdapter(new DiaryAdapter(getActivity(), mDiaryBeanList));
    }

    private void getDiaryBeanList() {
        mDiaryBeanList = new ArrayList<>();
        List<DiaryBean> diaryList = new ArrayList<>();
        SQLiteDatabase sqliteDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = sqliteDatabase.query("Diary", null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String dateSystem = GetDate.getDate().toString();
                if (date.equals(dateSystem)){
                    mLlMain.removeView(mItemFirst);
                }
                break;
            } while (cursor.moveToNext());
        }

        if (cursor.moveToFirst()){
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));
                mDiaryBeanList.add(new DiaryBean(date, title, content, tag));
            } while (cursor.moveToNext());
        }

        cursor.close();

        for (int i = mDiaryBeanList.size() - 1; i >= 0; i--){
            diaryList.add(mDiaryBeanList.get(i));
        }
        mDiaryBeanList = diaryList;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDiaryBeanList();
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.main_fab_enter_edit)
    public void onViewClicked(){
        AddDiaryActivity.startActivity(getActivity());
    }
}
