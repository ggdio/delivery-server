package br.com.ggdio.delivery.util.reflection;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackageScanner {

	/**
	 *
	 * Attempts to list all the classes in the specified package as determined
	 * by the context class loader
	 *
	 * @param pkg
	 *            the package name to search
	 * @return a list of classes that exist within that package
	 * @throws ClassNotFoundException
	 *             if something went wrong
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List listPackageClasses(String pkg) throws ClassNotFoundException {
		ArrayList result = new ArrayList();
		ArrayList<File> directories = new ArrayList();
		HashMap packageNames = null;
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			for (URL jarURL : ((URLClassLoader) Thread.currentThread().getContextClassLoader()).getURLs()) {
				scanJar(result, pkg, jarURL.getPath());
				String path = pkg;
				Enumeration<URL> resources = cld.getResources(path);
				File directory = null;
				while (resources.hasMoreElements()) {
					String path2 = resources.nextElement().getPath();
					directory = new File(URLDecoder.decode(path2, "UTF-8"));
					directories.add(directory);
				}
				if (packageNames == null) {
					packageNames = new HashMap();
				}
				packageNames.put(directory, pkg);
			}
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(pkg + " does not appear to be a valid package (Null pointer exception)");
		} catch (UnsupportedEncodingException encex) {
			throw new ClassNotFoundException(pkg + " does not appear to be a valid package (Unsupported encoding)");
		} catch (IOException ioex) {
			throw new ClassNotFoundException("IOException was thrown when trying to get all resources for " + pkg);
		}
		for (File directory : directories) {
			if (directory.exists()) {
				String[] files = directory.list();
				for (String file : files) {
					if (file.endsWith(".class")) {
						try {
							// System.out.println(packageNames.get(directory).toString()
							// + '.' + file.substring(0, file.length() – 6));
							result.add(Class.forName(packageNames.get(directory).toString() + '.'
									+ file.substring(0, file.length() - 6)));
						} catch (Throwable e) {
						}
					}
				}
			} else {
				throw new ClassNotFoundException(
						pkg + " (" + directory.getPath() + ") does not appear to be a valid package");
			}
		}
		return result;
	}

	/**
	 *
	 * Returns the list of classes in the same directories as Classes in
	 * classes.
	 *
	 * @param result
	 * @param classes
	 * @param jarPath
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void scanJar(List result, String packageName, String jarPath) {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarPath);
			Enumeration<JarEntry> en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry entry = en.nextElement();
				String entryName = entry.getName();
				packageName = packageName.replace('.', '/');
				if (entryName != null && entryName.endsWith(".class") && entryName.startsWith(packageName)) {
					try {
						Class entryClass = Class
								.forName(entryName.substring(0, entryName.length() - 6).replace('/', '.'));
						if (entryClass != null) {
							result.add(entryClass);
						}
					} catch (Throwable e) {
						// do nothing, just continue processing classes
					}
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				if (jarFile != null) {
					jarFile.close();
				}
			} catch (Exception e) {
			}
		}
	}

}
