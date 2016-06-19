package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/19.
 */
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.app.*;
import android.content.*;
import android.content.DialogInterface.*;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

public class HeroActivity  extends AppCompatActivity {
    private LinearLayout head_index;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    private ImageButton[] imgbtn;
    ImageButton tmp1;
    ImageButton tmp2;
    ImageButton tmp3;
    ImageButton tmp4;
    TextView username;
    TextView userlevel;
    TextView usermoney;
    String leveltmp;
    String moneytmp;

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_activity);

        username = (TextView)findViewById(R.id.txtv1);
        userlevel = (TextView)findViewById(R.id.txtv3);
        usermoney = (TextView)findViewById(R.id.txtv5);

        username.setText(SJ.name);

        leveltmp = Integer.toString(SJ.level);
        userlevel = (TextView)findViewById(R.id.txtv3);
        userlevel.setText(leveltmp);

        usermoney = (TextView)findViewById(R.id.txtv5);
        moneytmp = Integer.toString(SJ.money);
        usermoney.setText(moneytmp);

        mInflater = LayoutInflater.from(this);

        tmp1 = (ImageButton) findViewById(R.id.txtv_hero_big);
        tmp2 = (ImageButton) findViewById(R.id.txtv_hero_skill);
        tmp3 = (ImageButton) findViewById(R.id.btn_hero_attack);
        tmp4 = (ImageButton) findViewById(R.id.btn_hero_protect);


        initData();
        initView();

    }


    private void initData() {
        mImgIds = new int[]{SJ.getInstance().yx[1].yxhead_img,R.drawable.yx_jianke_s, R.drawable.yx_jianke_s
        };

        imgbtn = new ImageButton[]{null, null, null, null, null,};

        mContext = HeroActivity.this;

    }

    private void initView() {
        head_index = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < mImgIds.length; i++) {
            View view = mInflater.inflate(R.layout.hero_activity_item,
                    head_index, false);
            imgbtn[i] = (ImageButton) view
                    .findViewById(R.id.id_index_gallery_item_image);
            imgbtn[i].setImageResource(mImgIds[i]);
            head_index.addView(view);
        }
        imgbtn[0].setOnClickListener(lis0);
        imgbtn[1].setOnClickListener(lis1);
        imgbtn[2].setOnClickListener(lis2);
    }

    private Button.OnClickListener lis0 = new Button.OnClickListener() {
        public void onClick(View v) {
            tmp1.setBackgroundResource(SJ.yx[1].yxbody_img);
            tmp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_big(v);
                }
            });
            tmp2.setBackgroundResource(SJ.yx[1].jnimg);
            tmp3.setBackgroundResource(SJ.yx[1].gz.zbhead_img);
            tmp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_gz(v);
                }
            });
            tmp4.setBackgroundResource(SJ.yx[1].fz.zbhead_img);
            tmp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_fz(v);
                }
            });
        }
    };

    private Button.OnClickListener lis1 = new Button.OnClickListener() {
        public void onClick(View v) {
            tmp1.setBackgroundResource(R.mipmap.herob);
            tmp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_big(v);
                }
            });
            tmp2.setBackgroundResource(R.drawable.herob_jn);
            tmp3.setBackgroundResource(R.drawable.gz2);
            tmp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_gz(v);
                }
            });
            tmp4.setBackgroundResource(R.drawable.fz2);
            tmp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_fz(v);
                }
            });
        }
    };

    private Button.OnClickListener lis2 = new Button.OnClickListener() {
        public void onClick(View v) {
            tmp1.setBackgroundResource(R.mipmap.heroc);
            tmp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_big(v);
                }
            });
            tmp2.setBackgroundResource(R.drawable.heroc_jn);
            tmp3.setBackgroundResource(R.drawable.gz3);
            tmp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_gz(v);
                }
            });
            tmp4.setBackgroundResource(R.drawable.fz3);
            tmp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopWindow_fz(v);
                }
            });
        }
    };


    private void initPopWindow_big(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hero_big_popup, null, false);
        Button btn_change = (Button) view.findViewById(R.id.btn_change);
        Button btn_remove = (Button) view.findViewById(R.id.btn_remove);
        btn_change.setText("更换");
        btn_remove.setText("下阵");
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.layout.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 0, -50);

        //设置popupWindow里的按钮的事件
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HeroActivity.this, "你点击了更换", Toast.LENGTH_SHORT).show();
                startActivity(new Intent("com.litreily.PacketActivity"));
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeroActivity.this, "你点击了下阵", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initPopWindow_gz(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hero_big_popup, null, false);
        Button btn_change = (Button) view.findViewById(R.id.btn_change);
        Button btn_remove = (Button) view.findViewById(R.id.btn_remove);
        btn_change.setText("更换");
        btn_remove.setText("解除");
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.layout.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, -200, -40);

        //设置popupWindow里的按钮的事件
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HeroActivity.this, "你点击了更换", Toast.LENGTH_SHORT).show();
                startActivity(new Intent("com.litreily.PacketActivity"));
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeroActivity.this, "你点击了解除", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initPopWindow_fz(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hero_big_popup, null, false);
        Button btn_change = (Button) view.findViewById(R.id.btn_change);
        Button btn_remove = (Button) view.findViewById(R.id.btn_remove);
        btn_change.setText("更换");
        btn_remove.setText("解除");
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.layout.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, -200, -40);

        //设置popupWindow里的按钮的事件
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HeroActivity.this, "你点击了更换", Toast.LENGTH_SHORT).show();
                startActivity(new Intent("com.litreily.PacketActivity"));
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeroActivity.this, "你点击了解除", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ParticleSystem ps;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Create a particle system and start emiting
                ps = new ParticleSystem(this, 100, R.drawable.star_pink, 800);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedRange(0.05f, 0.1f);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.emit((int) event.getX(), (int) event.getY(), 40);
                break;
            case MotionEvent.ACTION_MOVE:
                ps.updateEmitPoint((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                ps.stopEmitting();
                break;
        }
        return true;
    }
}