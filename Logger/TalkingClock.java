import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.InterruptibleChannel;
import java.util.Date;

import javax.swing.Timer;

/*
 * Java内部类包含一个对外部类的隐式引用，static内部类不包含此引用
 * */
public class TalkingClock {

	private int interval;
	private boolean beep;

	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}

	public void Start() {
		System.out.println("内部类Start");
		ActionListener actionListener = this.new TimePrinter();
		Timer timer = new Timer(interval, actionListener);
		timer.start();
	}

	public void Start(int interval, final boolean beep) {

		// 定义一个局部类，只对当前方法可见，且不可设置private或public修饰符。但局部类可访问方法中的final参数
		class FuncTimePrinter implements ActionListener {// 直接在类中定义的内部类

			@Override
			public void actionPerformed(ActionEvent e) {

				Date now = new Date();
				System.out.println("time is " + now);
				if (beep) {
					Toolkit.getDefaultToolkit().beep();
				}
			}

		}

		System.out.println("局部类Start");
		ActionListener actionListener = new FuncTimePrinter();
		Timer timer = new Timer(interval, actionListener);
		timer.start();
	}

	private class TimePrinter implements ActionListener {// 直接在类中定义的内部类

		@Override
		public void actionPerformed(ActionEvent e) {

			Date now = new Date();
			System.out.println("time is " + now);
			if (TalkingClock.this.beep) {// 调用外部类对象，与下面直接调用beep变量效果一直
				System.out.println("actionPerformed了" + interval);
			}
			if (!beep) {
				System.out.println("actionPerformed了" + interval);
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

class TimePrinter implements ActionListener {// 直接在类中定义的内部类

	private TalkingClock outer;

	public TimePrinter(TalkingClock clock) {
		this.outer = clock;
	}

	public void actionPerformed(ActionEvent e) {

		Date now = new Date();
		System.out.println("time is " + now);
		/*
		 * if (outer.beep) {// 调用外部类对象，但不能访问对象私有域
		 * System.out.println("actionPerformed了"); }
		 */
	}

}