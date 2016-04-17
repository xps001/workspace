package com.test;

import java.util.Random;
import java.util.Vector;
import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

//播放声音的类
class AePlayWave extends Thread {

	private String filename;
	public AePlayWave(String wavfile) {
		filename = wavfile;

	}

	public void run() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		//这是缓冲
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}

	
}
class Shot implements Runnable{
	int x;
	int y;
	int direct;
	int speed=2;
	boolean isLive=true;
	public Shot(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (direct) {
			case 0:
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;
			case 2:
				y+=speed;
				break;
			case 3:
				x-=speed;
				break;
			}
			if (x<0||x>400||y<0||y>300) {
				this.isLive=false;
				break;
			}
		}
		
	}
}
class Bomb{
	int x,y;
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void lifeDown(){
		if (life>0) {
			life--;
		}else{
			this.isLive=false;
		}
	}
	
}
//坦克
class Tank{
	int x=0;
	int y=0;
	int speed=1;
	int direct=0;
	int color;
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x; 
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
	}
}
class EnemyTank extends Tank implements Runnable{
	boolean isLive=true;
	int times=0;
	Shot s=null;
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	Vector<Shot> ss=new Vector<Shot>();
	public EnemyTank(int x,int y){
		super(x,y);	
	}
	public void shotTank(){
		switch (this.direct) {
		case 0:
			s=new Shot(x+10, y, 0);
			ss.add(s);
			break;
		case 1:
			s=new Shot(x+25, y+15, 1);
			ss.add(s);
			break;
		case 2:
			s=new Shot(x+10, y+30, 2);
			ss.add(s);
			break;
		case 3:
			s=new Shot(x-5, y+15, 3);
			ss.add(s);
			break;
		}
		Thread t=new Thread(s);
		t.start();
	}
	public boolean isMeetOtherTank(){
		boolean ismeet=false;
		switch (this.direct) {
		case 0:
			for (int i = 0; i <ets.size(); i++) {
				EnemyTank et1=ets.get(i);
				if (et1!=this) {
					switch (et1.direct) {
					case 0:
					case 2:
						if (Math.abs(this.x-et1.x)>0&&Math.abs(this.x-et1.x)<20&&Math.abs(this.y-et1.y)<30) {
							ismeet=true;
						}
						break;
					case 1:
					case 3:	
						if (Math.abs(this.x-et1.x)>0&&Math.abs(this.x-et1.x)<20&&Math.abs(this.y-et1.y)<30) {
							ismeet=true;
						}
						break;

					}
				}
			}
			break;
		case 1:
			for (int i = 0; i <ets.size(); i++) {
				EnemyTank et1=ets.get(i);
				if (et1!=this) {
					switch (et1.direct) {
					case 0:
					case 2:
						if (Math.abs(this.x-et1.x)<30&&Math.abs(this.y-et1.y)<30) {
							ismeet=true;
						}
						break;
					case 1:
					case 3:	
						if (Math.abs(this.x-et1.x)<=30&&Math.abs(this.y-et1.y)<20) {
							ismeet=true;
						}
						break;

					}
				}
			}	
			break;
		case 2:
			for (int i = 0; i <ets.size(); i++) {
				EnemyTank et1=ets.get(i);
				if (et1!=this) {
					switch (et1.direct) {
					case 0:
					case 2:
						if (Math.abs(this.x-et1.x)>0&&Math.abs(this.x-et1.x)<20&&Math.abs(this.y-et1.y)<30) {
							ismeet=true;
						}
						break;
					case 1:
					case 3:	
						if (Math.abs(this.x-et1.x)>0&&Math.abs(this.x-et1.x)<20&&Math.abs(this.y-et1.y)<30) {
							ismeet=true;
						}
						break;

					}
				}
			}			
			break;
		case 3:
			for (int i = 0; i <ets.size(); i++) {
				EnemyTank et1=ets.get(i);
				if (et1!=this) {
					switch (et1.direct) {
					case 0:
					case 2:
						if (Math.abs(this.x-et1.x)<20&&Math.abs(this.y-et1.y)<30) {
							ismeet=true;
						}
						break;
					case 1:
					case 3:	
						if (Math.abs(this.x-et1.x)<30&&Math.abs(this.y-et1.y)<20) {
							ismeet=true;
						}
						break;

					}
				}
			}				
			break;
		}
		 return ismeet;
	}
	public void setTanks(Vector<EnemyTank> vv){
		this.ets=vv;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (direct) {
			case 0:
				for (int i = 0; i < 30; i++) {
					if (y>0&&!this.isMeetOtherTank()) {
						y-=speed;	
					}
					try {
						Thread.sleep(30);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}				
				break;
			case 1:
				for (int i = 0; i < 30; i++) {
					if (x<370&&!this.isMeetOtherTank()) {
						x+=speed;	
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				break;
			case 2:
				for (int i = 0; i < 30; i++) {
					if (y<270&&!this.isMeetOtherTank()) {
						y+=speed;	
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				break;
			case 3:
				for (int i = 0; i < 80; i++) {
					if (x>0&&!this.isMeetOtherTank()) {
						x-=speed;	
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				break;
			}
			times++;
			if (times%2==0) {
				if (ss.size()<5) {
					shotTank();	
				}
			}
//			System.out.println("敌人的坦克坐标 ：x:"+x+"y:"+y);		
				this.direct=(int)(Math.random()*4);
			if (this.isLive==false) {
				break;
			}
		}
	}
}
class Hero extends Tank{
	Shot s=null;
	boolean isLive=true;
	Vector<Shot> ss=new Vector<Shot>();
	public Hero(int x,int y){
		super(x,y);	
	}
	public void shotTank(){
		switch (this.direct) {
		case 0:
			s=new Shot(x+10, y, 0);
			ss.add(s);
			break;
		case 1:
			s=new Shot(x+25, y+15, 1);
			ss.add(s);
			break;
		case 2:
			s=new Shot(x+10, y+30, 2);
			ss.add(s);
			break;
		case 3:
			s=new Shot(x-5, y+15, 3);
			ss.add(s);
			break;
		}
		
		Thread t=new Thread(s);
		t.start();
	}
	public void moveUp(){
		y-=speed;
	}
	public void moveRight(){
		x+=speed;
	}
	public void moveDown(){
		y+=speed;
	}
	public void moveLeft(){
		x-=speed;
	}
}