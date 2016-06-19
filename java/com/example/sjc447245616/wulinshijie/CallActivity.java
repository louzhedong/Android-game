package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/19.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sjc447245616.wulinshijie.R;
import com.plattysoft.leonids.ParticleSystem;

import org.w3c.dom.Text;

public class CallActivity  extends AppCompatActivity {
    Button btn1;
    TextView username;
    TextView userlevel;
    TextView usermoney;
    String leveltmp;
    String moneytmp;
    ImageView yxbig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        finish();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_activity);

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

        yxbig = (ImageView)findViewById(R.id.txtv_call_big);
        yxbig.setImageResource(R.drawable.wenhao_b);

        btn1 = (Button)findViewById(R.id.btn_call);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SJ.money < 500){
                    Toast.makeText(CallActivity.this, "金钱不足-_-", Toast.LENGTH_SHORT).show();
                }
                else {
                    SJ.money -= 500;
                    moneytmp = Integer.toString(SJ.money);
                    usermoney.setText(moneytmp);
                    SJ.getInstance().yx[2].level = 1;
                    SJ.getInstance().yx[2].jingyan_have = 0;
                    SJ.getInstance().yx[2].jingyan_need = 30;
                    SJ.getInstance().yx[2].place = 0;
                    SJ.getInstance().yx[2].gz.id = 0;
                    SJ.getInstance().yx[2].fz.id = 0;
                    SJ.getInstance().yx[2].type_have = 1;
                    SJ.number_hero++;
                    Toast.makeText(CallActivity.this, "金钱不足-_-" + SJ.number_hero, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(CallActivity.this, GetHero.class);
                    startActivity(intent);
                }
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
