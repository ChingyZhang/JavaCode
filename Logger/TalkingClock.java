import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.InterruptibleChannel;
import java.util.Date;

import javax.swing.Timer;

/*
 * Java�ڲ������һ�����ⲿ�����ʽ���ã�static�ڲ��಻����������
 * */
public class TalkingClock {

	private int interval;
	private boolean beep;

	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}

	public void Start() {
		System.out.println("�ڲ���Start");
		ActionListener actionListener = this.new TimePrinter();
		Timer timer = new Timer(interval, actionListener);
		timer.start();
	}

	public void Start(int interval, final boolean beep) {

		// ����һ���ֲ��ֻ࣬�Ե�ǰ�����ɼ����Ҳ�������private��public���η������ֲ���ɷ��ʷ����е�final����
		class FuncTimePrinter implements ActionListener {// ֱ�������ж�����ڲ���

			@Override
			public void actionPerformed(ActionEvent e) {

				Date now = new Date();
				System.out.println("time is " + now);
				if (beep) {
					Toolkit.getDefaultToolkit().beep();
				}
			}

		}

		System.out.println("�ֲ���Start");
		ActionListener actionListener = new FuncTimePrinter();
		Timer timer = new Timer(interval, actionListener);
		timer.start();
	}

	private class TimePrinter implements ActionListener {// ֱ�������ж�����ڲ���

		@Override
		public void actionPerformed(ActionEvent e) {

			Date now = new Date();
			System.out.println("time is " + now);
			if (TalkingClock.this.beep) {// �����ⲿ�����������ֱ�ӵ���beep����Ч��һֱ
				System.out.println("actionPerformed��" + interval);
			}
			if (!beep) {
				System.out.println("actionPerformed��" + interval);
				Toolkit.getDefaultToolkit().beep();
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TalkingClock talkingClock = new TalkingClock(100, true);
		talkingClock.Start();
		talkingClock.Start(100, true);
	}

}

class TimePrinter implements ActionListener {// ֱ�������ж�����ڲ���

	private TalkingClock outer;

	public TimePrinter(TalkingClock clock) {
		this.outer = clock;
	}

	public void actionPerformed(ActionEvent e) {

		Date now = new Date();
		System.out.println("time is " + now);
		/*
		 * if (outer.beep) {// �����ⲿ����󣬵����ܷ��ʶ���˽����
		 * System.out.println("actionPerformed��"); }
		 */
	}

}