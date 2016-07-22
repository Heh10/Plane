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
import Plane.MusicPlayer;

public class SetJPanel extends JPanel
{

	/**
	 * ��Ϸ����
	 */
	private static final long serialVersionUID = 3349012082035919165L;
	
	//��Ϸ����
	
	public int type = 0;
	/**
	 * ���췽��
	 */
	public SetJPanel()
	{
		JButton button_1 = new JButton();
		
		button_1.setIcon(new ImageIcon(Config.on));
		
		button_1.setBounds((Config.window_width - 81)/2, 300,81,25);
		
		this.add(button_1);
		
		button_1.setFocusable(false);
		
		this.setLayout(null);
		
		JButton button = new PlaneButton("������ҳ");
		
		button.setBounds((Config.window_width - 160)/2, Config.window_height - 150, 160, 45);
		
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
				//��ʾ��ҳ
				PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "welcomeJPanel");
			}
		});
		
		button_1.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub
				if(type == 0)
				{
					MusicPlayer.isPlay = false;
					type = 1;
					button_1.setIcon(new ImageIcon(Config.off));
					
				}
				else
				{
					MusicPlayer.isPlay = true;
					type = 0;
					button_1.setIcon(new ImageIcon(Config.on));
				}
			}
		});
	}
	
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//������
		g.drawImage(new ImageIcon(Config.img_bg_level_2).getImage(), 0, 0,Config.window_width,Config.window_height, null);
		
		g.setColor(Color.white);
		g.setFont(new Font("΢���ź�",Font.BOLD,20));
		g.drawString("��Ϸ���ֿ���", (Config.window_width - 130)/2,280);
	}
}
