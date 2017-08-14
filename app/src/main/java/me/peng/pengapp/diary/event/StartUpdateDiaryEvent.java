package me.peng.pengapp.diary.event;

import me.peng.pengapp.diary.bean.DiaryBean;

/**
 * Created by Administrator on 2017/8/12.
 */

public class StartUpdateDiaryEvent {

    private DiaryBean mDiaryBean;

    public StartUpdateDiaryEvent(DiaryBean diaryBean){
        mDiaryBean = diaryBean;
    }

    public DiaryBean getDiaryBean(){
        return mDiaryBean;
    }
}
