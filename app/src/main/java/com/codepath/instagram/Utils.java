package com.codepath.instagram;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by yahuijin on 9/2/15.
 */
public class Utils {

    private static final int SPANNABLE_COLOR = Color.parseColor("#125688");

    public static Spannable getSpannable(String fullString, int highlightLength) {
        Spannable spannable = new SpannableString(fullString);
        spannable.setSpan(
                new ForegroundColorSpan(SPANNABLE_COLOR),
                0,
                highlightLength,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return spannable;
    }

    public static String getSmartDate(String since1970) {
        String smartDate;
        Date date = new Date(Long.parseLong(since1970) * 1000);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTime(date);

        Calendar calendarNow = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendarNow.setTime(new Date());

        long timeDiff = calendar.getTime().getTime() - calendarNow.getTime().getTime();
        long timeDiffSecs = timeDiff / 1000;
        long timeDiffMins = timeDiff / (60 * 1000);
        long timeDiffHours = timeDiff / (60 * 60 * 1000);
        long timeDiffDays = timeDiff / (60 * 60 * 1000 * 24);
        long timeDiffWeeks = timeDiff / (60 * 60 * 1000 * 24 * 7);

        // If the post is less than a minute, show now
        if (timeDiffSecs < 60) {
            smartDate = "Now";
        }
        // If the post is less than 60 mins old, show in minutes
        else if (timeDiffMins < 60) {
            smartDate = String.format("%dm", timeDiffMins);
        }
        // If the post is less than 24 hours old, show in hours
        else if (timeDiffHours < 24) {
            smartDate = String.format("%dh", timeDiffHours);
        }
        // If the post is less than 7 days old, show in days
        else if (timeDiffDays < 7) {
            smartDate = String.format("%dd", timeDiffDays);
        }
        // Otherwise, show in weeks
        else {
            smartDate = String.format("%dw", timeDiffWeeks);
        }

        return smartDate;
    }
}
