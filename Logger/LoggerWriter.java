import java.awt.image.DirectColorModel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.naming.spi.DirectoryManager;

public class LoggerWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File file = new File("C://JavaLog//Log.txt");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			fw.write("你好么？我是Java的打印方法");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}