package com.n16dccn159.admin.view.textlink;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomTextViewLink extends AppCompatTextView {
    private static final int MIN_PHONE_NUMBER_LENGTH = 8;

    private AutoLinkOnClickListener autoLinkOnClickListener;

    private AutoLinkMode[] autoLinkModes;

    private boolean isUnderLineEnabled = false;

    public CustomTextViewLink(Context context) {
        super(context);
    }

    public CustomTextViewLink(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setHighlightColor(int color) {
        super.setHighlightColor(Color.TRANSPARENT);
    }

    public void setAutoLinkText(String text) {
        SpannableString spannableString = makeSpannableString(text);
        setText(spannableString);
        setMovementMethod(new LinkTouchMovementMethod());
    }

    private SpannableString makeSpannableString(String text) {

        final SpannableString spannableString = new SpannableString(text);

        List<AutoLinkItem> autoLinkItems = matchedRanges(text);

        for (final AutoLinkItem autoLinkItem : autoLinkItems) {
            int currentColor = autoLinkItem.getAutoLinkMode().getColor();
            int currentColorSelect = autoLinkItem.getAutoLinkMode().getColorSelect();

            TouchableSpan clickableSpan = new TouchableSpan(currentColor, currentColorSelect, isUnderLineEnabled) {
                @Override
                public void onClick(View widget) {
                    if (autoLinkOnClickListener != null) {
                        autoLinkOnClickListener.onAutoLinkTextClick(autoLinkItem.getAutoLinkMode(),
                                autoLinkItem.getMatchedText());
                    }
                }
            };

            spannableString.setSpan(clickableSpan, autoLinkItem.getStartPoint(), autoLinkItem.getEndPoint(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

    private List<AutoLinkItem> matchedRanges(String text) {

        List<AutoLinkItem> autoLinkItems = new LinkedList<>();

        if (autoLinkModes == null) {
            throw new NullPointerException("Please add at least one mode");
        }

        for (AutoLinkMode anAutoLinkMode : autoLinkModes) {
            Pattern pattern = anAutoLinkMode.getPattern();
            Matcher matcher = pattern.matcher(text);

            if (anAutoLinkMode.getName().equals(AutoLinkMode.MODE_PHONE)) {
                while (matcher.find()) {
                    if (matcher.group().length() > MIN_PHONE_NUMBER_LENGTH) {
                        autoLinkItems.add(
                                new AutoLinkItem(matcher.start(), matcher.end(), matcher.group(), anAutoLinkMode));
                    }
                }
            } else {
                while (matcher.find()) {
                    autoLinkItems.add(
                            new AutoLinkItem(matcher.start(), matcher.end(), matcher.group(), anAutoLinkMode));
                }
            }
        }

        return autoLinkItems;
    }

    public void addAutoLinkMode(AutoLinkMode... autoLinkModes) {
        this.autoLinkModes = autoLinkModes;
    }

    public void enableUnderLine() {
        isUnderLineEnabled = true;
    }

    public void setAutoLinkOnClickListener(AutoLinkOnClickListener autoLinkOnClickListener) {
        this.autoLinkOnClickListener = autoLinkOnClickListener;
    }

    public interface AutoLinkOnClickListener {
        void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText);
    }
}