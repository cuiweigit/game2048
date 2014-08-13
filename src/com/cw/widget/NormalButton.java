package com.cw.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class NormalButton extends Button {
    public NormalButton(Context context) {
        super(context);
    }

    public NormalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnClickListener(final OnClickListener listener) {
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // to do ~~~
                if (listener != null) {
                    listener.onClick(v);
                }

            }
        });
    }
}
