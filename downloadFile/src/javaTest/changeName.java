package javaTest;

/**
 * 修改文件夹里面的文件名称
 * @author kascend
 *
 */
import java.io.*;

public class changeName {
	/**
	 * 批量修改文件夹里面的文件名
	 * 
	 * @param filepath 文件夹路径
	 * @param rStr     被替换的文字
	 * @param newStr   要替换的文字
	 */
	public static void getFileName(String filepath, String rStr, String newStr) {
		try {
			File fileDir = new File(filepath);
			for (File file : fileDir.listFiles()) {
				if (file.isFile()) {
					String fileName = file.getName();
					String newName = fileName.replace(rStr, newStr);
					File f_new = new File(filepath, newName);
					file.renameTo(f_new);
					System.out.println(
							filepath + "\\" + fileName + ">>" + filepath + "\\" + newName + "-----" + f_new.exists());
				}
			}
		} catch (Exception e) {
			System.out.println("错误信息: " + e.getMessage() + "   请确认文件夹是否处在！");
		}

	}

	/**
	 * 批量生成对应文件的文件夹-常用户iOS替换SVGA动画
	 * 
	 * @param filepath
	 */
	public static void setNewFile(String filepath) {
		File fileDir = new File(filepath);
		for (File fileone : fileDir.listFiles()) {
			String pathone = fileone.getPath();
			System.out.println(pathone);
			File path1 = new File(pathone);
			if (!path1.isDirectory()) {
				String pathoneName = pathone.substring(0, pathone.lastIndexOf("."));
				File path = new File(pathoneName);
				if (!path.isDirectory()) {
					path.mkdir();
					System.out.println(path + "创建成功！");
				}
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filepath = "/Users/kascend/Downloads/new/PC";
		String rStrAndroid = "svga";
		String newStriOS="DynamicAnimation-";
		String newStrPC ="Animation-";
		int isIOS = 3;
		if (isIOS==1) {//iOS配置
			getFileName(filepath, rStrAndroid, newStriOS);
			getFileName(filepath, "."+newStriOS, ".svga");
			getFileName(filepath, "_", "-");
			setNewFile(filepath);
		}else {
			if(isIOS==2) {//Android配置
				getFileName(filepath, "-", "_");
			}else {
				getFileName(filepath, rStrAndroid, newStrPC);
				getFileName(filepath, "."+newStrPC, ".svga");
				getFileName(filepath, "_", "-");
			}
		}
	}

}
