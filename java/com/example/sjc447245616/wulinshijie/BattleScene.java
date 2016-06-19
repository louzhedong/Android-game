package com.example.sjc447245616.wulinshijie;

import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCProgressTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class BattleScene extends Activity {
	
	private CCGLSurfaceView view = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new CCGLSurfaceView(this);
		
		setContentView(view);
	
		CCDirector director = CCDirector.sharedDirector();	//�õ�CCDirect����
		director.attachInView(view);			//���õ�ǰ��Ϸ��������ʹ�õ�view����
//		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		
//		director.setDisplayFPS(true);		//������Ϸ�Ƿ���ʾFPSֵ
		director.setAnimationInterval(1/30.0);		//������Ϸ��FPSֵ����Ⱦһ֡��������Ҫ��ʱ��
		CCScene scene = CCScene.node();			//���ɳ�������
		//���ɲ��������
		//�������������ӵ�������
		scene.addChild(new BattleLayer());
		//���г���
		director.runWithScene(scene);
		
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run(){
				BattleScene.this.finish();
			}
		};
		timer.schedule(task, 1000*10);
	}
	
	static  class BattleLayer extends CCLayer{
		
		//����һ���������
				CCSprite player1;
				CCSprite player2;
				CCSprite player3;
				CCSprite monster1;
				CCSprite monster2;
				CCSprite monster3;
				CCSprite background;
				CCSprite startbattle;
				CCProgressTimer _player1;
				CCProgressTimer _player2;
				CCProgressTimer _player3;
				CCProgressTimer _monster1;
				CCProgressTimer _monster2;
				CCProgressTimer _monster3;
		
		public BattleLayer(){
			CGSize s = CCDirector.sharedDirector().winSize();
			CGPoint center = CGPoint.ccp(s.width/2, s.height/2);
			
			//��ʼս��ͼ��
			startbattle = CCSprite.sprite("startbattle1.png");
			this.addChild(startbattle,1);
			startbattle.setPosition(CGPoint.make(s.width/2, s.height/2));
			
			//ս������ͼƬ
			background = CCSprite.sprite("forest_back.png");
			this.addChild(background, 0);
			background.setPosition(center);
			
			//����Ԫ��1
			//��ʼ���������
			player1 = CCSprite.sprite("shuilinxiao.png");
			//���þ�������λ��
			CGPoint point1 = CGPoint.ccp(s.width/4,s.height/2);
			player1.setPosition(point1);
			//�������������������㵱��
			this.addChild(player1);
			
			_player1 = CCProgressTimer.progress("blood.png");
			_player1.setPosition(s.width/4, s.height/40+s.height/2);
			_player1.setPercentage(100);
			this.addChild(_player1);
			
			
			//����Ԫ��2
			player2 = CCSprite.sprite("longaotianxiao.png");
			CGPoint point2 = CGPoint.ccp(s.width/4,s.height*3/4);
			player2.setPosition(point2);
			this.addChild(player2);
			
			_player2 = CCProgressTimer.progress("blood.png");
			_player2.setPosition(s.width/4,s.height*3/4+s.height/40);
			_player2.setPercentage(100);
			this.addChild(_player2);
			
			
			//����Ԫ��3
			player3 = CCSprite.sprite("jiankexiao.png");
			CGPoint point3 = CGPoint.ccp(s.width/4,s.height/4);
			player3.setPosition(point3);
			this.addChild(player3);
			
			_player3 = CCProgressTimer.progress("blood.png");
			_player3.setPosition(s.width/4, s.height/4+s.height/40);
			_player3.setPercentage(100);
			this.addChild(_player3);
			
			//����Ԫ��1
			monster1 = CCSprite.sprite("xiaomoguxiao.png");
			CGPoint point4 = CGPoint.ccp(s.width*3/4,s.height/2);
			monster1.setPosition(point4);
			this.addChild(monster1);
			
			_monster1 = CCProgressTimer.progress("blood.png");
			_monster1.setPosition(s.width*3/4,s.height/2+s.height/40);
			_monster1.setPercentage(100);
			this.addChild(_monster1);
			
			
			
			//����Ԫ��2
			monster2 = CCSprite.sprite("xiaomoguxiao.png");
			CGPoint point5 = CGPoint.ccp(s.width*3/4, s.height*3/4);
			monster2.setPosition(point5);
			this.addChild(monster2);
			
			_monster2 = CCProgressTimer.progress("blood.png");
			_monster2.setPosition(s.width*3/4,s.height*3/4+s.height/40);
			_monster2.setPercentage(100);
			this.addChild(_monster2);
			
			
			//����Ԫ��3
			monster3 = CCSprite.sprite("xiaomoguxiao.png");
			CGPoint point6 = CGPoint.ccp(s.width*3/4, s.height/4);
			monster3.setPosition(point6);
			this.addChild(monster3);
	
			_monster3 = CCProgressTimer.progress("blood.png");
			_monster3.setPosition(s.width*3/4,s.height/4+s.height/40);
			_monster3.setPercentage(100);
			this.addChild(_monster3);
			
			//��ʼս��ͼ����ת
			CCRotateBy rot = CCRotateBy.action(2, 720);
			//��ʼս��ͼ����޵���
			CCFadeIn fadin = CCFadeIn.action(2);
			//CCScaleTo scale2 = CCScaleTo.action(2f, 10f, 10f);
			
			CCFadeOut fadout =  CCFadeOut.action(2);
			
			CCMoveBy movby1 = CCMoveBy.action(1, CGPoint.make(0,0));
			
			CCSpawn spawn1 = CCSpawn.actions(rot, fadin);
			CCSequence seq_startbattle = CCSequence.actions(spawn1,movby1, fadout);
			startbattle.runAction(seq_startbattle);
			
			//���ﾫ��player1����
			CGPoint player_move1 = CGPoint.ccp(s.width/2-s.width/20, 0);
			CGPoint uplayer_move1 = CGPoint.ccp(s.width/20-s.width/2, 0);
			CCMoveBy player_moveby1 = CCMoveBy.action((float) 0.2, player_move1);
			CCMoveBy moveby21 = CCMoveBy.action(5, CGPoint.make(0,0));
			CCMoveBy stay1 = CCMoveBy.action((float) 0.1, CGPoint.make(0, 0));
			CCMoveBy uplayer_moveby1 = CCMoveBy.action((float) 0.2, uplayer_move1);
			CCSequence seq_player1 = CCSequence.actions(moveby21, player_moveby1,stay1,uplayer_moveby1);
			
			CCProgressTo to1 = CCProgressTo.action((float) 0.01, 30);	//monster1��Ѫ���仯
			CCMoveBy to_moveby = CCMoveBy.action((float) 5.25, CGPoint.make(0,0));
			CCSequence seq_monster1_to = CCSequence.actions(to_moveby, to1);
			_monster1.runAction(seq_monster1_to);
			player1.runAction(seq_player1);
			
			_player1.runAction(seq_player1.copy());
			
		
			//���ﾫ��player2����
			
			CGPoint player_move2 = CGPoint.ccp(s.width/2-s.width/20, 0);
			CGPoint uplayer_move2 = CGPoint.ccp(s.width/20-s.width/2, 0);
			CCMoveBy player_moveby2 = CCMoveBy.action((float) 0.2, player_move2);
			CCMoveBy moveby22 = CCMoveBy.action((float) 5.5, CGPoint.make(0,0));
			CCMoveBy stay2 = CCMoveBy.action((float) 0.1, CGPoint.make(0, 0));
			CCMoveBy uplayer_moveby2 = CCMoveBy.action((float) 0.2, uplayer_move2);
			CCSequence seq_player2 = CCSequence.actions(moveby22, player_moveby2,stay2,uplayer_moveby2);
			
			CCProgressTo to2 = CCProgressTo.action((float) 0.01,40);
			CCMoveBy to_moveby2 =CCMoveBy.action((float) 5.75,CGPoint.make(0, 0));
			CCSequence seq_monster2_to = CCSequence.actions(to_moveby2, to2);
			_monster2.runAction(seq_monster2_to);
			
			player2.runAction(seq_player2);
		
			_player2.runAction(seq_player2.copy());
			
			//���ﾫ��monster1����
			
			CGPoint player_move3 = CGPoint.ccp(s.width/20-s.width/2, 0);
			CGPoint uplayer_move3 = CGPoint.ccp(s.width/2-s.width/20, 0);
			CCMoveBy player_moveby3 = CCMoveBy.action((float) 0.2, player_move3);
			CCMoveBy stay3 = CCMoveBy.action((float) 0.1, CGPoint.make(0, 0));
			CCMoveBy moveby23 = CCMoveBy.action(6, CGPoint.make(0,0));
			CCMoveBy uplayer_moveby3 = CCMoveBy.action((float) 0.2, uplayer_move3);
			CCSequence seq_monster1 = CCSequence.actions(moveby23, player_moveby3,stay3,uplayer_moveby3);
			
			CCProgressTo to3 = CCProgressTo.action((float) 0.01, 20);
			CCMoveBy to_moveby3 = CCMoveBy.action((float) 6.25, CGPoint.make(0,0));
			CCSequence seq_player1_to = CCSequence.actions(to_moveby3, to3);
			_player1.runAction(seq_player1_to);
			monster1.runAction(seq_monster1);
			_monster1.runAction(seq_monster1.copy());
			
			//���ﾫ��monster3����
			
			CGPoint player_move4 = CGPoint.ccp(s.width/20-s.width/2, 0);
			CGPoint uplayer_move4 = CGPoint.ccp(s.width/2-s.width/20, 0);
			CCMoveBy player_moveby4 = CCMoveBy.action((float) 0.2, player_move4);
			CCMoveBy stay4 = CCMoveBy.action((float) 0.1, CGPoint.make(0, 0));
			CCMoveBy moveby24 = CCMoveBy.action((float) 6.5, CGPoint.make(0,0));
			CCMoveBy uplayer_moveby4 = CCMoveBy.action((float) 0.2, uplayer_move4);
			CCSequence seq_monster3 = CCSequence.actions(moveby24, player_moveby4,stay4,uplayer_moveby4);
			
			CCProgressTo to4 = CCProgressTo.action((float) 0.01,20);
			CCMoveBy to_moveby4 = CCMoveBy.action((float) 6.25, CGPoint.make(0, 0));
			CCSequence seq_play3_to = CCSequence.actions(to_moveby4, to4);
			_player3.runAction(seq_play3_to);
			monster3.runAction(seq_monster3);
			_monster3.runAction(seq_monster3.copy());
			
			
			//���ﾫ��player3����
			
			CGPoint player_move5 = CGPoint.ccp(s.width/2-s.width/20, 0);
			CGPoint uplayer_move5 = CGPoint.ccp(s.width/20-s.width/2, 0);
			CCMoveBy player_moveby5 = CCMoveBy.action((float) 0.2, player_move5);
			CCMoveBy stay5 = CCMoveBy.action((float) 0.1, CGPoint.make(0, 0));
			CCMoveBy moveby25 = CCMoveBy.action((float) 7, CGPoint.make(0,0));
			CCMoveBy uplayer_moveby5 = CCMoveBy.action((float) 0.2, uplayer_move5);
			CCSequence seq_player3 = CCSequence.actions(moveby25, player_moveby5,stay5,uplayer_moveby5);
			
			CCProgressTo to5 = CCProgressTo.action((float) 0.01,20);
			CCMoveBy to_moveby5 = CCMoveBy.action((float) 6.75, CGPoint.make(0, 0));
			CCSequence seq_monster3_to = CCSequence.actions(to_moveby5, to5);
			_monster3.runAction(seq_monster3_to);
			
			player3.runAction(seq_player3);
			_player3.runAction(seq_player3.copy());
			
			//���ﾫ��monster2����
			
			CGPoint player_move6 = CGPoint.ccp(s.width/20-s.width/2, 0);
			CGPoint uplayer_move6 = CGPoint.ccp(s.width/2-s.width/20, 0);
			CCMoveBy player_moveby6 = CCMoveBy.action((float) 0.2, player_move6);
			CCMoveBy stay6 = CCMoveBy.action((float) 0.1, CGPoint.make(0, 0));
			CCMoveBy moveby26 = CCMoveBy.action((float) 7, CGPoint.make(0,0));
			CCMoveBy uplayer_moveby6 = CCMoveBy.action((float) 0.2, uplayer_move6);
			CCSequence seq_monster2 = CCSequence.actions(moveby26, player_moveby6,stay6,uplayer_moveby6);
			
			CCProgressTo to6 = CCProgressTo.action((float) 0.01,20);
			CCMoveBy to_moveby6 = CCMoveBy.action((float) 7.25, CGPoint.make(0, 0));
			CCSequence seq_play2_to = CCSequence.actions(to_moveby6, to6);
			_player2.runAction(seq_play2_to);
			
			monster2.runAction(seq_monster2);
			_monster2.runAction(seq_monster2.copy());
			
		}
	}
	
	
	public void onStart(){
		super.onStart();

	}

	public void onPause() {
    	super.onPause();
    	CCDirector.sharedDirector().onPause();
    }

    // call got rejected
    public void onResume() {
    	super.onResume();
        CCDirector.sharedDirector().onResume();
    }

    public void onDestroy() {
    	super.onDestroy();
    	CCDirector.sharedDirector().end();
    }
}
