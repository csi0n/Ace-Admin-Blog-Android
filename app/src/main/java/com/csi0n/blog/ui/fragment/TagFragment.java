package com.csi0n.blog.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csi0n.blog.R;
import com.csi0n.blog.business.pojo.model.Cate;
import com.csi0n.blog.business.pojo.model.Tag;
import com.csi0n.blog.business.pojo.response.GetTagIndexResponse;
import com.csi0n.blog.ui.base.common.FragmentLauncher;
import com.csi0n.blog.ui.base.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by csi0n on 10/24/16.
 */

public class TagFragment extends MvpFragment<TagPresenter, TagPresenter.ITag> implements TagPresenter.ITag {

    @Bind(R.id.tag_group)
    TagGroup tagGroup;

    Tag[] tags;

    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_tag;
    }

    @Override
    protected void afterMvpInit(View view, Bundle savedInstanceState) {
        super.afterMvpInit(view, savedInstanceState);
        tagGroup.setOnTagClickListener(onTagClickListener);
    }

    private TagGroup.OnTagClickListener onTagClickListener = new TagGroup.OnTagClickListener() {
        @Override
        public void onTagClick(String tag) {
            for (int i = 0; i < tags.length; i++) {
                if (tags[i].name.equals(tag)) {
                    startDetail(tags[i]);
                }
            }
        }
    };

    public void startDetail(Tag tag) {
        TagShowFragment.TagShowExtraParam extraParam = new TagShowFragment.TagShowExtraParam();
        extraParam.setFragmentClass(TagShowFragment.class);
        extraParam.tag = tag;
        FragmentLauncher.launch(mvpActivity, extraParam);
    }

    @Override
    public void onGetTag(GetTagIndexResponse getTagIndexResponse) {
        Tag[] tags = getTagIndexResponse.tags;
        this.tags = tags;
        if (tags != null && tags.length > 0) {
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < tags.length; i++) {
                strings.add(tags[i].name);
            }
            tagGroup.setTags(strings);
        }
    }
}
