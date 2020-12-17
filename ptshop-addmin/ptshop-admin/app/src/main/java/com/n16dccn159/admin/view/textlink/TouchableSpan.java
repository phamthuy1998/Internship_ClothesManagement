package com.n16dccn159.admin.view.textlink;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

abstract class TouchableSpan extends ClickableSpan {
    
    // Status link
    private boolean isPressed;
    // Color of link 
    private int normalTextColor;
    // Color of link when user click
    private int pressedTextColor;
    private boolean isUnderLineEnabled;

    TouchableSpan(int normalTextColor, int pressedTextColor, boolean isUnderLineEnabled) {
        this.normalTextColor = normalTextColor;
        this.pressedTextColor = pressedTextColor;
        this.isUnderLineEnabled = isUnderLineEnabled;
    }

    void setPressed(boolean isSelected) {
        isPressed = isSelected;
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        int textColor = isPressed ? pressedTextColor : normalTextColor;
        textPaint.setColor(textColor);
        textPaint.bgColor = Color.TRANSPARENT;
        textPaint.setUnderlineText(isUnderLineEnabled);
    }
}