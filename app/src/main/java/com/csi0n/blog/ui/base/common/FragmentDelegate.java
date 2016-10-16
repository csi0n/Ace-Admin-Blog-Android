package com.csi0n.blog.ui.base.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.security.InvalidParameterException;


/**
 * Created by xiaqiulei on 14/11/20.
 */
public class FragmentDelegate<F extends Fragment, P extends CommonExtraParam> {


    protected P extraReqParam;
    private F fragment;

    public FragmentDelegate(F f) {
        if (f == null) {
            throw new InvalidParameterException("fragment is null.");
        }
        this.fragment = f;
    }

    public void beforeOnViewCreated(View view, Bundle savedInstanceState) {

    }

    public void afterOnViewCreated(View view, Bundle savedInstanceState) {


        extraReqParam = CommonExtraParam.getReqExtraParam(fragment.getActivity());

        String content = String.format("fragment = %s, extraReqParam = %s", fragment, extraReqParam);

    }

    public P getReqExtraParam() {
        return extraReqParam;
    }

    public void setSuccessResult(CommonExtraParam extraParam) {
        Intent it = new Intent();
        it.putExtra(ICommonFragment.EXTRA_RESP, extraParam);
        fragment.getActivity().setResult(Activity.RESULT_OK, it);
        fragment.getActivity().finish();
    }
}