package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/12/13.
 */
public class SJ {

    private static SJ sj;
    public static SJ getInstance() {        //如果涉及到类中类的实例，则需要调用该函数
        if (sj == null)
            sj = new SJ();
        return sj;
    }

    //具体游戏数据
    public static String name = "";
    public static int level;
    public static int jingyan_have;
    public static int jingyan_need;
    public static int money;

    public static int number_hero = 0;              //所有英雄
    public static int number_fight_hero = 0;        //上阵英雄
    public static int number_wp = 0;              //所有英雄
    public static int number_zb = 0;

    public static Yingxiong yx[];                   //上阵状况
    public static Zhuangbei zb[];
    public static Wupin wp[];                       //背包状况
    public static Wupin ww;


    public SJ(){
        ww = new Wupin();
        wp = new Wupin[200];
        for(int i = 0;i < 200; i++){
            wp[i] = new Wupin();
        }

        yx = new Yingxiong[200];
        for(int i = 0;i < 200; i++){
            yx[i] = new Yingxiong();
            yx[i].gz = new Zhuangbei();
            yx[i].fz = new Zhuangbei();
        }

        zb = new Zhuangbei[200];
        for(int i = 0;i < 200; i++){
            zb[i] = new Zhuangbei();
        }


    }

}


class Yingxiong{
    public  String name = "";
    public  int id;     //哪个英雄？
    public  int level;
    public  int jingyan_have;
    public  int jingyan_need;

    public  int place = 0;   //是否上阵,在哪个位置
    public  int type_have = 0;

    public  int yxhead_img = 0;
    public  int yxbody_img = 0;
    public int jnimg = 0;
    public  String descrption = "";

    public  int attack_start;
    public  int attack;
    public  int protect_start;
    public  int protect;
    public  int blood_start;
    public  int blood;

    public  Zhuangbei gz,fz;

}

class Wupin{
    public  String name = "";
    public  int id = 0;     //那种物品？
    public  int number;
    public  boolean type_have = false;
    public  int wphead_img = 0;
    public  String description = "";
    public Wupin(){
        name = new String();
        description = new String();
    }
}

class Zhuangbei {
    public  String name = "";
    public  int id;     //那种物品？
    public  int number;
    public  boolean type_have = false;
    public int zbhead_img = 0;
    public  String description = "";

    public  String place = "";       //place对应的是战队的三个英雄位置,分别为 a,b,c,ab,ac,bc,abc

    public  int type = 0;             //gz:1,fz:2
    public  int add_attack = 0;
    public  int add_protect = 0;
    public  int add_blood = 0;

    public Zhuangbei(){
        name = new String();
        description = new String();
        place = new String();
    }
}

class Jineng{
    public  String name= "";
    public  String owner= "";
    public  int place;

    public  int jnhead_img = 0;
    public  int add_attack;
    public  int add_protect;
    public  int add_blood;
}



