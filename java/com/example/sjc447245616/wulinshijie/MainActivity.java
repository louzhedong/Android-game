package com.example.sjc447245616.wulinshijie;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;


public class MainActivity extends AppCompatActivity {

    //main_activity
    Button main_btn_name;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    TextView main_txtv_lv;
    TextView main_txtv_money;

    int firstflag = 0;
    public SQLiteDatabase db;
    public static Context mContext;
    public MyDBOpenHelper myDBHelper;
    private StringBuilder sb;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        myDBHelper = new MyDBOpenHelper(mContext, "my.db", null, 1);

        ///////////////////////////雪花
//        new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
//                .setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
//                .setRotationSpeed(144)
//                .setAcceleration(0.000017f, 90)
//                .emit(findViewById(R.id.emiter_top_right), 8);

        new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.000017f, 90)
                .emit(findViewById(R.id.emiter_top_left), 8);
        //////////////////////////////////////////database
        db = myDBHelper.getWritableDatabase();

        //判断唯一一次初始化应用数据
        Cursor cursor = db.query("flag", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int flag = cursor.getInt(cursor.getColumnIndex("id"));
                firstflag = flag;
            } while (cursor.moveToNext());
        }
        cursor.close();

        ContentValues values1[] = new ContentValues[9];
        for(int i = 0; i < 9; i++)
            values1[i] = new ContentValues();

        if(firstflag == 0) {
            values1[0].put("id", 1);
            values1[0].put("name", "天空与大海");
            values1[0].put("level", 1);
            values1[0].put("money", 800);
            values1[0].put("jingyanhave", 0);
            values1[0].put("jingyanneed", 25);
            values1[0].put("allattack", 20);
            values1[0].put("allprotect", 30);
            values1[0].put("allblood", 40);
            db.insert("user", null, values1[0]);

            values1[1].put("id", 1);
            values1[1].put("level", 1);
            values1[1].put("jingyanhave", 0);
            values1[1].put("jingyanneed", 30);
            values1[1].put("place", 1);
            values1[1].put("gzid", 1);
            values1[1].put("fzid", 101);
            db.insert("hero", null, values1[1]);

            values1[2].put("id", 1);
            values1[2].put("number", 10);
            db.insert("thing", null, values1[2]);
            values1[3].put("id", 2);
            values1[3].put("number", 2);
            db.insert("thing", null, values1[3]);

            values1[4].put("id", 1);
            values1[4].put("number", 1);
            values1[4].put("place", "a");
            values1[4].put("type",1);
            db.insert("clothes", null, values1[4]);
            values1[5].put("id", 101);
            values1[5].put("number", 1);
            values1[5].put("place", "a");
            values1[5].put("type",2);
            db.insert("clothes", null, values1[5]);
            Toast.makeText(mContext, "系统礼物已发送~", Toast.LENGTH_SHORT).show();

            ContentValues value = new ContentValues();
            value.put("id", 1);
            db.insert("flag", null, value);
        }

        ////////////////
        intent = new Intent("android.Music");

        startService(intent);
        //初始化游戏数据
        initdata();
    }

    @Override
    protected void onStart(){
        main_btn_name = (Button)findViewById(R.id.btn0);
        main_txtv_lv = (TextView)findViewById(R.id.txtv3);
        main_txtv_money = (TextView)findViewById(R.id.txtv5);

        main_btn_name.setText(SJ.name);
        String level;
        level = Integer.toString(SJ.level);
        main_txtv_lv.setText(level);
        String money;
        money = Integer.toString(SJ.money);
        main_txtv_money.setText(money);


        main_btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.litreily.MineActivity"));
            }
        });

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.litreily.HeroActivity"));
            }
        });

        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.litreily.PacketActivity"));
            }
        });

        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.litreily.StoreActivity"));
            }
        });

        btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.litreily.CallActivity"));
            }
        });

        btn5 = (Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.litreily.FightActivity"));
            }
        });

        btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.litreily.SetActivity"));
            }
        });



        super.onStart();
    }


    @Override
    protected void onStop(){
        super.onStop();
    }


