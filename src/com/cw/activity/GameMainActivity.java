package com.cw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cw.andr.R;
import com.cw.enumeration.EnumDirection;
import com.cw.util.StringUtil;
import com.cw.widget.Grid2048;
import com.google.gson.Gson;

import java.util.Random;

public class GameMainActivity extends SuperActivity implements OnTouchListener {
    private static final Integer _GRID_LEN = 4;
    private static final Integer _ID_START = R.id.r00c00;
    private RelativeLayout rl_gameView;
    private TextView tv_best;
    private TextView tv_score;
    private TextView tv_title;
    /* 游戏界面格子 */
    private Grid2048[][] grid44;
    private int[][] data2048;
    private float downX;
    private float downY;
    private float upX;
    private float upY;
    private EnumDirection curDirection;
    private EnumDirection lastDirection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main_layout);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        rl_gameView = (RelativeLayout) findViewById(R.id.rl_gameView);
        tv_best = (TextView) findViewById(R.id.tv_best);
        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_title = (TextView) findViewById(R.id.tv_title);
        grid44 = new Grid2048[4][];
        data2048 = new int[4][];
        tv_best.setText(sp.getInt(_BEST_KEY, 0) + "");//初始化最高分
        for (int y = 0; y != _GRID_LEN; ++y) {
            grid44[y] = new Grid2048[4];
            data2048[y] = new int[4];//初始化数据（本应放入initData中，但因少写一些循环提高效率故在此初始化）
            for (int x = 0; x != _GRID_LEN; ++x) {
                data2048[y][x] = 0;
                grid44[y][x] = (Grid2048) findViewById(_ID_START + (y * 4 + x));
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initListener() {
        rl_gameView.setOnTouchListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        String flag = intent.getStringExtra(_INTENT_FLAG_KEY);
        if (_FLAG_RESUME.equals(flag))//继续游戏
        {
            String data = sp.getString(_DATA_KEY, "");
            if (StringUtil.isNotBlankOrNull(data)) {
                try {
                    Gson gson = new Gson();
                    data2048 = (int[][]) gson.fromJson(data, int[][].class);
                    setDate();
                    tv_score.setText(sp.getInt(_SCORE_KEY, 0) + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    goOneRound();
                }
            }
        } else {
            goOneRound();
        }
        editor.remove(_DATA_KEY);
        editor.remove(_SCORE_KEY);
        editor.commit();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            upX = event.getX();
            upY = event.getY();
            curDirection = getDirection(downX, downY, upX, upY);
            move(curDirection);
            // Log.v("cw", curDirection + "");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据Ontouch横纵坐标变化判断手指移动方向
     *
     * @param dx
     * @param dy
     * @param ux
     * @param uy
     * @return
     */
    private EnumDirection getDirection(float dx, float dy, float ux, float uy) {
        EnumDirection res = EnumDirection._O;
        float x = ux - dx;// 横向移动坐标
        float y = uy - dy;// 纵向移动坐标
        float hv = Math.abs(y) - Math.abs(x);
        if (hv > 0) {// 纵向移动
            res = y > 0 ? EnumDirection._DOWN : EnumDirection._UP;
        } else if(hv < 0) {// 横向移动
            res = x > 0 ? EnumDirection._RIGHT : EnumDirection._LEFT;
        }
        return res;
    }

    /**
     * 随机在一个随机位置内生成一个2或4
     *
     * @return
     */
    private boolean goOneRound() {
        Random r = new Random();
        int num = (r.nextInt(2) == 0) ? 2 : 4;
        for (int i = 0; i != _GRID_LEN; ++i) {
            for (int j = 0; j != _GRID_LEN; ++j) {
                if (data2048[i][j] == 0) {
                    while (true) {
                        int y = r.nextInt(4);
                        int x = r.nextInt(4);
                        if (data2048[y][x] == 0) {
                            data2048[y][x] = num;
                            setDate();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 游戏界面移动操作
     */
    private void move(EnumDirection direction) {
        switch (direction) {
            case _LEFT:
                slideLeft();
                break;
            case _RIGHT:
                slideRight();
                break;
            case _DOWN:
                slideDown();
                break;
            case _UP:
                slideUp();
                break;
            default:
                return;
        }
        boolean res = goOneRound();
        //Log.v("msg",res + "");
        if (!res) {
            gameOver();
        }
    }

    private void gameOver() {
        try {
            int s = Integer.valueOf(tv_score.getText() + "");
            int sOld = sp.getInt(_BEST_KEY, 0);
            if (s > sOld) {//新纪录，记录最高分
                editor.putInt(_BEST_KEY, s).commit();
            }

        } catch (Exception e) {

        } finally {
            Toast.makeText(this, "游戏结束！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        saveDate();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveDate();
        super.onStop();
    }



    @Override
    protected void onDestroy() {
        saveDate();
        super.onDestroy();
    }

    /**
     * 保存游戏进度
     */
    private void saveDate() {
        try {
            Gson gson = new Gson();
            String data = gson.toJson(data2048);
            int s = Integer.valueOf(tv_score.getText() + "");
            editor.putInt(_SCORE_KEY,s );
            editor.putString(_DATA_KEY, data);
            editor.commit();
        } catch (Exception e) {
            editor.remove(_SCORE_KEY);
            editor.remove(_DATA_KEY);
            editor.commit();
            e.printStackTrace();
        } finally {

        }
    }


    /**
     * 将数据塞到界面中
     */
    private void setDate() {
        for (int y = 0; y != _GRID_LEN; ++y) {
            for (int x = 0; x != _GRID_LEN; ++x) {
                if (grid44[y][x] != null) {
                    grid44[y][x].setNum(data2048[y][x]);
                }
            }
        }
    }

    private void addScore(int score) {
        try {
            int s = Integer.parseInt(tv_score.getText() + "");
            s += score;
            tv_score.setText(s + "");
        } catch (Exception e) {
        }

    }

    /**
     * 向左滑动
     */
    private void slideLeft() {
        for (int y = 0; y != _GRID_LEN; ++y) {
            for (int x = 0; x < _GRID_LEN; ++x) {
                for (int tmp = x + 1; tmp < _GRID_LEN; ++tmp) {
                    if (data2048[y][tmp] != 0) {
                        if (data2048[y][x] == 0) {
                            data2048[y][x] = data2048[y][tmp];
                            data2048[y][tmp] = 0;

                        } else if (data2048[y][x] == data2048[y][tmp]) {
                            {
                                boolean flag = true;
                                for (int l = x + 1; l < tmp; ++l) {
                                    if (data2048[y][l] != 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    data2048[y][x] += data2048[y][tmp];
                                    addScore(data2048[y][tmp]);
                                    data2048[y][tmp] = 0;
                                    break;
                                }
                            }

                        }

                    }
                }
            }
        }
    }
    ////////////////////////////////////////////////////////

    /**
     * 向上滑动
     */
    private void slideUp() {
        for (int y = 0; y != _GRID_LEN; ++y) {
            for (int x = 0; x < _GRID_LEN; ++x) {
                for (int tmp = x + 1; tmp < _GRID_LEN; ++tmp) {
                    if (data2048[tmp][y] != 0) {
                        if (data2048[x][y] == 0) {
                            data2048[x][y] = data2048[tmp][y];
                            data2048[tmp][y] = 0;

                        } else if (data2048[x][y] == data2048[tmp][y]) {
                            {
                                boolean flag = true;
                                for (int l = x + 1; l < tmp; ++l) {
                                    if (data2048[l][y] != 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    data2048[x][y] += data2048[tmp][y];
                                    addScore(data2048[tmp][y]);
                                    data2048[tmp][y] = 0;
                                    break;
                                }
                            }

                        }

                    }
                }
            }
        }
    }
    //////////////////////////////////////////////////////

    /**
     * 向右滑动
     */
    private void slideRight() {
        for (int y = 0; y != _GRID_LEN; ++y) {
            for (int x = _GRID_LEN - 1; x >= 0; --x) {
                for (int tmp = x - 1; tmp >= 0; --tmp) {
                    if (data2048[y][tmp] != 0) {
                        if (data2048[y][x] == 0) {
                            data2048[y][x] = data2048[y][tmp];
                            data2048[y][tmp] = 0;

                        } else if (data2048[y][x] == data2048[y][tmp]) {
                            {
                                boolean flag = true;
                                for (int l = x - 1; l > tmp; --l) {
                                    if (data2048[y][l] != 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    data2048[y][x] += data2048[y][tmp];
                                    addScore(data2048[y][tmp]);
                                    data2048[y][tmp] = 0;
                                    break;
                                }
                            }

                        }

                    }
                }
            }
        }
    }
    //////////////////////////////////////////////////////////////////

    /**
     * 向下滑动
     */
    private void slideDown() {
        for (int y = 0; y != _GRID_LEN; ++y) {
            for (int x = _GRID_LEN - 1; x >= 0; --x) {
                for (int tmp = x - 1; tmp >= 0; --tmp) {
                    if (data2048[tmp][y] != 0) {
                        if (data2048[x][y] == 0) {
                            data2048[x][y] = data2048[tmp][y];
                            data2048[tmp][y] = 0;

                        } else if (data2048[x][y] == data2048[tmp][y]) {
                            {
                                boolean flag = true;
                                for (int l = x - 1; l > tmp; --l) {
                                    if (data2048[l][y] != 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    data2048[x][y] += data2048[tmp][y];
                                    addScore(data2048[tmp][y]);
                                    data2048[tmp][y] = 0;
                                    break;
                                }
                            }

                        }

                    }
                }
            }
        }
    }
    //////////////////////////////////////////////////////////////////
}

