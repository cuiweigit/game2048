package com.cw.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.cw.andr.R.color;
import com.cw.util.StringUtil;

public class Grid2048 extends BoldTextView {
    public Grid2048(Context context) {
        super(context);
    }

    public Grid2048(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Grid2048 add(Grid2048 g) {
        if (g.getNum() != null) {
            Integer tag = (this.getNum() == null) ? 0 : this.getNum();
            tag += g.getNum();
            this.setNum(tag);
            g.setNum(-1);
        }
        return this;
    }

    public void setNum(Integer num) {
        this.setTag(num);
        if (num != null) {
            switch (num) {
                case 2:
                    this.setBackgroundColor(Color.rgb(238, 228, 218));
                    this.setTextColor(Color.rgb(119, 110, 101));
                    this.setTextSize(35);
                    this.setText(this.getTag() + "");
                    break;
                case 4:
                    this.setBackgroundColor(Color.rgb(236, 236, 200));
                    this.setTextColor(Color.rgb(119, 110, 101));
                    this.setTextSize(35);
                    this.setText(this.getTag() + "");
                    break;
                case 8:
                    this.setBackgroundColor(Color.rgb(242, 177, 119));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(35);
                    this.setText(this.getTag() + "");
                    break;
                case 16:
                    this.setBackgroundColor(Color.rgb(236, 141, 83));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(35);
                    this.setText(this.getTag() + "");
                    break;
                case 32:
                    this.setBackgroundColor(Color.rgb(245, 124, 95));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(35);
                    this.setText(this.getTag() + "");
                    break;
                case 64:
                    this.setBackgroundColor(Color.rgb(233, 89, 55));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(35);
                    this.setText(this.getTag() + "");
                    break;
                case 128:
                    this.setBackgroundColor(Color.rgb(243, 217, 107));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(25);
                    this.setText(this.getTag() + "");
                    break;
                case 256:
                    this.setBackgroundColor(Color.rgb(241, 208, 75));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(25);
                    this.setText(this.getTag() + "");
                    break;
                case 512:
                    this.setBackgroundColor(Color.rgb(228, 192, 42));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(25);
                    this.setText(this.getTag() + "");
                    break;
                case 1024:
                    this.setBackgroundColor(Color.rgb(227, 186, 20));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(20);
                    this.setText(this.getTag() + "");
                    break;
                case 2048:
                    this.setBackgroundColor(Color.rgb(236, 196, 0));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(20);
                    this.setText(this.getTag() + "");
                    break;
                case 4096:
                    this.setBackgroundColor(Color.rgb(94, 218, 146));
                    this.setTextColor(Color.rgb(251, 255, 253));
                    this.setTextSize(20);
                    this.setText(this.getTag() + "");
                    break;
                default:
                    this.setBackgroundColor(Color.rgb(204, 192, 178));
                    this.setTextColor(Color.rgb(204, 192, 178));
                    this.setTag(null);
                    this.setText(this.getTag() + "");
                    break;

            }
        }

    }

    public boolean isBlank() {
        return this.getNum() == null;
    }

    public Integer getNum() {
        Integer res = null;
        try {
            if (this.getTag() == null) {
                res = Integer.valueOf(this.getTag() + "");
            } else {
                res = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = null;
        }
        return res;
    }

    public boolean equals(Grid2048 g) {
        try {
            return this.getNum().equals(g.getNum());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
