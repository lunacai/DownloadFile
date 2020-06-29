package javaTest;

import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * 通过链接下载文件
 * 
 * @author Canace
 *
 */

public class downFile {
	/**
	 * @功能 下载文件接口
	 * @param filePath 文件将要保存的目录
	 * @param method   请求方法，包括POST和GET
	 * @param url      请求的路径
	 * @return
	 */
	public static File saveUrlAs(String url, String filePath, String fileName, String method) {
		// 创建不同的文件夹目录
		File file = new File(filePath);
		// 判断文件夹是否存在
		if (!file.exists()) {
			// 如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}
		FileOutputStream fileOut = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		try {
			// 建立链接
			URL httpUrl = new URL(url);
			conn = (HttpURLConnection) httpUrl.openConnection();
			// 以Post方式提交表单，默认get方式
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// post方式不能使用缓存
			conn.setUseCaches(false);
			// 连接指定的资源
			conn.connect();
			// 获取网络输入流
			inputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			// 判断文件的保存路径后面是否以/结尾
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
			// 写入到文件（注意文件保存路径的后面一定要加上文件的名称）
			fileOut = new FileOutputStream(filePath + fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fileOut);

			byte[] buf = new byte[4096];
			int length = bis.read(buf);
			// 保存文件
			while (length != -1) {
				bos.write(buf, 0, length);
				length = bis.read(buf);
			}
			bos.close();
			bis.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("抛出异常！！");
		}
		return file;
	}
	/**
	 * 读取文档，获得下载地址
	 * @param filePath 文档地址链接，相对地址
	 * @return
	 */
	public static List<String> getFileToList(String filePath) {
		List<String> list = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				if (str.trim().length() > 2) {
					list.add(str);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strs= args[0];
		String filePath = args[1];
		List<String> getl=getFileToList(strs);
		for(String attribute : getl) {
			System.out.println(attribute);
			String photoUrl = attribute;
			String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));
			System.out.println("fileName---->" + fileName);
			File file = saveUrlAs(photoUrl, filePath, fileName, "GET");
			System.out.println("Run ok!/n<BR>Get URL file " + file);
		}
	}

}
