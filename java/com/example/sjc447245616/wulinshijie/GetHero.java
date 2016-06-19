package com.example.sjc447245616.wulinshijie;

import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.actions.instant.CCPlace;
import org.cocos2d.actions.interval.CCFadeIn;
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

public class GetHero extends Activity {

		private CCGLSurfaceView view = null;
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
		scene.addChild(new HeroLayer());
		//���г���
		director.runWithScene(scene);
		
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run(){
				GetHero.this.finish();
			}
		};
		timer.schedule(task, 1000*5);
	}
	
	static  class HeroLayer extends CCLayer{
		
		//����һ���������
				CCSprite hero;
				CCSprite background;
		
		public HeroLayer(){
			CGSize s = CCDirector.sharedDirector().winSize();
			
			CGPoint center = CGPoint.ccp(s.width/2, s.height/2);
			//����ͼƬ
			CGPoint zero =CGPoint.ccp(160, 240);
			background = CCSprite.sprite("heroback.png");
			this.addChild(background, 0);
			background.setPosition(zero);
			
			hero = CCSprite.sprite("longaotianda.png");
			CGPoint initpoint = CGPoint.ccp(1000, 1000);
			hero.setPosition(initpoint);
			this.addChild(hero);
			
			
			CCPlace palce = CCPlace.action(center);
//			CCScaleTo scaleto = CCScaleTo.action(2, 2);
			CCFadeIn fadein = CCFadeIn.action(2);
			CCRotateBy rotateby = CCRotateBy.action(2, 720);
			
			CCSpawn spawn = CCSpawn.actions( fadein,rotateby);
			
			CCSequence seq = CCSequence.actions(palce, spawn);
			
			hero.runAction(seq);
			
			
			
			
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
