package com.ptithcm.admin.view.textlink;

import android.graphics.Color;
import android.util.Patterns;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

public class AutoLinkMode {
    public static final int DEFAULT_COLOR = Color.RED;
    public static final int DEFAULT_COLOR_SELECT = Color.RED;

    public static final String MODE_URL = "Url";
    public static final String MODE_PHONE = "Phone";
    public static final String MODE_EMAIL = "Email";

    public static AutoLinkMode URL = new AutoLinkMode(MODE_URL, Patterns.WEB_URL, DEFAULT_COLOR, DEFAULT_COLOR_SELECT);

    public static AutoLinkMode PHONE =
            new AutoLinkMode(MODE_PHONE, Patterns.PHONE, DEFAULT_COLOR, DEFAULT_COLOR_SELECT);

    public static AutoLinkMode EMAIL =
            new AutoLinkMode(MODE_EMAIL, Patterns.EMAIL_ADDRESS, DEFAULT_COLOR, DEFAULT_COLOR_SELECT);

    private String name;

    private Pattern pattern;

    private int color = DEFAULT_COLOR;

    private int colorSelect = DEFAULT_COLOR_SELECT;

    public AutoLinkMode(@NonNull String name, @NonNull Pattern pattern, int color, int colorSelect) {
        this.name = name;
        this.pattern = pattern;
        this.color = color;
        this.colorSelect = colorSelect;
    }

    public AutoLinkMode(String name, Pattern pattern) {
        this.name = name;
        this.pattern = pattern;
        this.color = DEFAULT_COLOR;
        this.colorSelect = DEFAULT_COLOR_SELECT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColorSelect() {
        return colorSelect;
    }

    public void setColorSelect(int colorSelect) {
        this.colorSelect = colorSelect;
    }

    // Write method setter, getter 
}