package me.peng.pengapp.common.net;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2017/8/5.
 */

public interface VolleyResponseCallback {
    void onSuccess(String response);
    void onError(VolleyError error);
}
