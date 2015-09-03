package com.codepath.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yahuijin on 9/2/15.
 */
public class CommentsAdapter extends ArrayAdapter<Comment> {

    public CommentsAdapter(Context context, List<Comment> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_row, parent, false);
        }

        TextView tvComment = (TextView)convertView.findViewById(R.id.tvComment);
        tvComment.setText(comment.message);

        TextView tvAuthor = (TextView)convertView.findViewById(R.id.tvAuthorName);
        tvAuthor.setText(comment.getSpannableName(), TextView.BufferType.SPANNABLE);

        TextView tvDate = (TextView)convertView.findViewById(R.id.tvDate);
        tvDate.setText(Utils.getSmartDate(comment.date));

        ImageView ivAvater = (ImageView)convertView.findViewById(R.id.ivAvatar);
        Picasso.with(getContext()).load(comment.avatarUrl).into(ivAvater);

        return convertView;
    }
}
