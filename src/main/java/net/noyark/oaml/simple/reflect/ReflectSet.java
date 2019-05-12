/*Culesberry technolegy Co. Ltd. (c) 2019-2020
 * 
 * Stating that the software,the software belongs Gulesberry
 * noyark open source group,noyark has all the power to interpret
 * and copyright information for the software prohibit organizations
 * and individuals conduct their business practices and illegal practices,
 * projects of: magiclu,Chinese name *Changcun Lu*.The software has nothing
 * to do with current politics,free software is the purpose of noyark
 * 
 * noyark-system info:
 * 	****************************************************
 * 											www.noyark.net
 *		 ****************************************************
 * 
 */
package net.noyark.oaml.simple.reflect;

import net.noyark.oaml.ConfigQueryer;
import net.noyark.oaml.annotations.Config;
import net.noyark.oaml.annotations.Root;
import net.noyark.oaml.exception.AmbiguousTypeException;
import net.noyark.oaml.simple.OamlConfig;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to extensively load all Config
 * annotated classes and create corresponding configuration
 * file objects,then load the Root or Node annotations,load
 * them into hierarchy and key-value pairs,and various data types
 * to achieve the recommended configuration.
 * <BR>How to load using this class
 * <BR>if the config class in the a.b.c.d package,the packageFile
 * can be a or a.b or a.b.c or a.b.c.d
 * <BR>&nbsp;new ReflectSet("the parent package",this).loadAnnotation();
 * @author magiclu550
 * @since JDK1.8
 * @since oaml 003
 *
 */

public class ReflectSet {
	/** the package file*/
	private String packageFile;
	/** usually is 'this' */
	private Object thisClass;
	/** all config instance*/
	private Map<Class<?>,OamlConfig> configsMap;
	
	/**
	 * After the constructor constructs the object,
	 * the <CODE>loadAnnotation</CODE> method is 
	 * called to load the configuration file.
	 * @param packageFile the parent package file
	 * if the config class in the a.b.c.d package,the packageFile
	 * can be a or a.b or a.b.c or a.b.c.d
	 * @param thisClass always is 'this',you can also use other object
	 */
	
	public ReflectSet(String packageFile,Object thisClass) {
		this.packageFile = packageFile;
		this.thisClass = thisClass;
		this.configsMap = new HashMap<Class<?>, OamlConfig>();
	}
	
	/**
	 * Used to load all the classes with Config annotation 
	 * under the specified package ,and parse it into a 
	 * configuration file,and generate a configuration file object
	 */
	
	public void loadAnnotation() {
		try {
			scanPackage();
		} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException | InstantiationException
				| IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the OamlConfig object loaded by the corresponding class
	 * @param clz the Class type
	 * @return the Config object,if it is not exists,it will return null
	 */
	
	public OamlConfig getConfigObject(Class<?> clz) {
		return configsMap.get(clz);
	}
	/**
	 * Its role is to further parse
	 * the obtained class and resolve it to
	 * an instance; 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	private void scanPackage() throws IllegalArgumentException, IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
			String classPath = thisClass.getClass().getResource("/").getPath();
			String otherPath;
			if(classPath.indexOf("test-classes")!=-1) {
				otherPath = classPath.replace("test-classes","classes");
			}else {
				otherPath = classPath.replace("classes","test-classes");
			}
			String[] allPath = {classPath,otherPath};
			for(String path:allPath) {
				String packagePath = path+packageFile.replaceAll("\\.","/");
				File file = new File(packagePath);
				List<File> fileName = loadClass(file);
				for(File fn:fileName) {
					File[] fs = fn.listFiles();
					if(fs!=null) {
						for(File f1:fs) {
							if(f1.getName().endsWith(".class")) {
								String classpath = f1.getPath();
								classpath = classpath.substring(classpath.indexOf("classes")+"classes".length()+1,classpath.indexOf(".class")).replaceAll("/",".");
								Class<?> clz = Class.forName(classpath);
								Annotation anno = clz.getDeclaredAnnotation(Config.class);
								if(anno!=null){
									Config configType = (Config)anno;
									String name = configType.value();//获取filename
									boolean getClass = configType.classpath();
									//获得类型
									Object object = clz.newInstance();
									Field[] fields = object.getClass().getDeclaredFields();
									OamlConfig config = ConfigQueryer.getInstance(name,getClass);
									configsMap.put(clz, config);
									for(Field f:fields) {
										Root root = f.getAnnotation(Root.class);
										net.noyark.oaml.annotations.Node node = f.getAnnotation(net.noyark.oaml.annotations.Node.class);
										if(root !=null&&node != null) {
											throw new AmbiguousTypeException("the Root annotation and the Node annotation can not coexist");
										}
										if(root !=null) {
											String key = root.value();
											config.put(key, f.get(object));
										}
										if(node!=null) {
											String key = node.value();
											Object objects = f.get(object);
											config.put(key,objects);
										}
									}
									config.save();
								}
							}
						}
					}
				}
			}
	}
	
	/**
	 * It can get Package and Classes next the root package
	 * @param file the classpath file
	 * @return the file about classes and package
	 */
	
	private List<File> loadClass(File file) {
		List<File> allFiles = new ArrayList<File>();
		File[] files = file.listFiles();
		if(files!=null) {
			for(File f:files) {
				if(f.getName().endsWith(".class")) {
					allFiles.add(f);
				}
			}
		}
		searchFile(files, allFiles);
		return allFiles;
	}
	
	/**
	 * It can get all of the file about package
	 * @param files the file objects
	 * @param allFiles the package file
	 */
	
	private void searchFile(File[] files,List<File> allFiles) {
		if(files!=null) {
			for(File f:files) {
				if(f.isDirectory()){
					allFiles.add(f);
					File[] files2 = f.listFiles();
					searchFile(files2, allFiles);
				}
			}
		}
	}
}
