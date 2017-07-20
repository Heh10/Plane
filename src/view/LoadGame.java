package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Plane.Config;
import Plane.MusicPlayer;

/**
 * ��Ϸ����ҳ��
 * 
 * @author Administrator
 *
 */
public class LoadGame extends JPanel implements Runnable
{

	private static final long serialVersionUID = 1L;
	// ����ͼƬ
	public Image bg;
	// �����ɻ�ͼƬ
	public Image logo_plane, bomb3;
	public Font f;
	// �ɻ���xy
	private int x = 0;
	// �����̶߳���
	private Thread thread;
	// ���ض�����״̬
	private boolean logo_type = true;
	
	public GameJPanel gameJPanel;

	/**
	 * ���췽��
	 * @param gameJPanel 
	 */
	public LoadGame(GameJPanel gameJPanel)
	{
		// ʵ��������ͼƬ
		bg = new ImageIcon(Config.img_bg_level_2).getImage();
		// ��������
		f = new Font("", Font.BOLD, 25);
		
		this.gameJPanel = gameJPanel;
		
		this.setLayout(null);

	}

	/**
	 * ��ʼ�̣߳��ػ�
	 */
	public void logoin()
	{
		MusicPlayer music = new MusicPlayer(Config.loading_sound);
		music.play();
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * ���Ʊ������Զ����ã� ������� �߳��ں�̨����ui repaint
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(bg, 0, 0,480,700, null);

		g.drawImage(new ImageIcon(Config.myPlane).getImage(),(Config.window_width - 116)/2, 30,120,79, null);
		g.setColor(Color.WHITE);
		g.drawRect(19,Config.window_height - 201, 440, 42);
		g.setColor(Color.ORANGE);
		g.fillRect(20,Config.window_height - 200, 440 * x/100, 40);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("΢���ź�",Font.BOLD,40));
		g.drawString("ȫ��ɻ���ս", (Config.window_width - 250)/2, 180);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("��Ϸ������...", 150,400);
	}
	
	/**
	 * �߳�run����
	 */
	
	@Override
	public void run()
	{
		while (logo_type)
		{
			// �޸�x���� �ػ�
			x = x + 1;
			repaint();
			try
			{
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if (x > 100)
			{
				logo_type = false;//�����߳�
			}
		}
		logo_type = true;
		x = 0;
		// ��Ϸҳ��
		PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "gameJPanel");
		// ��ʼ��Ϸ
		gameJPanel.startGame();
	}

}
