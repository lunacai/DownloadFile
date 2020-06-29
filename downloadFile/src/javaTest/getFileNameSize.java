package javaTest;

/**
 * 获得文件的名称和大小，并且写入excel表中
 * @author kascend
 *
 */
import java.io.*;

public class getFileNameSize {
	/**
	 * 批量修改文件夹里面的文件名，和计算文件大小写入excel表中
	 * 
	 * @param filepath 文件夹路径
	 * @param rStr     被替换的文字
	 * @param newStr   要替换的文字
	 */
	public static void getFileName(String filepath, String files) {
		try {
			File filecsv = new File(files);
			FileOutputStream fos = null;
			if (!filecsv.exists()) {
				filecsv.createNewFile();// 如果文件不存在，就创建该文件
				fos = new FileOutputStream(filecsv);// 首次写入获取
			} else {
				// 如果文件已存在，那么就在文件末尾追加写入
				fos = new FileOutputStream(filecsv, true);// 这里构造方法多了一个参数true,表示在文件末尾追加写入
			}
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");// 指定以UTF-8格式写入文件
			String content = "";
			String lastname = "";
			File fileDir = new File(filepath);
			for (File file : fileDir.listFiles()) {
				System.out.println(file);
				if (file.isFile()) {
					String fileName = file.getName();
					long fileSize = file.length();
//					String newName = fileName.replace("-", "_");
					lastname = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					String newName = fileName;
					if (lastname.equals(".svga") && fileName.lastIndexOf('_') != -1) {
						newName = fileName.substring(0, fileName.lastIndexOf('_')) + lastname;
						File f_new = new File(filepath, newName);
						file.renameTo(f_new);
						System.out.println(filepath + "\\" + fileName + ">>" + filepath + "\\" + newName + "-----"
								+ f_new.exists());
					}
					content = newName + "," + fileSize / 1024;
					System.out.println(content);
				}
				// 使用true，即进行append file
				if (lastname.equals(".svga") || lastname.equals(".apk")) {
					osw.write(content);
					osw.write("\r\n");
				}

			}
			osw.close();
		} catch (Exception e) {
			System.out.println("错误信息: " + e.getMessage() + "   请确认文件夹是否处在！");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filepath = "/Users/kascend/Downloads/ff/Androids";
		String files = "/Users/kascend/Downloads/ff/Androids/svga.csv";
		getFileName(filepath, files);
	}

}
