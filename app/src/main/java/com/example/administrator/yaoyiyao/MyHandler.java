package com.example.administrator.yaoyiyao;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.lang.ref.WeakReference;

import static com.example.administrator.yaoyiyao.MainActivity.AGAIN_SHAKE;
import static com.example.administrator.yaoyiyao.MainActivity.END_SHAKE;
import static com.example.administrator.yaoyiyao.MainActivity.START_SHAKE;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class MyHandler extends Handler {
    private WeakReference<MainActivity> mReference;
    private MainActivity mActivity;
    public MyHandler(MainActivity activity) {
        mReference = new WeakReference<MainActivity>(activity);
        if (mReference != null) {
            mActivity = mReference.get();
        }
    }
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case START_SHAKE:
                //This method requires the caller to hold the permission VIBRATE.
                mActivity.mVibrator.vibrate(300);
                //发出提示音
                mActivity.mSoundPool.play(mActivity.mWeiChatAudio, 1, 1, 0, 0, 1);
                mActivity.mTopLine.setVisibility(View.VISIBLE);
                mActivity.mBottomLine.setVisibility(View.VISIBLE);
                mActivity.startAnimation(false);//参数含义: (不是回来) 也就是说两张图片分散开的动画
                break;
            case AGAIN_SHAKE:
                mActivity.mVibrator.vibrate(300);
                break;
            case END_SHAKE:
                //整体效果结束, 将震动设置为false
                mActivity.isShake = false;
                // 展示上下两种图片回来的效果
                mActivity.startAnimation(true);
                break;
        }
    }

}
