package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/19.
 */
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.*;
import android.content.*;

import com.plattysoft.leonids.ParticleSystem;

public class FightActivity  extends AppCompatActivity {
    private LinearLayout head_index;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    private ImageButton[] imgbtn;
    public static int choice = 0;
    public static TextView username;
    public static TextView userlevel;
    public static TextView usermoney;
    String leveltmp;
    String moneytmp;

    //listview
    private int[] mImgIds1 = new int[]{R.drawable.gw_xiaomogu_s, R.drawable.gw_dumogu_s,
    };

    private final static String[] datawd1 = {"小蘑菇","小蘑菇","小蘑菇","毒蘑菇","毒蘑菇","桃木精"};
    private final static String[] datawd2 = {"失足海盗","失足海盗","南海海盗","海盗头目","毒蘑菇","桃木精"};
    private final static String[] datawd3 = {"小蘑菇","小蘑菇","小蘑菇","毒蘑菇","毒蘑菇","桃木精"};

    //创建数据源.
    Monster[] data0 = new Monster[]{
            new Monster(mImgIds1[0],datawd1[0],"战斗"),
            new Monster(mImgIds1[0],datawd1[1],"战斗"),
            new Monster(mImgIds1[0],datawd1[2],"战斗"),
            new Monster(mImgIds1[1],datawd1[3],"战斗"),
            new Monster(mImgIds1[1],datawd1[4],"战斗"),
            new Monster(mImgIds1[1],datawd1[5],"战斗") ,
    };

    Monster[] data1 = new Monster[]{
            new Monster(mImgIds1[0],datawd2[0],"战斗"),
            new Monster(mImgIds1[1],datawd2[1],"战斗"),
            new Monster(mImgIds1[0],datawd2[2],"战斗"),
            new Monster(mImgIds1[0],datawd2[3],"战斗"),
    };

    Monster[] data2 = new Monster[]{
            new Monster(mImgIds1[0],datawd3[0],"战斗"),
            new Monster(mImgIds1[0],datawd3[1],"战斗"),
            new Monster(mImgIds1[1],datawd3[2],"战斗"),
            new Monster(mImgIds1[0],datawd3[3],"战斗"),
    };

//    Monster[] data3 = new Monster[]{
//            new Monster(mImgIds1[0],data[0],"战斗"),
//            new Monster(mImgIds1[0],data[1],"战斗"),
//            new Monster(mImgIds1[0],data[2],"战斗"),
//            new Monster(mImgIds1[1],data[3],"战斗"),
//
//    };

    FightListAdapter adapter0;
    FightListAdapter adapter1;
    FightListAdapter adapter2;
//    FightListAdapter adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_activity);
        mInflater = LayoutInflater.from(this);
        initData();
        initView();

        ListView listview = (ListView)findViewById(R.id.fight_listview);
        adapter0 = new FightListAdapter(this, R.layout.fight_listview,data0) ;//okay, the resource id is passed.
        adapter1 = new FightListAdapter(this, R.layout.fight_listview,data1) ;//okay, the resource id is passed.
        adapter2 = new FightListAdapter(this, R.layout.fight_listview,data2) ;//okay, the resource id is passed.
//        adapter3 = new FightListAdapter(this, R.layout.fight_listview,data3) ;//okay, the resource id is passed.
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


