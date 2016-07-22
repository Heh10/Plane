package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Plane.Config;

public class HelpJPanel extends JPanel
{
	/**
	 * ����ҳ��
	 */
	private static final long serialVersionUID = -5506961801477244517L;

	/**
	 * ���췽��
	 */
	public HelpJPanel()
	{
		//���ò���
		this.setLayout(null);
		
		JButton button = new PlaneButton("������ҳ");
		
		button.setBounds((Config.window_width - 160)/2,Config.window_height - 150,160,45);
		
		this.add(button);
		
		button.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "welcomeJPanel");
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new ImageIcon(Config.img_bg_level_2).getImage(),0,0,Config.window_width,Config.window_height,null);
		g.setColor(Color.white);
		g.drawRect(19,149,52,52);
		g.drawImage(new ImageIcon(Config.m02c).getImage(),20,150,50,50,null);
		g.drawRect(19,249,52,52);
		g.drawImage(new ImageIcon(Config.m02d).getImage(),20,250,50,50,null);
		g.drawRect(19,349,52,52);
		g.drawImage(new ImageIcon(Config.shield).getImage(),20,350,50,50,null);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Font f = new Font("΢���ź�",Font.BOLD,30);
		g.setColor(Color.white);
		g.setFont(f);
		g.drawString("��Ϸ����", (Config.window_width - 150)/2, 80);
		g.setFont(new Font("΢���ź�",Font.BOLD,15));
		g.drawString("Q���ܣ�����һ���ӵ�������2�룬��ȴʱ��5��",100,180);
		g.drawString("W���ܣ����������ӵ�������2�룬��ȴʱ��5��",100,280);
		g.drawString("E���ܣ�����޵л��ܣ�����4�룬��ȴʱ��9��",100,380);
		
		g.drawString("��Ϸ�а��ո����ͣ��ʼ��Ϸ", 120, 480);
	}
}
