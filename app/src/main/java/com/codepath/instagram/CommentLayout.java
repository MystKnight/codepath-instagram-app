package com.codepath.instagram;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yahuijin on 9/2/15.
 */
public class CommentLayout extends LinearLayout {

    TextView tvComment;

    public CommentLayout(Context context) {
        super(context);
        this.init();
    }

    public CommentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public CommentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CommentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    private void init() {
        inflate(getContext(), R.layout.comment_layout, this);
        this.tvComment = (TextView)this.findViewById(R.id.tvComment);
    }

    public void setCommentText(Spannable text, TextView.BufferType bufferType) {
        this.tvComment.setText(text, bufferType);
    }
}