        listview.setAdapter(adapter0);

    }

    private void initData() {
        mImgIds = new int[]{R.mipmap.heroa_head, R.mipmap.herob_head, R.mipmap.heroc_head,
        };
        imgbtn = new ImageButton[]{null,null,null,null,null,null,null,null};
    }

    private void initView() {
        head_index = (LinearLayout) findViewById(R.id.id_index_customs);
        for (int i = 0; i < mImgIds.length; i++)
        {
            View view = mInflater.inflate(R.layout.fight_activity_item,
                    head_index, false);
            imgbtn[i] = (ImageButton) view
                    .findViewById(R.id.id_index_customs_item_image);
            imgbtn[i].setImageResource(mImgIds[i]);
            head_index.addView(view);
        }
        imgbtn[0].setOnClickListener(lis1);
        imgbtn[1].setOnClickListener(lis2);
        imgbtn[2].setOnClickListener(lis3);
    }

    private Button.OnClickListener lis1 = new Button.OnClickListener() {
        public void onClick(View v) {
            ListView listview1 = (ListView)findViewById(R.id.fight_listview);
            listview1.setAdapter(adapter0);
            choice = 0;
        }
    };

    private Button.OnClickListener lis2 = new Button.OnClickListener() {
        public void onClick(View v) {
            ListView listview2 = (ListView)findViewById(R.id.fight_listview);
            listview2.setAdapter(adapter1);
            choice = 1;
        }
    };

    private Button.OnClickListener lis3 = new Button.OnClickListener() {
        public void onClick(View v) {
            ListView listview3 = (ListView)findViewById(R.id.fight_listview);
            listview3.setAdapter(adapter2);
            choice = 2;
        }
    };

    ////////////////adapter!!!
    class FightListAdapter extends ArrayAdapter<Monster> {
        private LayoutInflater mInflater;
        private ImageView[] imgbtn;
        String leveltmp;
        String moneytmp;

        public  class ViewHolder
        {
            ImageView head;
            TextView name;
            Button go;
        }

        public FightListAdapter(Context context, int textViewResourceId,Monster[] obj) {
            super(context, textViewResourceId, obj);
            // TODO Auto-generated constructor stub
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                //创建新的view视图.
                convertView = mInflater.inflate(R.layout.fight_listview, null); //see above, you can use the passed resource id.
            }

            imgbtn = new ImageView[]{null,null,null,null,null,
                    null,null,null,null,null};
            ViewHolder holder = null;

            if(holder==null){
                holder = new ViewHolder();
                //查找每个ViewItem中,各个子View,放进holder中
                holder.head = (ImageView) convertView.findViewById(R.id.fight_head);
                holder.name = (TextView) convertView.findViewById(R.id.fight_name);
                TextPaint tp = holder.name.getPaint();
                tp.setFakeBoldText(true);
                holder.go = (Button) convertView.findViewById(R.id.fight_go);
                //保存对每个显示的ViewItem中, 各个子View的引用对象
                convertView.setTag(holder);
            }
            else// I think this a bug, program can not run here!!!--linc2014.11.12
            {
                holder = (ViewHolder)convertView.getTag();
            }

            //获取当前要显示的数据
            Monster person = getItem(position);

            holder.head.setImageResource(person.getHead());
            holder.name.setText(person.getName());
            holder.go.setText(person.getGo());
            holder.go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (FightActivity.choice) {
                        case 0:
                            //跳转到战斗画面，或者说播放战斗画面
                            switch (position) {
                                case 0:
                                    SJ.money += 10;
                                    SJ.jingyan_have += 15;
                                    diaozhuan();
                                    break;
                                case 1:
                                    SJ.money += 10;
                                    SJ.jingyan_have += 20;
                                    break;
                                case 2:
                                    SJ.money += 10;
                                    SJ.jingyan_have += 25;

                                    break;
                                case 3:
                                    SJ.money += 15;
                                    SJ.jingyan_have += 30;

                                    break;
                                case 4:
                                    SJ.money += 15;
                                    SJ.jingyan_have += 35;

                                    break;
                                case 5:
                                    SJ.money += 20;
                                    SJ.jingyan_have += 45;

                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 1:
                            switch (position) {
                                case 0:
                                    SJ.money += 25;
                                    SJ.jingyan_have += 50;

                                    break;
                                case 1:
                                    SJ.money += 25;
                                    SJ.jingyan_have += 50;

                                    break;
                                case 2:
                                    SJ.money += 30;
                                    SJ.jingyan_have += 55;

                                    break;
                                case 3:
                                    SJ.money += 35;
                                    SJ.jingyan_have += 60;

                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 2:
                            switch (position) {
                                case 0:

                                    break;
                                case 1:

                                    break;
                                case 2:

                                    break;
                                case 3:

                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    if (SJ.jingyan_have > SJ.level * 30) {
                        SJ.level++;
                        SJ.jingyan_have = 0;
                        SJ.jingyan_need = 0;
                    }
                    leveltmp = Integer.toString(SJ.level);
                    FightActivity.userlevel.setText(leveltmp);
                    moneytmp = Integer.toString(SJ.money);
                    FightActivity.usermoney.setText(moneytmp);
                }
            });
            return convertView;
        }


        public void diaozhuan(){
//            Intent intent = new Intent("com.litreily.HeroActivity");
//            startActivity(intent);
            Intent intent = new Intent();
            intent.setClass(getContext(), BattleScene.class);
            startActivity(intent);
        }
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
