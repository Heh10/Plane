package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Plane.Config;

public class WelcomeJPanel extends JPanel implements ActionListener
{
	/**
	 * ��Ϸ��ҳ
	 */
	private static final long serialVersionUID = 1L;

	public LoadGame loadJPanel;

	// ���Ʊ���
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new ImageIcon(Config.img_bg_level_2).getImage(), 0, 0, 480, 700, null);
		g.drawImage(new ImageIcon(Config.myPlane).getImage(), (Config.window_width - 116)/2, 30,120,79, null);
		g.setFont(new Font("΢���ź�",Font.BOLD,40));
		g.setColor(Color.white);
		g.drawString("ȫ��ɻ���ս", (Config.window_width - 240)/2, 165);
	}

	/**
	 * ���췽��
	 * 
	 * @param loadJPanel
	 * 
	 */
	public WelcomeJPanel(LoadGame loadJPanel)
	{
		this.loadJPanel = loadJPanel;
		// ��������
		Font f = new Font("", Font.BOLD, 20);
		// logo ͼƬ
		JLabel logo = new JLabel(new ImageIcon(Config.Logo));

		// ��ť���� �ĳ�һ��
		JButton button1 = new PlaneButton("��ʼ��Ϸ");
		JButton button2 = new PlaneButton("��Ϸ����");
		JButton button3 = new PlaneButton("��Ϸ����");
		JButton button4 = new PlaneButton("��Ϸ�̵�");
		JButton button5 = new PlaneButton("���а�");
		JButton button6 = new PlaneButton("�˳���Ϸ");

		// ��Ȩ����
		JLabel plane_info = new JLabel("��Ȩ����");
		// ��������
		plane_info.setFont(f);
		//������ɫ
		plane_info.setForeground(Color.white);

		// ���ñ߽粼��
		this.setOpaque(false);

		// ���ò���
		this.setLayout(null);

		// ����λ��
		logo.setBounds((Config.window_width - 230) / 2, 40, 230, 140);
		button1.setBounds((Config.window_width - 160) / 2, 200, 160, 45);
		button2.setBounds((Config.window_width - 160) / 2, 260, 160, 45);
		button3.setBounds((Config.window_width - 160) / 2, 320, 160, 45);
		button4.setBounds((Config.window_width - 160) / 2, 380, 160, 45);
		button5.setBounds((Config.window_width - 160) / 2, 440, 160, 45);
		button6.setBounds((Config.window_width - 160) / 2, 500, 160, 45);
		plane_info.setBounds((Config.window_width - 90) / 2, 560, 90, 45);

		// ��Ӱ�ť�¼�
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);

		// ���Logo
//		this.add(logo);
		// ��Ӱ�ť
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		this.add(button6);
		// ��Ӱ�Ȩ��Ϣ
		this.add(plane_info);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String text = e.getActionCommand();

		if (text == "��ʼ��Ϸ")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "LoadGame");
			loadJPanel.logoin();
		}
		if (text == "�˳���Ϸ")
		{
			System.exit(0);
		}
		if(text == "���а�")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "rankJPanel");
		}
		if(text == "��Ϸ����")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "helpJPanel");
		}
		if(text == "��Ϸ�̵�")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "storeJPanel");
		}
		if(text == "��Ϸ����")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "setJPanel");
		}

	}
}
