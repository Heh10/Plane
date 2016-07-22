package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 
 * �ӵ���
 * @author HE
 *
 */

public class Bullet
{
	// ����
	public int x, y, w, h;
	// ͼƬ
	public Image bg;
	//�ӵ�ƫ�ƽǶ�
	public int deg;

	/**
	 * ���췽��
	 */
	public Bullet(int x, int y, int w, int h, Image bg, int deg)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.bg = bg;
		this.deg = deg;
	}

	/**
	 * ���ӵ�
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.drawImage(bg, x, y, w, h, null);
	}

	/**
	 * �ƶ�
	 * 
	 * @param i
	 */
	public void move(int i)
	{
		switch (i)
		{
			case 0:
				y = y + 3;
				x = x + deg;
				break;
			case 1:
				y = y - 5;
				x = x + deg;
				break;

			default:
				break;
		}
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
