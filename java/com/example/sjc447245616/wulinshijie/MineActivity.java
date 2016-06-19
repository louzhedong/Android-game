package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/20.
 */
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
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

public class MineActivity  extends AppCompatActivity {
    TextView username;
    TextView userlevel;
    TextView usermoney;
    String leveltmp;
    String moneytmp;

    TextView usernamec;
    TextView userlevelc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_activity);
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

        /////////////////////////////
        usernamec = (TextView)findViewById(R.id.mine_namec);
        usernamec.setText(SJ.name);

        userlevelc = (TextView)findViewById(R.id.mine_levelc);
        userlevelc.setText(leveltmp);
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