//    protected void onDestory(){
//        stopService(intent);
//        super.onDestroy();
//    }

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

    private void initdata(){
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int pid = cursor.getInt(cursor.getColumnIndex("id"));
                SJ.name = cursor.getString(cursor.getColumnIndex("name"));
                SJ.level = cursor.getInt(cursor.getColumnIndex("level"));
                SJ.money = cursor.getInt(cursor.getColumnIndex("money"));
                SJ.jingyan_have = cursor.getInt(cursor.getColumnIndex("jingyanhave"));
                SJ.jingyan_need = cursor.getInt(cursor.getColumnIndex("jingyanneed"));
            } while (cursor.moveToNext());
        }
        cursor.close();

        initzb();
        initwp();
        inityx();
    }

    private void inityx(){
        Cursor cursor = db.query("hero", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int pid =  cursor.getInt(cursor.getColumnIndex("id"));
                SJ.getInstance().yx[pid].id = pid;
                SJ.getInstance().yx[pid].level = cursor.getInt(cursor.getColumnIndex("level"));
                SJ.getInstance().yx[pid].jingyan_have = cursor.getInt(cursor.getColumnIndex("jingyanhave"));
                SJ.getInstance().yx[pid].jingyan_need = cursor.getInt(cursor.getColumnIndex("jingyanneed"));
                SJ.getInstance().yx[pid].place = cursor.getInt(cursor.getColumnIndex("place"));
                SJ.getInstance().yx[pid].gz.id = cursor.getInt(cursor.getColumnIndex("gzid"));
                SJ.getInstance().yx[pid].fz.id = cursor.getInt(cursor.getColumnIndex("fzid"));
                SJ.getInstance().yx[pid].type_have = 1;
                if(SJ.getInstance().yx[pid].place != 0)
                    SJ.number_fight_hero ++;
                SJ.number_hero ++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        for(int i = 0; i < 50; i++){
            switch(i){
                case 1:
                    SJ.getInstance().yx[i].id = i;
                    SJ.getInstance().yx[i].name = "龙傲天";
                    SJ.getInstance().yx[i].yxhead_img = R.drawable.yx_longaotian_s;
                    SJ.getInstance().yx[i].yxbody_img = R.drawable.yx_longaotian_b;
                    SJ.getInstance().yx[i].jnimg = R.drawable.jn_cishagongji;
                    SJ.getInstance().yx[i].descrption = "天下第一奇男子";
                    SJ.getInstance().yx[i].attack_start = 22;
                    SJ.getInstance().yx[i].attack = SJ.getInstance().yx[i].attack_start + SJ.getInstance().yx[i].level * (4 + 9) + 35;
                    SJ.getInstance().yx[i].protect_start = 5;
                    SJ.getInstance().yx[i].protect = SJ.getInstance().yx[i].protect_start + SJ.getInstance().yx[i].level * 2;
                    SJ.getInstance().yx[i].blood = 120;
                    SJ.getInstance().yx[i].blood = SJ.getInstance().yx[i].blood_start + SJ.getInstance().yx[i].level * 11;
                    break;
                case 2:
                    SJ.getInstance().yx[i].id = i;
                    SJ.getInstance().yx[i].name = "水灵";
                    SJ.getInstance().yx[i].yxhead_img = R.drawable.yx_shuilin_s;
                    SJ.getInstance().yx[i].yxbody_img = R.drawable.yx_shuilin_b;
                    SJ.getInstance().yx[i].jnimg = R.drawable.jn_shuilongshu;
                    SJ.getInstance().yx[i].descrption = "绝世美人";
                    SJ.getInstance().yx[i].attack_start = 21;
                    SJ.getInstance().yx[i].attack = SJ.getInstance().yx[i].attack_start + SJ.getInstance().yx[i].level * (4 + 9) + 38;
                    SJ.getInstance().yx[i].protect_start = 4;
                    SJ.getInstance().yx[i].protect = SJ.getInstance().yx[i].protect_start + SJ.getInstance().yx[i].level * 2;
                    SJ.getInstance().yx[i].blood = 96;
                    SJ.getInstance().yx[i].blood = SJ.getInstance().yx[i].blood_start + SJ.getInstance().yx[i].level * 10;
                    break;
                case 3:
                    SJ.getInstance().yx[i].id = i;
                    SJ.getInstance().yx[i].name = "武僧";
                    SJ.getInstance().yx[i].yxhead_img = R.drawable.yx_wusen_s;
                    SJ.getInstance().yx[i].yxbody_img = R.drawable.yx_wusen_b;
                    SJ.getInstance().yx[i].jnimg = R.drawable.jn_jinzhongzhao;
                    SJ.getInstance().yx[i].descrption = "武林狂僧";
                    SJ.getInstance().yx[i].attack_start = 20;
                    SJ.getInstance().yx[i].attack = SJ.getInstance().yx[i].attack_start + SJ.getInstance().yx[i].level * 4;
                    SJ.getInstance().yx[i].protect_start = 6;
                    SJ.getInstance().yx[i].protect = SJ.getInstance().yx[i].protect_start + SJ.getInstance().yx[i].level * (3 + 4) + 5;
                    SJ.getInstance().yx[i].blood = 130;
                    SJ.getInstance().yx[i].blood = SJ.getInstance().yx[i].blood_start + SJ.getInstance().yx[i].level * 13;
                    break;
                case 4:
                    SJ.getInstance().yx[i].id = i;
                    SJ.getInstance().yx[i].name = "剑客";
                    SJ.getInstance().yx[i].yxhead_img = R.drawable.yx_jianke_s;
                    SJ.getInstance().yx[i].yxbody_img = R.drawable.yx_jianke_b;
                    SJ.getInstance().yx[i].jnimg = R.drawable.jn_shunpizhan;
                    SJ.getInstance().yx[i].descrption = "西门吹雪";
                    SJ.getInstance().yx[i].attack_start = 21;
                    SJ.getInstance().yx[i].attack = SJ.getInstance().yx[i].attack_start + SJ.getInstance().yx[i].level * (5 + 9) + 39;
                    SJ.getInstance().yx[i].protect_start = 5;
                    SJ.getInstance().yx[i].protect = SJ.getInstance().yx[i].protect_start + SJ.getInstance().yx[i].level * 2;
                    SJ.getInstance().yx[i].blood = 112;
                    SJ.getInstance().yx[i].blood = SJ.getInstance().yx[i].blood_start + SJ.getInstance().yx[i].level * 11;
                    break;
                case 5:
                    SJ.getInstance().yx[i].id = i;
                    SJ.getInstance().yx[i].name = "南宫瑾";
                    SJ.getInstance().yx[i].yxhead_img = R.drawable.yx_nangongjin_s;
                    SJ.getInstance().yx[i].yxbody_img = R.drawable.yx_nangongjing_b;
                    SJ.getInstance().yx[i].jnimg = R.drawable.jn_longyin;
                    SJ.getInstance().yx[i].descrption = "巧笑倩兮美女子";
                    SJ.getInstance().yx[i].attack_start = 22;
                    SJ.getInstance().yx[i].attack = SJ.getInstance().yx[i].attack_start + SJ.getInstance().yx[i].level * (5 + 9) + 42;
                    SJ.getInstance().yx[i].protect_start = 4;
                    SJ.getInstance().yx[i].protect = SJ.getInstance().yx[i].protect_start + SJ.getInstance().yx[i].level * 2;
                    SJ.getInstance().yx[i].blood = 105;
                    SJ.getInstance().yx[i].blood = SJ.getInstance().yx[i].blood_start + SJ.getInstance().yx[i].level * 12;
                    break;
                default:
                    break;
            }
            if(SJ.getInstance().yx[i].gz.id != 0)
                SJ.getInstance().yx[i].gz = SJ.getInstance().zb[SJ.getInstance().yx[i].gz.id];
            else
                SJ.getInstance().yx[i].gz = new Zhuangbei();

            if(SJ.getInstance().yx[i].fz.id != 0)
                SJ.getInstance().yx[i].fz = SJ.getInstance().zb[SJ.getInstance().yx[i].fz.id];
            else
                SJ.getInstance().yx[i].fz = new Zhuangbei();

        }



    }

    private void initzb(){

        Cursor cursor = db.query("clothes", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int pid = cursor.getInt(cursor.getColumnIndex("id"));
                SJ.getInstance().zb[pid].id = pid;
                SJ.getInstance().zb[pid].number = cursor.getInt(cursor.getColumnIndex("number"));
                SJ.getInstance().zb[pid].place = cursor.getString(cursor.getColumnIndex("place"));
                SJ.getInstance().zb[pid].type = cursor.getInt(cursor.getColumnIndex("type"));
                SJ.number_zb++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        for(int i = 0; i < 200; i++){
            switch(i){
                case 1:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "铁剑";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_tiejian_s;
                    SJ.getInstance().zb[i].description= "攻击+5";
                    SJ.getInstance().zb[i].add_attack = 5;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 2:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "法杖";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_fazhang_s;
                    SJ.getInstance().zb[i].description= "攻击+5";
                    SJ.getInstance().zb[i].add_attack = 5;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 3:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "水晶剑";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_shuijinjian_s;
                    SJ.getInstance().zb[i].description= "攻击+12";
                    SJ.getInstance().zb[i].add_attack = 12;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 4:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "魔杖";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_mozhang_s;
                    SJ.getInstance().zb[i].description= "攻击+14";
                    SJ.getInstance().zb[i].add_attack = 14;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 5:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "半月刀";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_banyuedao_s;
                    SJ.getInstance().zb[i].description= "攻击+30";
                    SJ.getInstance().zb[i].add_attack = 30;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 6:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "桃木杖";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_taomuzhang_s;
                    SJ.getInstance().zb[i].description= "攻击+28";
                    SJ.getInstance().zb[i].add_attack = 28;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 7:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "铁齿剑";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_tiechijian_s;
                    SJ.getInstance().zb[i].description= "攻击+75";
                    SJ.getInstance().zb[i].add_attack = 75;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 8:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "幽冥权杖";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_youmingquanzhang_s;
                    SJ.getInstance().zb[i].description= "攻击+70";
                    SJ.getInstance().zb[i].add_attack = 70;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 9:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "裁决刀";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_caijuedao_s;
                    SJ.getInstance().zb[i].description= "攻击+110";
                    SJ.getInstance().zb[i].add_attack = 110;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 10:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "龙杖";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.gz_longzhang_s;
                    SJ.getInstance().zb[i].description= "攻击+100";
                    SJ.getInstance().zb[i].add_attack = 100;
                    SJ.getInstance().zb[i].add_protect = 0;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 101:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "布衣";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_buyinan_s;
                    SJ.getInstance().zb[i].description= "防御+3";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 3;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 102:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "锁子甲";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_suozijia_s;
                    SJ.getInstance().zb[i].description= "防御+10";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 10;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 103:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "白法袍";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_baifapao_s;
                    SJ.getInstance().zb[i].description= "防御+9";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 9;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 104:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "铁甲";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_tiejia_s;
                    SJ.getInstance().zb[i].description= "防御+19";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 19;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 105:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "红罗裙";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_hongluoqun_s;
                    SJ.getInstance().zb[i].description= "防御+17";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 17;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 106:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "丝绸裙";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_sichouqun_s;
                    SJ.getInstance().zb[i].description= "防御+28";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 28;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 107:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "鳞甲";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_lingjia_s;
                    SJ.getInstance().zb[i].description= "防御+30";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 30;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 108:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "五彩裙";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_wucaiqun_s;
                    SJ.getInstance().zb[i].description= "防御+45";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 45;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                case 109:
                    SJ.getInstance().zb[i].id = i;
                    SJ.getInstance().zb[i].name = "天魔甲";
                    SJ.getInstance().zb[i].zbhead_img = R.drawable.fz_tianmojia_s;
                    SJ.getInstance().zb[i].description= "防御+48";
                    SJ.getInstance().zb[i].add_attack = 0;
                    SJ.getInstance().zb[i].add_protect = 48;
                    SJ.getInstance().zb[i].add_blood = 0;
                    SJ.getInstance().zb[i].type_have = true;
                    break;
                default:
                    break;
            }
        }

    }

    private void initwp(){

        int pid = 0;
        Cursor cursor = db.query("thing", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                pid = cursor.getInt(cursor.getColumnIndex("id"));
                SJ.getInstance().wp[pid].id = pid;
                SJ.getInstance().wp[pid].number = cursor.getInt(cursor.getColumnIndex("number"));
                SJ.number_wp++;
                pid = 0;
            } while (cursor.moveToNext());
        }
        cursor.close();

        for(int i = 1; i <= 6; i++ ){
            switch (i){
                case 1:
                    SJ.wp[i].id = i;
                    SJ.wp[i].name = "包子";
                    SJ.wp[i].wphead_img = R.drawable.wp_baozi_s;
                    SJ.wp[i].description = "恢复30点血量";
                    SJ.wp[i].type_have = true;
                    break;
                case 2:
                    SJ.wp[i].id = i;
                    SJ.wp[i].name = "碧螺春";
                    SJ.wp[i].wphead_img = R.drawable.wp_biluochun_s;
                    SJ.wp[i].description = "恢复100点血量";
                    SJ.wp[i].type_have = true;
                    break;
                case 3:
                    SJ.wp[i].id = i;
                    SJ.wp[i].name = "生机丸";
                    SJ.wp[i].wphead_img = R.drawable.wp_shengjiwan_s;
                    SJ.wp[i].description = "恢复200点血量";
                    SJ.wp[i].type_have = true;
                    break;
                case 4:
                    SJ.wp[i].id = i;
                    SJ.wp[i].name = "经验药水";
                    SJ.wp[i].wphead_img = R.drawable.wp_jingyanyaoshui_s;
                    SJ.wp[i].description = "增加10点经验";
                    SJ.wp[i].type_have = true;
                    break;
                case 5:
                    SJ.wp[i].id = i;
                    SJ.wp[i].name = "经验药膏";
                    SJ.wp[i].wphead_img = R.drawable.wp_jingyanyaogao_s;
                    SJ.wp[i].description = "增加50点经验";
                    SJ.wp[i].type_have = true;
                    break;
                case 6:
                    SJ.wp[i].id = i;
                    SJ.wp[i].name = "经验奶酪";
                    SJ.wp[i].wphead_img = R.drawable.wp_baozi_s;
                    SJ.wp[i].description = "增加100点经验";
                    SJ.wp[i].type_have = true;
                    break;
                default:
                    break;
            }
        }
    }
}

