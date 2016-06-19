package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/19.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

import org.w3c.dom.Text;

public class StoreActivity  extends AppCompatActivity {
    public static TextView username;
    public static TextView usermoney;
    public static TextView userlevel;


    private int[] mImgIds = new int[]{R.drawable.wp_baozi_s, R.drawable.wp_biluochun_s,R.drawable.wp_shengjiwan_s,R.drawable.wp_jingyanyaoshui_s,
            R.drawable.wp_jingyanyaogao_s,R.drawable.wp_jingyannailao_s,
    };

    private final static String[] data = {"包子","碧螺春","生机丸","经验药水","经验药膏","经验奶酪",};

    //创建数据源.
    Product[] data1 = new Product[]{
            new Product(mImgIds[0],data[0],30,"恢复30点血量","购买"),
            new Product(mImgIds[1],data[1],90,"恢复100点血量","购买"),
            new Product(mImgIds[2],data[2],170,"恢复200点血量","购买"),
            new Product(mImgIds[3],data[3],30,"增加10点经验","购买"),
            new Product(mImgIds[4],data[4],140,"增加50点经验","购买"),
            new Product(mImgIds[5],data[5],260,"增加100点经验","购买") ,
    };


    String leveltmp;
    String moneytmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity);

        username = (TextView)findViewById(R.id.txtv1);
        username.setText(SJ.name);

        leveltmp = Integer.toString(SJ.level);
        userlevel = (TextView)findViewById(R.id.txtv3);
        userlevel.setText(leveltmp);

        usermoney = (TextView)findViewById(R.id.txtv5);
        moneytmp = Integer.toString(SJ.money);
        usermoney.setText(moneytmp);

        ListView listview = (ListView)findViewById(R.id.store_listview);

        StoreListAdapter adapter = new StoreListAdapter(this, R.layout.store_listview,data1) ;//okay, the resource id is passed.

        listview.setAdapter(adapter);

    }

    @Override
    protected void onStart(){

        super.onStart();
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

    class StoreListAdapter extends ArrayAdapter<Product> {
        private LayoutInflater mInflater;
        String moneytmp;
        int price = 0;

        public StoreListAdapter(Context context, int textViewResourceId,Product[] obj) {
            super(context, textViewResourceId,obj);
            // TODO Auto-generated constructor stub
            this.mInflater = LayoutInflater.from(context);
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if(convertView == null){
                //创建新的view视图.
                convertView = mInflater.inflate(R.layout.store_listview, null); //see above, you can use the passed resource id.
            }

            if(holder==null){
                holder = new ViewHolder();
                //查找每个ViewItem中,各个子View,放进holder中
                holder.head = (ImageView) convertView.findViewById(R.id.store_head);
                holder.name = (TextView) convertView.findViewById(R.id.store_name);
                holder.price = (TextView) convertView.findViewById(R.id.store_price);
                holder.description = (TextView) convertView.findViewById(R.id.store_description);
                holder.buy = (Button) convertView.findViewById(R.id.store_buy);

                //保存对每个显示的ViewItem中, 各个子View的引用对象
                convertView.setTag(holder);
            }
            else// I think this a bug, program can not run here!!!--linc2014.11.12
            {
                holder = (ViewHolder)convertView.getTag();
            }

            //获取当前要显示的数据
            Product person = getItem(position);
            holder.head.setImageResource(person.getHead());

            holder.name.setText(person.getName());
            TextPaint tp = holder.name.getPaint();
            tp.setFakeBoldText(true);

            holder.buy.setText(person.getBuy());
            String pricetmp = Integer.toString(person.getPrice());
            holder.price.setText(pricetmp);
            holder.description.setText(person.getDescription());

            final View finalConvertView = convertView;
            final View finalConvertView1 = convertView;
            holder.buy.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (position == 0){
                        if(SJ.money < 30){
                            Toast.makeText(getContext(), "金钱不足-_-", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SJ.wp[1].type_have = true;
                            if (SJ.wp[1].number == 0)
                                SJ.number_wp++;
                            SJ.wp[1].number++;
                            price = 30;
                            showInfo();
                        }
                    }
                    if (position == 1){
                        if(SJ.money < 90){
                            Toast.makeText(getContext(), "金钱不足-_-", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SJ.wp[2].type_have = true;
                            if (SJ.wp[2].number == 0)
                                SJ.number_wp++;
                            SJ.wp[2].number++;
                            price = 90;
                            showInfo();
                        }
                    }
                    if (position == 2){
                        if(SJ.money < 170){
                            Toast.makeText(getContext(), "金钱不足-_-", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SJ.wp[3].type_have = true;
                            if (SJ.wp[3].number == 0)
                                SJ.number_wp++;
                            SJ.wp[3].number++;
                            price = 170;
                            showInfo();
                        }
                    }
                    if (position == 3){
                        if(SJ.money < 30){
                            Toast.makeText(getContext(), "金钱不足-_-", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SJ.wp[4].type_have = true;
                            if (SJ.wp[4].number == 0)
                                SJ.number_wp++;
                            SJ.wp[4].number++;
                            price = 30;
                            showInfo();
                        }
                    }
                    if (position == 4){
                        if(SJ.money < 140){
                            Toast.makeText(getContext(), "金钱不足-_-", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SJ.wp[5].type_have = true;
                            if (SJ.wp[5].number == 0)
                                SJ.number_wp++;
                            SJ.wp[5].number++;
                            price = 140;
                            showInfo();
                        }
                    }
                    if (position == 5){
                        if(SJ.money < 260){
                            Toast.makeText(getContext(), "金钱不足-_-", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SJ.wp[6].type_have = true;
                            if (SJ.wp[6].number == 0)
                                SJ.number_wp++;
                            SJ.wp[6].number++;
                            price = 260;
                            showInfo();
                        }
                    }

                }


            });

            return convertView;


        }


        private  class ViewHolder
        {
            ImageView head;
            TextView name;
            TextView price;
            TextView description;
            Button buy;
        }

        public void showInfo(){
            new AlertDialog.Builder(getContext())
                    .setTitle("购买提醒")
                    .setMessage("您确定购买么？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SJ.money -= price;
                            moneytmp = Integer.toString(SJ.money);
                            StoreActivity.usermoney.setText(moneytmp);
                            price = 0;
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            price = 0;
                        }
                    })
                    .show();
        }

    }
}
