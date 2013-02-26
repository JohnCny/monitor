package cn.sh.ae.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RarFile {

	public static void unRarFile(String targetPath, String absolutePath) {
		doRar(targetPath, absolutePath, true);
	}

	public static void rarFile(String targetPath, String absolutePath) {
		doRar(targetPath, absolutePath, false);
	}

	/**
	 * 
	 * 解压rar文件
	 * 
	 * 
	 * 
	 * @param targetPath
	 * 
	 * @param absolutePath
	 * 
	 */

	private static void doRar(String targetPath, String absolutePath,
			boolean unOrNot) {
		try {

			// 系统安装winrar的路径

			String cmd = "D:\\Program Files\\WinRAR\\winrar.exe";
			String unrarCmd = cmd + " a " + absolutePath + " " + targetPath;
			if (unOrNot)
				unrarCmd = cmd + " x -r -p- -o+ " + absolutePath + " "
						+ targetPath;

			Runtime rt = Runtime.getRuntime();

			Process pre = rt.exec(unrarCmd);

			InputStreamReader isr = new InputStreamReader(pre.getInputStream());

			BufferedReader bf = new BufferedReader(isr);

			String line = null;

			while ((line = bf.readLine()) != null) {

				line = line.trim();

				if ("".equals(line)) {

					continue;

				}

				System.out.println(line);

			}

			bf.close();

		} catch (Exception e) {

			System.out.println("解压发生异常");

		}

	}

	/**
	 * 
	 * @param args
	 * 
	 */

	// public static void main(String[] args) {
	//
	// String targetPath = "d:\\b\\";
	//
	// String rarFilePath = "d:\\b\\aa";
	//
	// RarFile unrar = new RarFile();
	//
	// RarFile.rarFile(targetPath, rarFilePath);
	//
	//	}

}