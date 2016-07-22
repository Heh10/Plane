package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Plane.Config;

public class PlaneJFrame extends JFrame implements ActionListener
{
	/**
	 * ��ϷJFrame
	 */
	private static final long serialVersionUID = 1L;
	
	//������Ƭ����
	public static CardLayout card;
	//����JPanel
	public static JPanel mainJPanel;
	
	public GameJPanel gameJPanel;
	
	/**
	 * ���췽��
	 */
	public PlaneJFrame()
	{
		// ���ô���λ�úʹ�С
		this.setBounds(Config.location_x, Config.location_y, Config.window_width, Config.window_height);
		// ���ñ���
		this.setTitle("ȫ��ɻ���ս");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setIconImage(new ImageIcon(Config.icon).getImage());
		
		//�����˵���
		JMenuBar menuBar = new JMenuBar();

		JMenu menu2 = new JMenu("ѡ��");
		
		JMenuItem item1 = new JMenuItem("����������");
		JMenuItem item2 = new JMenuItem("�˳�");
		
		//����������¼�
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		//��Ӳ˵���
		menuBar.add(menu2);
		
		menu2.add(item1);
		menu2.add(item2);

		this.setJMenuBar(menuBar);
		
		// ����JPanel
		mainJPanel = new JPanel();
		// ���ÿ�Ƭ����
		card = new CardLayout();
		mainJPanel.setLayout(card);
		
		// ��Ϸҳ��
		gameJPanel = new GameJPanel(this);
		//����ҳ��
		LoadGame loadJPanel = new LoadGame(gameJPanel);
		// ��ӭҳ��
		JPanel welcomeJPanel = new WelcomeJPanel(loadJPanel);
		//����ҳ��
		JPanel rankJPanel = new RankJPanel();
		//����ҳ��
		JPanel helpJPanel = new HelpJPanel();
		//�̵�ҳ��
		JPanel storeJPanel = new StoreJPanel();
		//��Ϸ����
		JPanel setJPanel = new SetJPanel();

		/**
		 * ���ҳ��
		 */
		// ��ӭҳ��
		mainJPanel.add("welcomeJPanel", welcomeJPanel);
		// ������Ϸҳ��
		mainJPanel.add("LoadGame", loadJPanel);
		// ��Ϸҳ��
		mainJPanel.add("gameJPanel", gameJPanel);
		//�������ҳ��
		mainJPanel.add("rankJPanel",rankJPanel);
		//��Ӱ���ҳ��
		mainJPanel.add("helpJPanel",helpJPanel);
		//����̵�ҳ��
		mainJPanel.add("storeJPanel",storeJPanel);
		//�����Ϸ����
		mainJPanel.add("setJPanel",setJPanel);
		// �����ҳ��
		this.add(mainJPanel);
		
		this.setAlwaysOnTop(true);
		// ���ò��ܸı��С
		this.setResizable(false);
		// ���ÿɼ�
		this.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "����������")
		{
			//��Ϸ����
			gameJPanel.GameOver();
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "welcomeJPanel");
		}
		if(e.getActionCommand() == "�˳�")
		{
			//�˳���Ϸ
			System.exit(0);
		}
	}
}
