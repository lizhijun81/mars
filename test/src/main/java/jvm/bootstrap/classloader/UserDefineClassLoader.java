package jvm.bootstrap.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义ClassLoader
 */
public class UserDefineClassLoader extends ClassLoader {

    private String rootPath;

    public UserDefineClassLoader(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        //转换为以文件路径表示的文件
        String filePath = classToFilePath(name);

        //获取指定路径的class文件对应的二进制流数据
        byte[] data = getBytesFromPath(filePath);

        //自定义ClassLoader 内部调用defineClass()
        return defineClass(name,data,0,data.length);

    }

    private byte[] getBytesFromPath(String filePath) {

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(filePath);

            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;

            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    private String classToFilePath(String name) {

        return rootPath + "/" + name.replace(".", "/") + ".class";

    }

    public static void main(String[] args) {  //加载jvm.bootstrap.classloader.User
        try {
            UserDefineClassLoader loader1 = new UserDefineClassLoader("/Users/lizhijun/Documents/GitHub/mars/test/doc");
            Class userClass1 = loader1.findClass("jvm.bootstrap.classloader.User");
            System.out.println(userClass1);

            UserDefineClassLoader loader2 = new UserDefineClassLoader("/Users/lizhijun/Documents/GitHub/mars/test/doc");
            Class userClass2 = loader2.findClass("jvm.bootstrap.classloader.User");

            System.out.println(userClass1 == userClass2);//实现了加载的类的隔离


            System.out.println(userClass1.getClassLoader());
            System.out.println(userClass1.getClassLoader().getParent());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }
}