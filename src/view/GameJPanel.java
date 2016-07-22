package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Plane.Config;
import Plane.MusicPlayer;
import entity.Boom;
import entity.Boss;
import entity.Bullet;
import entity.Enemy;
import entity.FileIO;
import entity.Plane;
import entity.Property;

/** 
 * 
 *  ��Ϸҳ��
 * 
 */
public class GameJPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener,KeyListener
{
	
	private static final long serialVersionUID = 1L;
	
	//�����Ϸ����ͼ
	public ArrayList<Image> image = new ArrayList<>();
	//�ļ���д
	FileIO io = new FileIO();
	//���߰�ʱ��
	private int fireTime = 0;
	//����
	public int level = 1;
	
	public int bgCount = 0;
	//��ʾ����ͼƬ
	public int nextLevelCount = 0;
	public boolean nextLevel = false;
	//�Ƿ���ͣ��Ϸ
	public static boolean isPause = false;
	// ����
	public Integer number = 0;
	// ����ͼƬ��y����
	private int bg_y = 0;
	// ��Ϸ�߼��߳�
	private Thread thread;
	// ��Ϸ�Ƿ����
	private static boolean isGameOver = true;
	// ��ҷɻ�����
	private Plane myPlane;
	//�Ƿ����Boss
	boolean showBoss = false;
	//�½�Boss����
	public Boss boss;
	// ��������������ӵ�
	private ArrayList<Bullet> my_plane_bullet = new ArrayList<>();
	// ��ŵл�
	private ArrayList<Enemy> enemy = new ArrayList<>();
	//��ű�ըЧ��
	private ArrayList<Boom> boom = new ArrayList<>();
	//��ŵл��ӵ�
	private ArrayList<Bullet> enemy_bullet = new ArrayList<>();
	//�����Ϸ����
	private ArrayList<Property> property = new ArrayList<>();
	//�����ػ����
	private int repaintCount = 1;
	
	public MusicPlayer music = new MusicPlayer(Config.bgm);
	
