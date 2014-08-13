package com.cw.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cw.util.CommonTools;

public class NetworkButton extends Button {

    public NetworkButton(Context context) {
        super(context);
    }

    public NetworkButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnClickListener(final OnClickListener listener) {
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // �ж���������״̬�����Ƿ�ִ���û��������
                if (CommonTools.isNetworkConnected(NetworkButton.this
                        .getContext()) && listener != null) {
                    listener.onClick(v);
                } else {
                    Toast.makeText(getContext(), "�����쳣��", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });
    }
}
