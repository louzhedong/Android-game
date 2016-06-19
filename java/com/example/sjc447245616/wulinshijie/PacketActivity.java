package com.example.sjc447245616.wulinshijie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;


public class PacketActivity  extends AppCompatActivity {

    TextView username;
    TextView userlevel;
    TextView usermoney;
    String leveltmp;
    String moneytmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packet_activity);
    }

    @Override
    protected void onStart(){
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




        /////////////////////

        int allpacket = 0;
        allpacket = 6 + 5 + 19;
        Thing[] data2 = new Thing[allpacket];
        for (int i = 0 ; i < allpacket; i++){
            data2[i] = new Thing();
            data2[i].address = "使用";
            data2[i].email = "出售";
        }

        int numbertmp = 0;
        for(int i = 1; i <= 6; i++) {
            data2[numbertmp].head = SJ.getInstance().wp[i].wphead_img;
            data2[numbertmp].name = SJ.getInstance().wp[i].name;
            data2[numbertmp].number = SJ.getInstance().wp[i].number;
            numbertmp++;
        }

        for(int i = 1; i <= 5; i++) {
            data2[numbertmp].head = SJ.getInstance().yx[i].yxhead_img;
            data2[numbertmp].name = SJ.getInstance().yx[i].name;
            if (SJ.getInstance().yx[i].type_have == 1) {
                data2[numbertmp].number = 1;
            }
            else
                data2[numbertmp].number = 0;
            numbertmp++;
        }

        for(int i = 1; i <= 10; i++){
            data2[numbertmp].head = SJ.getInstance().zb[i].zbhead_img;
            data2[numbertmp].name = SJ.getInstance().zb[i].name;
            data2[numbertmp].number = SJ.getInstance().zb[i].number;
            numbertmp++;
        }

        for(int i = 101; i <= 109; i++){
            data2[numbertmp].head = SJ.getInstance().zb[i].zbhead_img;
            data2[numbertmp].name = SJ.getInstance().zb[i].name;
            data2[numbertmp].number = SJ.getInstance().zb[i].number;
            numbertmp++;
        }



        ListView listview = (ListView)findViewById(R.id.packet_listview);

        ListAdapter adapter = new ListAdapter(this, R.layout.packet_listview,data2) ;//okay, the resource id is passed.

        listview.setAdapter(adapter);

        super.onStart();

    }


    class ListAdapter extends ArrayAdapter<Thing> {
        private LayoutInflater mInflater;
        private ImageButton[] imgbtn;

        public ListAdapter(Context context, int textViewResourceId,Thing[] obj) {
            super(context, textViewResourceId,obj);
            // TODO Auto-generated constructor stub
            this.mInflater = LayoutInflater.from(context);
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                //创建新的view视图.
                convertView = mInflater.inflate(R.layout.packet_listview, null); //see above, you can use the passed resource id.
            }

            imgbtn = new ImageButton[]{null,null,null,null,null,
                    null,null,null,null,null};
            ViewHolder holder = null;

            if(holder==null){
                holder = new ViewHolder();
                //查找每个ViewItem中,各个子View,放进holder中
                holder.head = (ImageView) convertView.findViewById(R.id.person_head);

                holder.name = (TextView) convertView.findViewById(R.id.person_name);
                TextPaint tp = holder.name.getPaint();
                tp.setFakeBoldText(true);

                holder.number = (TextView) convertView.findViewById(R.id.person_number);
                holder.email = (Button) convertView.findViewById(R.id.person_email);
                holder.address = (Button) convertView.findViewById(R.id.person_address);
                //保存对每个显示的ViewItem中, 各个子View的引用对象
                convertView.setTag(holder);
            }
            else// I think this a bug, program can not run here!!!--linc2014.11.12
            {
                holder = (ViewHolder)convertView.getTag();
            }

            //获取当前要显示的数据
            Thing person = getItem(position);

            holder.head.setImageResource(person.getHead());
            holder.name.setText(person.getName());
            holder.number.setText(String.valueOf(person.getNumber()));
            holder.email.setText(person.getEmail());
            holder.address.setText(person.getAddress());

            //使用
            holder.email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        if (SJ.money < 30) {

                        } else {


                        }
                    }
                    if (position == 1) {
                        if (SJ.money < 90) {
                        } else {


                        }
                    }
                    if (position == 2) {
                        if (SJ.money < 170) {

                        } else {


                        }
                    }
                    if (position == 3) {
                        if (SJ.money < 30) {

                        } else {


                        }
                    }
                    if (position == 4) {
                        if (SJ.money < 140) {

                        } else {

                        }
                    }
                    if (position == 5) {
                        if (SJ.money < 260) {

                        } else {

                        }
                    }

                }

            });

            //出售
            holder.address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        if (SJ.money < 30) {

                        } else {


                        }
                    }
                    if (position == 1) {
                        if (SJ.money < 90) {
                        } else {


                        }
                    }
                    if (position == 2) {
                        if (SJ.money < 170) {

                        } else {


                        }
                    }
                    if (position == 3) {
                        if (SJ.money < 30) {

                        } else {


                        }
                    }
                    if (position == 4) {
                        if (SJ.money < 140) {

                        } else {

                        }
                    }
                    if (position == 5) {
                        if (SJ.money < 260) {

                        } else {

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
            TextView number;
            Button email;
            Button address;
        }


    }

}
