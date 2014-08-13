package com.cw.widget;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class BoldTextView extends TextView {
    public BoldTextView(Context context) {
        super(context);
        TextPaint tp = this.getPaint();
        tp.setFakeBoldText(true);
    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TextPaint tp = this.getPaint();
        tp.setFakeBoldText(true);
    }
}
