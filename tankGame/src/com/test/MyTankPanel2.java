package com.test;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class MyTankPanel2 extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	MyPanel mp=null;
	MyStartPanel mps=null;
	JMenuBar jmb=null;
	JMenu jm=null;
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankPanel2 mtg=new MyTankPanel2();
	}
	public MyTankPanel2(){ 
		/**/
		jmb=new JMenuBar();
		jm=new JMenu("游戏（G）");
		jm.setMnemonic('G');
		jmi1=new JMenuItem("开始新游戏");
		jmi2=new JMenuItem("退出");
		jmi3=new JMenuItem("存盘退出");
		jmi4=new JMenuItem("继续游戏");
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi2.setActionCommand("exitgame");
		jmi3.setActionCommand("saveandexit");
		jmi4.setActionCommand("contigame");
		jm.add(jmi1);
		jm.add(jmi2);
		jm.add(jmi3);
		jm.add(jmi4);
		jmb.add(jm);
		this.setJMenuBar(jmb);
		mps=new MyStartPanel();
		Thread t=new Thread(mps);
		t.start();
		this.add(mps);
		this.setSize(400,300); 
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("newgame")){
			Record.getRecord();
			mp=new MyPanel();
			Thread t=new Thread(mp);
			t.start();
			this.remove(mps);
			this.add(mp);
			this.addKeyListener(mp);
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exitgame")){
			Record.save();
			System.exit(0);
		}else if(e.getActionCommand().equals("saveandexit")){
			Record.setEts(mp.ets);
			Record.saveandexit();
			System.exit(0);
		}else if(e.getActionCommand().equals("contigame")){
			Record.isnewgame=false;
			Record.contigame();
			mp=new MyPanel();
			Thread t=new Thread(mp);
			t.start();
			this.remove(mps);
			this.add(mp);
			this.addKeyListener(mp);
			this.setVisible(true);
		}
	}

}
//我的面板
 class MyStartPanel extends JPanel implements Runnable{

	/**
	 * @param args
	 */
	int times=0;
	public void paint(Graphics g){
		super.paint(g);
		if (times%2==1) {
			g.fillRect(0, 0, 400, 300);
			Font ft=new Font("华文彩云",Font.BOLD,30);
			g.setFont(ft);
			g.setColor(Color.yellow);
			g.drawString("stage: 1", 150, 150);
		}	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	while (true) {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		times++;	
		this.repaint();	
	}		
	}


}
 class Record{
	 public static boolean isnewgame=true;
	 public static int enemyNum=6;
   	 public static int myLife=3;
	 public static int shotEnNum=0;
	 private static FileWriter fw=null;
	 private static BufferedWriter bw=null;
	 private static FileReader fr=null;
	 private static BufferedReader br=null;
	 private static Vector<EnemyTank> ets=null;
	 public static Vector<EnemyTank> getEts() {
		return ets;
	}
	public static void setEts(Vector<EnemyTank> ets) {
		Record.ets = ets;
	}
	public static void save(){
		 try {
			fw=new FileWriter("d:\\myRecord.txt");
			bw=new BufferedWriter(fw);
			bw.write(shotEnNum+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	 }
	public static void saveandexit(){
		String saandex="";
		for(int i=0;i<Record.getEts().size();i++){
			EnemyTank et=Record.getEts().get(i);
			saandex+=et.x+" "+et.y+" "+et.direct+"\r\n";
		}
		 try {
				fw=new FileWriter("d:\\myRecord.txt");
				bw=new BufferedWriter(fw);
				bw.write(Record.enemyNum+"\r\n");
				bw.write(saandex);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					bw.close();
					fw.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	 public static void getRecord(){
			try {
				fr=new FileReader("d:\\myRecord.txt");
				br=new BufferedReader(fr);	
				String n=br.readLine();
				shotEnNum=Integer.parseInt(n);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	 }
	 public static void contigame(){
			try {
				fr=new FileReader("d:\\myRecord.txt");
				br=new BufferedReader(fr);	
				String n=br.readLine();
				String str="";
				String strs[]=null;
				Vector<EnemyTank> ets=new Vector<EnemyTank>();
				int x=0;
				int y=0; 
				int direct=0;
				shotEnNum=Integer.parseInt(n);
				while((str=br.readLine())!=null){
					strs=str.split(" ");
					x=Integer.parseInt(strs[0]);
					y=Integer.parseInt(strs[1]);
					direct=Integer.parseInt(strs[2]);
					EnemyTank et=new EnemyTank(x,y);
					et.setDirect(direct);
					ets.add(et);
				}
				Record.setEts(ets);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
	 }
	 public static int getEnemyNum() {
		return enemyNum;
	}
	public static void setEnemyNum(int enemyNum) {
		Record.enemyNum = enemyNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Record.myLife = myLife;
	}
	public static int getShotAllEnNum() {
		return shotEnNum;
	}
	public static void setShotAllEnNum(int shotAllEnNum) {
		Record.shotEnNum = shotAllEnNum;
	}
	public static void addEnNum(){
		shotEnNum++;
	}
	public static void shotEnemy(){
		enemyNum--;
	}
	public static void shotme(){
		myLife--;
	}
	 
 }
class  MyPanel extends JPanel implements KeyListener,Runnable{
	Hero hero=null;
	Image image1=null;
	Image image2=null;
	Image image3=null;
	Vector<Bomb> bombs=new Vector<Bomb>();
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	EnemyTank et=null;
	int ensize=6;
	public MyPanel(){
		AePlayWave apw=new AePlayWave("d:/workspace/tankGame/111.wav");
		apw.start();
		hero=new Hero(100, 100);
/* 		try {
			image1=ImageIO.read(new File("/bomb_1.gif"));
			image2=ImageIO.read(new File("/bomb_2.gif"));
			image3=ImageIO.read(new File("/bomb_3.gif"));s
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		if(Record.isnewgame){
			for(int i=0;i<ensize;i++){
				EnemyTank et=new EnemyTank((i+1)*50, 0);
				et.setColor(0);
				et.setDirect(2);
				et.setTanks(ets);
				ets.add(et);
				Thread t=new Thread(et);
				t.start();
			}	
		}else{
			for(int i=0;i<Record.getEts().size();i++){
				Record.getEts().get(i).setTanks(ets);
				ets.add(Record.getEts().get(i));
				Thread t=new Thread(Record.getEts().get(i));
				t.start();	
			}
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		this.showinfo(g);
		if (hero.isLive) {
			this.drawTank(hero.getX(), hero.getY(), g, hero.direct, 1);			
		}
		for (int i = 0; i < this.hero.ss.size(); i++) {
			Shot myShot=this.hero.ss.get(i);
			if (myShot!=null&&myShot.isLive!=false) {
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			if (myShot.isLive==false) {
				this.hero.ss.remove(myShot);
			}
		}
		for (int i = 0; i < bombs.size(); i++) {
			Bomb bomb=bombs.get(i);
			if (bomb.life>6) {
				g.drawImage(image1, bomb.x, bomb.y, 30, 30,this);
			}else if(bomb.life>3){
				g.drawImage(image2, bomb.x, bomb.y, 30, 30,this);		
			}else if(bomb.life>0){
				g.drawImage(image3, bomb.x, bomb.y, 30, 30,this);
			}
			bomb.lifeDown();
			if (bomb.life==0) {
				bombs.remove(bomb);				
			}
		}
		for(int i=0;i<ets.size();i++){
			EnemyTank et=ets.get(i);
			if (et.isLive==false) {
				ets.remove(et); 
				Record.addEnNum();
				Record.shotEnemy();
				
			}
			for (int j = 0; j < et.ss.size(); j++) {
				Shot tShot=et.ss.get(j);				
				if (tShot!=null&&tShot.isLive!=false) {
					g.draw3DRect(tShot.x, tShot.y, 1, 1, false);
				}
				if (tShot.isLive==false) {
					et.ss.remove(tShot);
				}
			}
			this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
		}
	}
	public void showinfo(Graphics g){
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Record.getEnemyNum()+"", 110, 350);
		this.drawTank(130, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Record.getMyLife()+"", 160, 350);
		
		Font f=new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("您的总成绩是：", 400, 30);
		this.drawTank(420, 40, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Record.getShotAllEnNum()+"", 460, 60);
		
	}
	public void hitEnemy(Shot s,EnemyTank et){
		switch (et.direct) {
		case 0:
		case 2:
               if (s.x>(et.x)&&s.x<(et.x+20)&&s.y>(et.y+5)&&s.y<(et.y+35)) {
				s.isLive=false;
				et.isLive=false;			
				Bomb bomb=new Bomb(et.x, et.y);
				bombs.add(bomb);	
			}		
			break;
		case 1:
		case 3:
            if (s.x>(et.x-5)&&s.x<(et.x+25)&&s.y>(et.y+5)&&s.y<(et.y+25)) {
				s.isLive=false;
				et.isLive=false;
				Bomb bomb=new Bomb(et.x, et.y);
				bombs.add(bomb);	
			}				
			break;

		}

	}
	public void hitHero(Shot s,Hero hr){
		switch (hr.direct) {
		case 0:
		case 2:
               if (s.x>(hr.x)&&s.x<(hr.x+20)&&s.y>(hr.y+5)&&s.y<(hr.y+35)) {
				s.isLive=false;
				hr.isLive=false;
				Record.shotme();
				Bomb bomb=new Bomb(hr.x, hr.y);
				bombs.add(bomb);	
			}		
			break;
		case 1:
		case 3:
            if (s.x>(hr.x-5)&&s.x<(hr.x+25)&&s.y>(hr.y+5)&&s.y<(hr.y+25)) {
				s.isLive=false;
				hr.isLive=false;
				Record.shotme();
				Bomb bomb=new Bomb(hr.x, hr.y);
				bombs.add(bomb);	
			}				
			break;

		}

	}
	public void drawTank(int x,int y,Graphics g,int dir,int type){
		switch(type){
		case 0:
		    g.setColor(Color.cyan);
		    break;
		case 1:
			g.setColor(Color.yellow);  
			break;
		}
		switch(dir){
		case 0:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15, y, 5, 30,false);
			g.fill3DRect(x+5,y+5, 10, 20,false);
			g.fillOval(x+5,y+10, 10, 10);
			g.drawLine(x+10, y, x+10, y+15);
			break;
		case 1:
			g.fill3DRect(x-5, y+5, 30,5,false);
			g.fill3DRect(x-5, y+20, 30, 5,false);
			g.fill3DRect(x,y+10, 20, 10,false);
			g.fillOval(x+5,y+10, 10, 10);
			g.drawLine(x+25,y+15,x+10,y+15);
			break;
		case 2:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15, y, 5, 30,false);
			g.fill3DRect(x+5,y+5, 10, 20,false);
			g.fillOval(x+5,y+10, 10, 10);
			g.drawLine(x+10, y+30, x+10, y+15);
			break;
		case 3:
			g.fill3DRect(x-5, y+5, 30,5,false);
			g.fill3DRect(x-5, y+20, 30, 5,false);
			g.fill3DRect(x,y+10, 20, 10,false);
			g.fillOval(x+5,y+10, 10, 10);
			g.drawLine(x-5,y+15,x+10,y+15);
			break;
		}  
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W){
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_D){
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		if (e.getKeyCode()==KeyEvent.VK_J) {
			if(this.hero.ss.size()<=4){
				this.hero.shotTank();	
			}
		}
		this.repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
			for (int i = 0; i < hero.ss.size(); i++) {
				Shot myShot=hero.ss.get(i);
				if (myShot.isLive) {
					for (int j = 0; j < ets.size(); j++) {
						EnemyTank et=ets.get(j);
						if (et.isLive) {
							hitEnemy(myShot, et);
						}
					}
				}
			}
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et=ets.get(i);
				for (int j = 0; j < et.ss.size(); j++) {
					Shot enemShot= et.ss.get(j);
					if (enemShot.isLive) {
						if (hero.isLive) {
							hitHero(enemShot,hero);
						}
					}
				}
			}
			this.repaint();
		}
	}
}
