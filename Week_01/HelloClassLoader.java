package com.zhoubin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器
 *
 * @author: ZhouBin
 * @date: 2020/10/16 12:08 下午
 */
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("com.zhoubin.Hello").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String myPath = "file:/Users/zhoubin/IdeaProjects/zhoubin-foodie/foodie-api/src/test/java/com/zhoubin/Hello.class";
        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(myPath));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
        return clazz;
    }
}