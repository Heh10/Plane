package Plane;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * ��Ϸ���ֲ�����
 * @author Administrator
 */
public class MusicPlayer 
{
	
	//���������ļ�·��
	public String path;
	private Clip clip;
	
	public static boolean isPlay = true;
	
	/**
	 * ���췽�� 
	 * @param path  �ļ�·��
	 */
	public MusicPlayer(String path)
	{
		//����ʵ����
		try {
			//���������ļ�
			File file = new File(path);
			AudioInputStream stream = AudioSystem.getAudioInputStream(file) ;
			//���ֲ��Ŷ���
			clip = AudioSystem.getClip();
			//���������ļ�
			clip.open(stream);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * ��ʼ���ֲ���
	 */
	public void play()
	{
		if(isPlay)
		{
			clip.start();
		}
	}
	/**
	 * ����ֹͣ����
	 */
	public void stop()
	{
		if(isPlay)
		{
			clip.stop();
		}
	}

	/**
	 * ����ѭ������
	 */
	public void loop()
	{
		if(isPlay)
		{
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
}
