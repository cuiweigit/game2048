package com.cw.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cw.andr.R;
import com.cw.util.StringUtil;

public class StartActivity extends SuperActivity implements OnClickListener {
    private Button btn_start;
    private Button btn_exit;
    private Button btn_resume;
    private TextView tv_show;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        initView();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContinueGame();
    }
    @Override
    protected void onRestart() {
        setContinueGame();
        super.onRestart();
    }
    @Override
    protected void onStart() {
        setContinueGame();
        super.onStart();


    }
    private void initView() {
        tv_show = (TextView) findViewById(R.id.tv_title);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_resume = (Button) findViewById(R.id.btn_resume);
        setContinueGame();
    }

    /**
     * 设置继续游戏按钮行为
     * @return
     */
    private void setContinueGame()
    {
        String data = sp.getString(_DATA_KEY, "");
        if (StringUtil.isBlankOrNull(data)) {
            // btn_resume.setVisibility(View.GONE);
            btn_resume.setEnabled(false);
            btn_resume.setBackgroundColor(Color.GRAY);
            flag = _FLAG_NEW;
        } else {
            // btn_resume.setVisibility(View.VISIBLE);
            btn_resume.setEnabled(true);
            btn_resume.setBackgroundColor(Color.rgb(119,110,101));
            flag = _FLAG_RESUME;
        }
    }
    private void initListener() {
        btn_start.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_resume.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_start:
                intent = new Intent(this, GameMainActivity.class);
                intent.putExtra(_INTENT_FLAG_KEY, _FLAG_NEW);
                this.startActivity(intent);
                break;
            case R.id.btn_resume:
                intent = new Intent(this, GameMainActivity.class);
                intent.putExtra(_INTENT_FLAG_KEY, flag);
                this.startActivity(intent);
                break;
            case R.id.btn_exit:
                finish();
                break;
            default:
                break;
        }

    }

}
