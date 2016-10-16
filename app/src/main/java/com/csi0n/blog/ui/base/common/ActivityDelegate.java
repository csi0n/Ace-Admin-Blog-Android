package com.csi0n.blog.ui.base.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.csi0n.blog.R;

import java.security.InvalidParameterException;


/**
 * CommonFragmentActivityDelegate <br/>
 * Created by xiaqiulei on 2015-04-03.
 */
public class ActivityDelegate<A extends ActionBarActivity, F extends Fragment> {

    static final int FRAGMENT_CONTAINER = R.id.fmFragmentContainer;

    protected A activity;
    private F commonFragment;
    private CommonExtraParam extraParam;

    public ActivityDelegate(A a) {
        if (a == null) {
            throw new InvalidParameterException("activity is null.");
        }
        activity = a;
    }

    public void beforeOnCreate(Bundle savedInstanceState) {

    }

    public void afterOnCreate(Bundle savedInstanceState) {
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        Intent it = activity.getIntent();
        Object obj = it.getSerializableExtra(ICommonFragment.EXTRA_REQ);
        if (validate(obj)) {
            extraParam = (CommonExtraParam) obj;
            if (savedInstanceState == null) {
                try {
                    commonFragment = (F) extraParam.getFragmentClass().newInstance();
                    activity.getSupportFragmentManager().beginTransaction().add(FRAGMENT_CONTAINER, commonFragment).commitAllowingStateLoss();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            activity.finish();
        }
    }

    private boolean validate(Object obj) {
        boolean ret = false;
        do {
            if (obj == null) {
                break;
            }
            if (!(obj instanceof CommonExtraParam)) {
                break;
            }
            CommonExtraParam param = (CommonExtraParam) obj;
            if (!param.validate()) {
                break;
            }
            ret = true;
        } while (false);
        return ret;
    }

    public F getCommonFragment() {
        return commonFragment;
    }

    public CommonExtraParam getExtraParam() {
        return extraParam;
    }
}