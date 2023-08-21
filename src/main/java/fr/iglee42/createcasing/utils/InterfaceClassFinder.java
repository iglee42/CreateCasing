package fr.iglee42.createcasing.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class InterfaceClassFinder {
    public static List<Class<?>> getClassesImplementingInterface(Class<?> interfaceClass) {
        List<Class<?>> implementingClasses = new ArrayList<>();
        for (Package pkg : Package.getPackages()) {
            String packageName = pkg.getName();
            List<Class<?>> packageClasses = getClassesInPackage(packageName);
            for (Class<?> clazz : packageClasses) {
                if (interfaceClass.isAssignableFrom(clazz)) {
                    implementingClasses.add(clazz);
                }
            }
        }
        return implementingClasses;
    }
    private static List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace('.', '/');
        try {
            String[] classPaths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
            for (String classPath : classPaths) {
                File file = new File(classPath + "/" + packagePath);
                if (file.exists() && file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File f : files) {
                        if (f.isFile() && f.getName().endsWith(".class")) {
                            String className = packageName + "." + f.getName().substring(0, f.getName().length() - 6);
                            Class<?> clazz = Class.forName(className);
                            classes.add(clazz);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }
}