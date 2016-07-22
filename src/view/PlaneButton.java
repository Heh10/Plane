package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Plane.Config;
import Plane.MusicPlayer;

public class PlaneButton extends JButton
{
	/**
	 * �Զ��尴ť
	 */
	private static final long serialVersionUID = 1L;
	private boolean button_type = true;
	// ��ť��ʾ����
	private String text;

	// ���췽��
	public PlaneButton(String text)
	{
		this.text = text;

		this.setText(text);
		// ���ô�С
		this.setPreferredSize(new Dimension(160, 45));
		this.setFocusable(false);
		// �����Ʊ���
		this.setContentAreaFilled(false);
		// �����Ʊ߽�
		this.setBorderPainted(false);

		// ��ť�ƽ��Ƴ��¼�
		this.addMouseListener(new MouseListener()
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
				button_type = true;
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
				button_type = false;
				MusicPlayer music = new MusicPlayer(Config.button_sound);
				music.play();
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// ���Ʊ���
		if (button_type == true)
		{
			g.drawImage(new ImageIcon(Config.button5).getImage(), 0, 0, 160, 45, null);
		} else
		{
			g.drawImage(new ImageIcon(Config.button6).getImage(), 0, 0, 160, 45, null);
		}
		// ��������
		g.setFont(new Font("΢���ź�",Font.BOLD,15));
		g.drawString(text, 50, 27);

	}
}
