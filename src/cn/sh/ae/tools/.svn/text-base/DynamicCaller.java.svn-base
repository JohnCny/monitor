//package cn.sh.ae.tools;
//
//import java.io.File;
//import java.lang.reflect.Constructor;
//import java.net.URL;
//import java.net.URLClassLoader;
//
//import cn.sh.ae.util.MyConstant;
//import cn.sh.ae.vo.Host;
//
//public class DynamicCaller {
//	
//	public static Object init(Host host) {
//		File f = new File("D:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\lib\\common.jar");
//		URL url = null;
//		try {
//			url = (f.toURI()).toURL();
//			URLClassLoader ucl = new URLClassLoader(new URL[] { url });
//			Class xClass = ucl.loadClass("cn.sh.ae.comm.Down");
//			Constructor constructor1 = xClass.getConstructor(Host.class);
//			return constructor1.newInstance(host);
//			// Method xMethod = xClass.getDeclaredMethod("test");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		// 用URL对象建立URLClassLoader
//
//	}
//
//	public static Class dynamiLoader(String jarPath, String className) {
//		File f = new File(MyConstant.LIBPATH + jarPath);
//		URL url = null;
//		try {
//			url = (f.toURI()).toURL();
//			URLClassLoader ucl = new URLClassLoader(new URL[] { url });
//			return ucl.loadClass(className);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//}
