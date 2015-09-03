package com.codepath.instagram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

/**
 * Created by yahuijin on 9/2/15.
 */
public class InstagramCommentFragment extends DialogFragment {

    public InstagramCommentFragment() {

    }

    public static InstagramCommentFragment newInstance(String title, int position) {
        InstagramCommentFragment frag = new InstagramCommentFragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("position", position);
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        int position = getArguments().getInt("position");

        //getDialog().setTitle(title);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);

        View view = inflater.inflate(R.layout.fragment_comments, container);
        ListView lvComments = (ListView)view.findViewById(R.id.lvComments);

        InstagramMainActivity activity = (InstagramMainActivity)getActivity();
        Instagram instagram = activity.instagrams.get(position);
        CommentsAdapter adapter = new CommentsAdapter(activity, instagram.commentCollection.comments);
        lvComments.setAdapter(adapter);


        return view;
    }
}
