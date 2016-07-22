package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Plane.Config;
import entity.FileIO;

public class RankJPanel extends JPanel
{
	/**
	 * ���а�ҳ��
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���췽��
	 */
	int x,y;
	
	//�����Ϸ����
	private ArrayList<Integer> number;
	
	FileIO io;
	/**
	 * ���췽��
	 */
	public RankJPanel()
	{
		io = new FileIO();
		this.number = io.list;
		this.setLayout(null);
		PlaneButton button_return = new PlaneButton("������ҳ");
		button_return.setBounds((Config.window_width - 160)/2,Config.window_height - 120,160,45);
		
		this.add(button_return);
		
		button_return.addMouseListener(new MouseListener()
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
		
		io = new FileIO();
		
		number = io.list;
		//������
		g.drawImage(new ImageIcon(Config.img_bg_level_2).getImage(),0,0,480,700,null);
		Font f = new Font("΢���ź�",Font.BOLD,40);
		
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("��Ϸ�������а�",(Config.window_width - 300)/2,50);
		
		x = Config.window_width/2;
		y = 100;
		//������
		g.setFont(new Font("΢���ź�",Font.BOLD,30));
		for(int i = 0; i < 10; i++)
		{
			g.drawString("��"+ (i + 1) + "����", x - 130, y);
			g.drawString(number.get(i).toString(), x, y);
			
			y = y + 50;
		}
		
	}
	
}