	/**
	 * 
	 * �߳�run����
	 * 
	 */
	@Override
	public void run()
	{
		while (!isGameOver)
		{	
			repaint();
			//��Ϸ��ͣ
			while(isPause)
			{
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				if(!isPause)
				{
					break;
				}
			}
			
			//�ƶ�����
			bg_y = bg_y + 1;
			if (bg_y >= Config.window_height)
			{
				bg_y = 0;
			}
			try
			{
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//Q��������ȴʱ��
			if(skill_Q > 0)
			{
				skill_Q --;
			}
			//W��������ȴʱ��
			if(skill_W > 0)
			{
				skill_W --;
			}
			//���߰�ʱ��
			if(fireTime > 0)
			{
				fireTime -- ;
			}
			//E������ȴʱ��
			if(skill_E > 0)
			{
				skill_E --;
			}
		}	
	}
	

	/**
	 * 
	 * ���췽��
	 * @param gameJFrame
	 * 
	 */
	public GameJPanel(PlaneJFrame gameJFrame)
	{
		
		//�������
		Image ImageCursor = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(""));
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageCursor, new Point(0, 0), "cursor"));
		//��ҷɻ�
		myPlane = new Plane(0, 0, 120, 79, new ImageIcon(Config.myPlane).getImage());
		//Boss
		boss = new Boss(150,-300,120,113);
		//�����������
		this.addMouseListener(this);
		//��Ӽ��̼���
		gameJFrame.addKeyListener(this);
		//����ƶ�����
		this.addMouseMotionListener(this);
		
		image.add(new ImageIcon(Config.img_bg_level_2).getImage());
		image.add(new ImageIcon(Config.img_bg_level_1).getImage());
		image.add(new ImageIcon(Config.img_bg_level_3).getImage());
		
	}

	/**
	 * ��ʼ��Ϸ
	 */
	public void startGame()
	{
		level = 1;
		isPause = false;
		bgCount = 0;
		skill_Q = 0;
		skill_W = 0;
		skill_E = 0;
		//�����߳�
		if (isGameOver)
		{
			myPlane.x = 200;
			myPlane.y = 600;
			/**
			 * ���¿�ʼ��Ϸ
			 */
			//��Ϸ����״̬
			isGameOver = false;
			thread = new Thread(this);
			//��ʼ�߳�
			thread.start();
			//��������
			music.loop();
		}
	}

	/**
	 * ������Ϸ����
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//������Ϸҳ��ı������ƶ���
		g.drawImage(image.get(bgCount), 0, bg_y, Config.window_width, Config.window_height, null);
		g.drawImage(image.get(bgCount), 0, bg_y - 700, Config.window_width, Config.window_height, null);
		
		if(!nextLevel)
		{
			//���л�
			drawEnemy(g);
			//����ҷɻ����ӵ�
			drawMyBullet(g);
			//���л��ӵ�
			drawEnemyBullet(g);
			//�����߰�
			drawProperty(g);
		}
		//��ʾ����ͼƬ
		else
		{
			g.drawImage(new ImageIcon(Config.guoguan).getImage(), (Config.window_width - 253)/2, (Config.window_height - 91)/2 - 150, 253, 91, null);
			nextLevelCount++;
			if(nextLevelCount == 300)
			{
				repaintCount -= nextLevelCount;
				nextLevel = false;
				nextLevelCount = 0;
				//�������ӵ��͵л��ӵ�����
				my_plane_bullet.clear();
				enemy_bullet.clear();
				//������1
				level++;
				bgCount++;
				if(bgCount == 3)
				{
					bgCount = 0;
				}
			}
		}
		//��ײ���
		Collision(g);
		//����ҷɻ� 
		myPlane.draw(g);
		//������
		drawShield(g);
		//����ըЧ��
		drawBoom(g);
		//���Ʒ���
		drawNumber(g);
		//������ͼ��
		drawSkill(g);
		//�����ܲ�ͼ
		drawSkillPicture(g);
		//�ػ������һ
		repaintCount++;
	}
	
	/**
	 * �����ܲ�ͼ
	 * @param g
	 */
	private void drawSkillPicture(Graphics g)
	{
		if(skill_Q > 400)
		{
			g.drawImage(new ImageIcon(Config.skillQ).getImage(), (Config.window_width - 200)/2, (Config.window_height - 200)/2, 200, 200, null);
		}
		if(skill_W > 400)
		{
			g.drawImage(new ImageIcon(Config.skillW).getImage(), (Config.window_width - 200)/2, (Config.window_height - 200)/2, 200, 200, null);
		}
		if(skill_E > 800)
		{
			g.drawImage(new ImageIcon(Config.skillE).getImage(), (Config.window_width - 200)/2, (Config.window_height - 200)/2, 200, 200, null);
		}
		
	}


	/**
	 * ������
	 * @param g
	 */
	private void drawShield(Graphics g)
	{
		if(skill_E > 500)
		{
			g.drawImage(new ImageIcon(Config.shield).getImage(), myPlane.x - myPlane.w/2, myPlane.y - 65, 120,120,null);
		}
		
	}


	/**
	 * 
	 * �����߰�
	 * @param g
	 */
	private void drawProperty(Graphics g)
	{
		if(repaintCount%1000 == 0)
		{
			//����������߰�
			Property p = new Property(new Random().nextInt(2));
			property.add(p);
		}
		
		//�����߰�
		for(int i = 0; i < property.size(); i++)
		{
			Property p = property.get(i);
			p.draw(g);
			p.move();
			
			//�ж��Ƿ��õ��߰�
			if(myPlane.isGetRect(p.x, p.y, p.w, p.h))
			{
				MusicPlayer music = new MusicPlayer(Config.jiaxue);
				music.play();
				//Ѫ����
				if(p.type == 0)
				{
					property.remove(i);
					if(myPlane.hp <= 90)
					{
						myPlane.hp += 10;
					}
					else
					{
						myPlane.hp = 100;
					}
				}
				//�ӵ���
				if(p.type == 1)
				{
					property.remove(i);
					fireTime = 1000;
				}
			}
			
			//����ײ��Ƴ����߰�
			if(p.x > Config.window_height)
			{
				property.remove(i);
			}
		}
	}


	/**
	 * 
	 * ������ҷɻ��ӵ�
	 * 
	 */
	private void drawMyBullet(Graphics g)
	{
		//����Q�����ӵ�
		if(skill_Q > 300)
		{
			if(repaintCount%20 == 0)
			{
				for(int i = 0; i < 7; i++)
				{
					Bullet bullet = new Bullet(myPlane.x + i * 20 - 68,myPlane.y - 100, 15, 45,new ImageIcon(Config.bossBullet).getImage(),0);
					my_plane_bullet.add(bullet);
				}
			}
		}
		//����W�����ӵ�
		if(skill_W > 300)
		{
			if(repaintCount%20 == 0)
			{
				for (int i = 0; i < 7; i++)
				{
					Bullet b = new Bullet(myPlane.x - 7, myPlane.y - 100, 15, 45, new ImageIcon(Config.bossBullet).getImage(),i - 3);
					my_plane_bullet.add(b);
				}
			}
		}
		
		//�Զ������ӵ�
		if (repaintCount % 20 == 0)
		{
			//������ͨ�ӵ�
			Bullet b = new Bullet(myPlane.x - 7, myPlane.y - 100, 15, 45, new ImageIcon(Config.bossBullet).getImage(),0);
			my_plane_bullet.add(b);
			
			//������õ��߰��ӵ�
			if(fireTime > 0)
			{
				//���Ҹ����������ӵ�
				Bullet b_l = new Bullet(myPlane.x - 7 - 30, myPlane.y - 100, 15, 20, new ImageIcon(Config.feidan).getImage(),0);
				Bullet b_r = new Bullet(myPlane.x - 7 + 30, myPlane.y - 100, 15, 20, new ImageIcon(Config.feidan).getImage(),0);
				my_plane_bullet.add(b_l);
				my_plane_bullet.add(b_r);
			}
		}

		//���ӵ�
		for (int i = 0; i < my_plane_bullet.size(); i++)
		{
			Bullet bullet = my_plane_bullet.get(i);
			bullet.draw(g);
			//�ӵ��ƶ�
			bullet.move(1);
			//�ӵ��Ƴ���Ļ��
			if (bullet.y < 0)
			{
				my_plane_bullet.remove(bullet);
			}
		}
	}
	/**
	 * �����л��ӵ�
	 * @param g
	 */
	public void drawEnemyBullet(Graphics g)
	{
		if(showBoss)
		{
			//Boss���ӵ�
			if (repaintCount % 200 == 0)
			{
				MusicPlayer music = new MusicPlayer(Config.bossfashezidan1);
				music.play();
				//����һ���ӵ�
				if(boss.type == 0 && boss.y == 50)
				{
					for(int i = 0; i < 7; i++)
					{
						Bullet b = new Bullet(boss.x + boss.w/2 + i * 30 - 105, boss.y + boss.h, 15, 45, new ImageIcon(Config.bomb18).getImage(),0);
						enemy_bullet.add(b);
					}
				}
				//���������ӵ�
				if((boss.type == 1 || boss.type == 2 || boss.type == 3 || boss.type == 4 ) && boss.y == 50)
				{
					for(int i = 0; i < 7; i++)
					{
						Bullet b = new Bullet(boss.x + boss.w/2, boss.y + boss.h, 15, 45, new ImageIcon(Config.bomb18).getImage(),i - 3);
						enemy_bullet.add(b);
					}
				}
				
			}
		}
		else
		{
			//�½���ͨ�л��ӵ�
			if (repaintCount % 200 == 0)
			{
				for(int i = 0; i < enemy.size(); i++)
				{
					Enemy e = enemy.get(i);
					Bullet b = new Bullet(e.x + e.w/2, e.y + e.h, 10, 30, new ImageIcon(Config.bomb18).getImage(),0);
					enemy_bullet.add(b);
				}
				
			}
		}
	
		//���ӵ�
		for (int i = 0; i < enemy_bullet.size(); i++)
		{
			Bullet bullet = enemy_bullet.get(i);
			bullet.draw(g);
			//�ӵ��ƶ�
			bullet.move(0);
			//�ӵ��Ƴ���Ļ��
			if (bullet.y > 700)
			{
				enemy_bullet.remove(bullet);
			}
		}
	}

	/**
	 * 
	 * �����л�
	 */
	public void drawEnemy(Graphics g)
	{
		//�½���ͨ�л�
		if(!showBoss)
		{
			if (repaintCount % 50 == 0)
			{
				Enemy e = new Enemy(new Random().nextInt(Config.window_width - 100), -100,new Random().nextInt(5));
				enemy.add(e);
			}
			
			//�ػ������2000����boss
			if(repaintCount%2000 == 0)
			{
				showBoss = true;
				//BossѪ��Ϊ�ػ����
				boss.count = repaintCount;
				boss.countAll = repaintCount;
				//����Bossλ��
				boss.x = (Config.window_width - boss.w)/2;
				boss.y = -200;
			}
		}
		//����Boss
		else
		{
			boss.draw(g);
			//Boss�ƶ�
			boss.move();
		}
		
		//���л�
		for (int i = 0; i < enemy.size(); i++)
		{
			Enemy enemy1 = enemy.get(i);
			enemy1.draw(g);
			//�ƶ��л�
			enemy1.move();
			//��Ļ���Ƴ�
			if (enemy1.x > 700)
			{
				enemy.remove(enemy1);
			}
		}
	}

	

	/**
	 * ��ײ���
	 * @param g
	 */
	public void Collision(Graphics g)
	{
		//�л��ӵ�������ҷɻ�
		for(int i = 0; i < enemy_bullet.size(); i++)
		{
			Bullet b = enemy_bullet.get(i);
			
			if(b.isGetRect(myPlane.x - myPlane.w/2 + 30, myPlane.y - myPlane.h/2 + 30, myPlane.w - 50, myPlane.h - 50))
			{
				if(skill_E < 500)
				{
					//���Ѫ����1
					myPlane.hp --;
					//��ӱ�ըЧ��
					boom.add(new Boom(myPlane.x - 25,myPlane.y - 25,50,50));
				}
				//�Ƴ��ӵ�
				enemy_bullet.remove(b);
					
				//���Ѫ��Ϊ0 �� ��Ϸ����
				if(myPlane.hp <= 0)
				{
					GameOver();
				}
				
			}
			
		}
		//�ӵ�����Boss�͵л�
		for (int i = 0; i < my_plane_bullet.size(); i++)
		{
			Bullet b = my_plane_bullet.get(i);
			
			//Boss��ײ���
			if(showBoss)
			{	
					if(b.isGetRect(boss.x, boss.y, boss.w, boss.h))
					{
						//BossѪ���� 10
						boss.count -= 10;
						//�Ƴ��ӵ�
						my_plane_bullet.remove(b);
						//��ӱ�ըЧ��
						boom.add(new Boom(b.x - 25,b.y - 25,50,50));
					}
					if(boss.isGetRect(myPlane.x - myPlane.w/2, myPlane.y - myPlane.h/2, myPlane.w, myPlane.h))
					{
						//BossѪ���� 10
						boss.count -= 10;
						//���Ѫ����1
						if(skill_E < 500)
						{
							myPlane.hp -= 1;
						}
						//��Ϸ����
						if(myPlane.hp <= 0)
						{
							GameOver();
						}
					}
					if(boss.count <= 0)
					{
						//Boss���ٳ���
						showBoss = false;
						//��ӱ�ըЧ��
						boom.add(new Boom(boss.x, boss.y, 200, 200));
						repaintCount = boss.countAll;
						number += repaintCount;
						//�ı�Boss
						boss.changeBoss();
						//��ʾ����ͼƬ
						nextLevel = true;
						MusicPlayer music = new MusicPlayer(Config.polan);
						music.play();
					}
					
			}
			//���ел�
			for (int j = 0; j < enemy.size(); j++)
			{
				Enemy e = enemy.get(j);
				
				if (b.isGetRect(e.x, e.y, e.w, e.h))
				{
					if(e.blood > 0)
					{
						//���ел��л�Ѫ������
						e.blood -= 50;
						my_plane_bullet.remove(b);
					}
					//�л�Ѫ��Ϊ��
					if(e.blood <= 0)
					{
						//��������100
						number += 100;
						//�Ƴ��л�
						enemy.remove(e);
						//�Ƴ��ӵ�
						my_plane_bullet.remove(b);
						//��ӱ�ըЧ��
						boom.add(new Boom(e.x,e.y,100,100));
						MusicPlayer music = new MusicPlayer(Config.dabaozha);
						music.play();
					}
				}
				
				
				//��ҷɻ���л���ײ
				if(e.isGetRect(myPlane.x - myPlane.w/2 + 30, myPlane.y - myPlane.h/2 + 30, myPlane.w - 50, myPlane.h - 50))
				{
					if(skill_E < 500)
					{
						myPlane.hp --;
					}
					enemy.remove(e);
					boom.add(new Boom(e.x,e.y,100,100));
					//Ѫ��Ϊ0����Ϸ����
					if(myPlane.hp <= 0)
					{
						GameOver();
					}
				}
			}
		}
	}

	/**
	 * 
	 * ��Ϸ����
	 * 
	 */
	public void GameOver()
	{
		FileIO io = new FileIO();
		music.stop();
		isGameOver = true;
		isPause = false;
		boss = new Boss(150,-300,120,113);
		showBoss = false;
		repaintCount = 1;
		fireTime = 0;
		level = 1;
		//��ռ���
		enemy.clear();
		my_plane_bullet.clear();
		enemy_bullet.clear();
		boom.clear();
		property.clear();
		//��������
		skill_Q = 0;
		skill_W = 0;
		skill_E = 0;
		try
		{
			//������д���ļ�
			io.writeNumber(number.toString());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		if(myPlane.hp <= 0)
		{
			int b = JOptionPane.showConfirmDialog(this,"��Ϸ�������Ƿ����¿�ʼ��");
			if(b == JOptionPane.YES_OPTION)
			{
				//��ʼ��Ϸ
				startGame();
			}
			else
			{
				//��ʾ��ӭҳ��
				PlaneJFrame.card.show(PlaneJFrame.mainJPanel,"welcomeJPanel");
			}
		}
		
		//��������Ϊ0
		number = 0;	
		//��Ѫ����Ϊ100
		myPlane.hp = 100;
	}
	
	/**
	 * ����Ϸ�����͹���
	 * @param g
	 */
	public void drawNumber(Graphics g)
	{
		String s = number.toString();
		g.setColor(Color.white);
		g.setFont(new Font("΢���ź�", Font.BOLD, 50));
		g.drawString(s, 10, 50);
		
		g.setFont(new Font("΢���ź�",Font.BOLD,20));
		
		g.drawString("��" + level + "��", 15, 80);
	}
	
	/**
	 * ����ըЧ��
	 */
	public void drawBoom(Graphics g)
	{
		for(int i = 0; i < boom.size(); i++)
		{
			Boom b = boom.get(i);
			
			b.draw(g);
			
			if(repaintCount%10 == 0)
			{
				b.type ++;
			}
			if(b.type == 4)
			{
				boom.remove(b);
			}
		}
	}
	
	/**
	 * ������ͼ��
	 * 
	 * @param g
	 */
	//E������ȴʱ��
	private int skill_E = 0;
	//Q������ȴʱ��
	private int skill_Q = 0;
	//W������ȴʱ��
	private int skill_W = 0;
	
	//������ͼ��
	public void drawSkill(Graphics g)
	{
		//Q����
		g.drawRect(Config.window_width - 81,Config.window_height - 151,52,52);
		g.drawImage(new ImageIcon(Config.m02c).getImage(),Config.window_width - 80,Config.window_height - 150,50,50,null);
		//W����
		g.drawRect(Config.window_width - 81,Config.window_height - 221,52,52);
		g.drawImage(new ImageIcon(Config.m02d).getImage(),Config.window_width - 80,Config.window_height - 220,50,50,null);
		//E����
		g.drawRect(Config.window_width - 81,Config.window_height - 291,52,52);
		g.drawImage(new ImageIcon(Config.shield).getImage(),Config.window_width - 80,Config.window_height - 290,50,50,null);
		//��ʱ��
		if(skill_Q > 0)
		{
			g.setFont(new Font("",Font.BOLD,50));
			g.drawString(String.valueOf(skill_Q/100 + 1), Config.window_width - 70,Config.window_height - 105);
		}
		if(skill_W > 0)
		{
			g.setFont(new Font("",Font.BOLD,50));
			g.drawString(String.valueOf(skill_W/100 + 1), Config.window_width - 70,Config.window_height - 175);
		}
		if(skill_E > 0)
		{
			g.setFont(new Font("",Font.BOLD,50));
			g.drawString(String.valueOf(skill_E/100 + 1), Config.window_width - 70,Config.window_height - 245);
		}
	}
	
	/**
	 * ���ͼ����¼�
	 */
	
	//��������������ӵ�
	@Override
	public void mouseClicked(MouseEvent g)
	{
		//����һ�������ӵ�
//		for (int i = 0; i < 7; i++)
//		{
//			Bullet b = new Bullet(myPlane.x - 7, myPlane.y - 100, 15, 45, new ImageIcon(Config.bossBullet).getImage(),i - 3);
//			my_plane_bullet.add(b);
//		}

	}
	//��ҷɻ��ƶ�
	@Override
	public void mouseMoved(MouseEvent e)
	{
		//��ȡ��������
		int x = e.getX();
		int y = e.getY();
		myPlane.move(x, y);
	}
	//�����¼�
	@Override
	public void keyPressed(KeyEvent e)
	{
		//��Q�������¼�(����һ���ӵ�)
		if(e.getKeyCode() == KeyEvent.VK_Q)
		{
			if(skill_Q == 0)
			{
				skill_Q = 500;
				MusicPlayer music = new MusicPlayer(Config.longshenzhili);
				music.play();
			}
		}
		//��W�������¼�
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			//����һ�������ӵ�
			if(skill_W == 0)
			{
				skill_W = 500;
				MusicPlayer music = new MusicPlayer(Config.jiluo);
				music.play();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_E)
		{
			//����һ�������ӵ�
			if(skill_E == 0)
			{
				skill_E = 900;
				MusicPlayer music = new MusicPlayer(Config.nice);
				music.play();
			}
		}
		
		//�ո���¼�(��ͣ��Ϸ)
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(isPause == true)
			{
				isPause = false;
			}
			else
			{
				isPause = true;
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent g){}
	@Override
	public void mouseExited(MouseEvent g){}
	@Override
	public void mousePressed(MouseEvent g){}
	@Override
	public void mouseReleased(MouseEvent g){}
	@Override
	public void mouseDragged(MouseEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyReleased(KeyEvent e){}
}
