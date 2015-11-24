package com.eboji.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扫描指定包(包括jar)下的class文件的工具类
 * @author zhoucl 
 */
public class ClassScanUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClassScanUtil.class);

	/**
	 * 扫描指定包的所有class文件
	 * 
	 * @param basePackage 基础包
	 * @param recursive 是否递归搜索子包
	 * @return Set
	 */
	public Set<Class<?>> getPackageAllClasses(String basePackage, boolean recursive) {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		String packageName = basePackage;
		if (packageName.endsWith(".")) {
			packageName = packageName.substring(0, packageName.lastIndexOf('.'));
		}
		String package2Path = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(package2Path);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					logger.debug("scan classes by file....");
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					doScanPackageClassesByFile(classes, packageName, filePath, recursive);
				} else if ("jar".equals(protocol)) {
					doScanPackageClassesByJar(packageName, url, recursive, classes);
				}
			}
		} catch (IOException e) {
			logger.error("IOException error:", e);
		}

		return classes;
	}

	/**
	 * 以jar的方式扫描包下的所有Class文件<br>
	 * @param basePackage 基础包
	 * @param url	类的URL
	 * @param recursive 是否递归查找
	 * @param classes Set
	 */
	private void doScanPackageClassesByJar(String basePackage, URL url, final boolean recursive, Set<Class<?>> classes) {
		String packageName = basePackage;
		String package2Path = packageName.replace('.', '/');
		JarFile jar;
		try {
			jar = ((JarURLConnection) url.openConnection()).getJarFile();
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				if (!name.startsWith(package2Path) || entry.isDirectory()) {
					continue;
				}

				//判断是否递归搜索子包
				if (!recursive && name.lastIndexOf('/') != package2Path.length()) {
					continue;
				}

				packageName = name.substring(0, name.lastIndexOf('/')).replace('/', '.');
				
				String className = name.substring(name.lastIndexOf('/') + 1);
				className = className.substring(0, className.length() - 6);
				
				this.loadClass(classes, packageName, className);
			}
		} catch (IOException e) {
			logger.error("IOException error:", e);
		}
	}

	/**
	 * 以文件的方式扫描包下的所有Class文件
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	private void doScanPackageClassesByFile(Set<Class<?>> classes, String packageName, String packagePath, boolean recursive) {
		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		final boolean fileRecursive = recursive;
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义文件过滤规则
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return fileRecursive;
				}
				return true;
			}
		});
		for (File file : dirfiles) {
			if (file.isDirectory()) {
				doScanPackageClassesByFile(classes, packageName + "." + file.getName(), file.getAbsolutePath(), recursive);
			} else {
				String className = file.getName().substring(0, file.getName().length() - 6);
				this.loadClass(classes, packageName, className);
			}
		}
	}

	public void loadClass(Set<Class<?>> classes, String packageName, String className) {
		String name = packageName + '.' + className;
		try {
			Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(name);
			if (c.getGenericSuperclass() == null) {
				return;
			}
			classes.add(c);
		} catch (ClassNotFoundException e) {
			logger.error(name + "---not found", e);
		}
	}
}