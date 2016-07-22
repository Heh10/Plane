package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Plane.Config;

/**
 * 
 * �л���
 * @author HE
 *
 */

public class Enemy
{
	// λ�ô�С
	public int x, y,w,h;
	
	public int type;
	
	public int blood = 100;
	
	public int fullBlood = blood;

	
	public ArrayList<Image> image = new ArrayList<>();
	
	/**
	 * ���췽��
	 * @param x
	 * @param y
	 * @param type
	 */
	public Enemy(int x, int y, int type)
	{
		this.x = x;
		this.y = y;
		
		this.type = type;
		
		image.add(new ImageIcon(Config.enemy0).getImage());
		image.add(new ImageIcon(Config.enemy1).getImage());
		image.add(new ImageIcon(Config.enemy2).getImage());
		image.add(new ImageIcon(Config.enemy3).getImage());
		image.add(new ImageIcon(Config.enemy4).getImage());
		
		if(type == 0)
		{
			this.w = 63;
			this.h = 58;
		}
		else if(type == 1)
		{
			this.w = 93;
			this.h = 64;
		}
		else if(type == 2)
		{
			this.w = 119;
			this.h = 78;
		}
		else if(type == 3)
		{
			this.w = 63;
			this.h = 63;
		}
		else if(type == 4)
		{
			this.w = 56;
			this.h = 66;
		}
	}

	// ���л�
	public void draw(Graphics g)
	{
			g.drawImage(image.get(type), x, y, w, h, null);
			
//			g.setColor(Color.white);
//			g.drawRect(x, y + h + 5, w, 5);
//			
//			g.setColor(Color.red);
//			g.fillRect(x, y + h + 5, w * blood/fullBlood, 5);
	}

	public void move()
	{
		y = y + 2;
	}
	
	
	//�����ײ
		public boolean isGetRect(int rectX,int rectY,int rectW,int rectH)
		{
			
			Rectangle rect = new Rectangle(x, y, w, h);
			
			if(rect.intersects(new Rectangle(rectX, rectY, rectW, rectH)))
			{
				return true;
			}
			else
			{
				return false;
			}
		}

}
