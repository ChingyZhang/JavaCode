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

		LoggerWriter.WriteLog("12345��ɽ���ϻ�", 5000);
		LoggerWriter.WriteLog("67890�ϻ����ڼ�");
		System.out.println("Main thread interrupted.2");
	}

	Thread t;
	int sleeptime = 2000;// �ļ���ռ��ʱ�̵߳ĵȴ�ʱ��
	static String filePath = "C://JavaLog//Log.txt";
	String logString = "1234longstring";


	LoggerWriter(String logStrings, int sleep) {
		logString = logStrings;
		sleeptime = sleep;
		t = new Thread(this, "LoggerWriter Thread");
		//t.run(); // ͬ��ִ���߳�
		t.start();//�첽ִ���߳�
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
				System.out.println("��ǰ�ļ���ռ�ã��̳߳�˯2s������");
				Thread.sleep(sleeptime);

			}
			FileWriter fw = new FileWriter(file, true);
			/*
			 * BufferedWriter���Դ���newLine()������ʾ���У�������FileWriter��Ϊ�˻��ж�����Ӳ����
			 */
			BufferedWriter bufferedWriter = new BufferedWriter(fw);
			bufferedWriter.write(new Date().toString());
			bufferedWriter.write(": ");
			bufferedWriter.write(logString);			
			//System.out.println("��ǰ�߳�sleeptime=" + sleeptime);
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
				System.out.println("��ǰ�ļ���ռ�ã��̳߳�˯2s������");
				Thread.sleep(3000);

			}
			FileWriter fw = new FileWriter(file, true);
			fw.write(new Date().toString());
			fw.write(": ");
			fw.write(logString);
			String crlf = System.getProperty("line.separator");// ��û��б���
			fw.write(crlf);
			System.out.println("��ǰ�ֳ�sleeptime=" + sleeptime);
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
