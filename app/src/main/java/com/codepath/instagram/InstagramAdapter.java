package com.codepath.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codepath.instagram.Comment;
import com.codepath.instagram.CommentLayout;
import com.codepath.instagram.Instagram;
import com.codepath.instagram.R;
import com.codepath.instagram.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yahuijin on 8/31/15.
 */
public class InstagramAdapter extends ArrayAdapter<Instagram> {

    public InstagramAdapter(Context context, List<Instagram> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Instagram instagram = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.instagram_row, parent, false);
        }

        ImageView avatar = (ImageView)convertView.findViewById(R.id.ivAvatar);
        Picasso.with(getContext()).load(instagram.author.avatarUrl).into(avatar);

        ImageView photo = (ImageView)convertView.findViewById(R.id.ivPhoto);
        Picasso.with(getContext()).load(instagram.photoUrl).placeholder(R.drawable.instagram_24dp).into(photo);

        TextView authorName = (TextView)convertView.findViewById(R.id.tvAuthorName);
        authorName.setText(instagram.author.name);

        TextView date = (TextView)convertView.findViewById(R.id.tvDate);
        date.setText(Utils.getSmartDate(instagram.date));

        TextView message = (TextView)convertView.findViewById(R.id.tvMessage);
        if (instagram.message != null) {
            message.setText(instagram.message);
        } else {
            message.setVisibility(View.GONE);
        }

        // Dynamically create our comments
        LinearLayout rl = (LinearLayout)convertView.findViewById(R.id.blah);
        rl.removeAllViews();

        TextView tvViewAllComments = (TextView)convertView.findViewById(R.id.tvViewAllComments);
        int commentCount = instagram.commentCollection.comments.size();
        if (commentCount > 0) {
            // Dynamically create the last 2 comments
            for (int i = 1; i <= 2; i++) {
                CommentLayout cl = new CommentLayout(getContext());
                int lastIndex = instagram.commentCollection.comments.size() - i;
                Comment comment = instagram.commentCollection.comments.get(lastIndex);
                cl.setCommentText(comment.getSpannableComment(), TextView.BufferType.SPANNABLE);
                rl.addView(cl);
            }

            tvViewAllComments.setText(String.format("View all %d comments", instagram.commentCount));
        } else {
            tvViewAllComments.setVisibility(View.GONE);
        }

        TextView tvLikes = (TextView)convertView.findViewById(R.id.tvLikes);
        tvLikes.setText(instagram.getLikeCountDisplay());

        return convertView;
    }
}
