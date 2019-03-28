package com.fanyiran.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import static com.fanyiran.lib.Constants.JAVA_NAME;

/**
 * Created by fanqiang on 2019/3/27.
 */
public class MyProxy {
    public static Object newProxyInstance(MyClassLoader classLoader, Class[] classes, ManInvocationHandler handler) {
        //创建java文件
        String javaString = "package com.fanyiran.lib;\n" +
                "\n" +
                "import java.lang.reflect.InvocationHandler;\n" +
                "import java.lang.reflect.Method;\n" +
                "import java.lang.reflect.Proxy;\n" +
                "\n" +
                "/**\n" +
                " * Created by fanqiang on 2019/3/27.\n" +
                " */\n" +
                "public class $Proxy0 extends Proxy implements People {\n" +
                "    protected $Proxy0(InvocationHandler h) {\n" +
                "        super(h);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void saySomething() {\n" +
                "        Method method;\n" +
                "        try {\n" +
                "            method = Class.forName(\"$Proxy0\").getMethod(\"saySomething\");\n" +
                "            h.invoke(this, method, null);\n" +
                "        } catch (NoSuchMethodException e) {\n" +
                "            e.printStackTrace();\n" +
                "        } catch (ClassNotFoundException e) {\n" +
                "            e.printStackTrace();\n" +
                "        } catch (Throwable throwable) {\n" +
                "            throwable.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "}";
        // 编译生成class文件
        File file = classLoader.getDirPath();
        if (!file.exists()) {
            file.mkdirs();
        }
        complieFile(new File(classLoader.getDirPath(),"$Proxy0.java"),javaString);
        try {
            Class<?> aClass = classLoader.loadClass(JAVA_NAME);
            //反射调用
            Constructor<?> constructor = aClass.getConstructor(ManInvocationHandler.class);
            return constructor.newInstance(handler);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void complieFile(File file, String javaString) {
        saveStringToFile(file,javaString);
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
        task.call();
        try {
            standardFileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveStringToFile(File file, String javaString) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(javaString.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
