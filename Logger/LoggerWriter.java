import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Date;

public class LoggerWriter implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main thread interrupted.1");

		LoggerWriter.WriteLog("12345上山打老虎", 5000);
		LoggerWriter.WriteLog("67890老虎不在家");
		System.out.println("Main thread interrupted.2");
	}

	Thread t;
	int sleeptime = 2000;// 文件被占用时线程的等待时间
	static String filePath = "C://JavaLog//Log.txt";
	String logString = "1234longstring";


	LoggerWriter(String logStrings, int sleep) {
		logString = logStrings;
		sleeptime = sleep;
		t = new Thread(this, "LoggerWriter Thread");
		//t.run(); // 同步执行线程
		t.start();//异步执行线程
	}

	public static void WriteLog(String logString) {
		new LoggerWriter(logString, 2000);
	}

	public static void WriteLog(String logString, int sleep) {
		new LoggerWriter(logString, sleep);
	}

	@Override
	public void run() {
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			while (!file.renameTo(file)) {
				System.out.println("当前文件被占用，线程沉睡2s再运行");
				Thread.sleep(sleeptime);

			}
			FileWriter fw = new FileWriter(file, true);
			/*
			 * BufferedWriter有自带的newLine()方法表示换行，避免了FileWriter中为了换行而进行硬编码
			 */
			BufferedWriter bufferedWriter = new BufferedWriter(fw);
			bufferedWriter.write(new Date().toString());
			bufferedWriter.write(": ");
			bufferedWriter.write(logString);			
			//System.out.println("当前线程sleeptime=" + sleeptime);
			//Thread.sleep(sleeptime);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runFileWriter() {
		try {
			File file = new File("C://JavaLog//Log.txt");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			while (!file.renameTo(file)) {
				System.out.println("当前文件被占用，线程沉睡2s再运行");
				Thread.sleep(3000);

			}
			FileWriter fw = new FileWriter(file, true);
			fw.write(new Date().toString());
			fw.write(": ");
			fw.write(logString);
			String crlf = System.getProperty("line.separator");// 获得换行编码
			fw.write(crlf);
			System.out.println("当前现场sleeptime=" + sleeptime);
			Thread.sleep(sleeptime);
			fw.flush();
			fw.close();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
