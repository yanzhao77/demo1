package javac;

import GroovyCommonUtils.GroovyCommonUtil;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.IOException;

public class test2 {
   /* static Object runWithGroovyClassLoader() throws Exception {
        // 获取当前资源路径，用于指定Groovy脚本
        String base_path = "F:\\IDEADownloads\\jdk10GroovyDemo1\\src\\Groovys\\";
        // 使用当前线程的context创建GroovyClassLoader
        // parseClass()方法将文件解析成可以运行的class
        Class aClass = new GroovyClassLoader().parseClass(new File(base_path + "test1.groovy"));
        // 创建此 Class 对象所表示的类的一个新实例
        GroovyObject groovyObject = (GroovyObject) aClass.newInstance();
        // groovy 方法的入参，多个参数从左到右书写，无入参保持为空new Object[]{}
        Object[] objects = new Object[]{1, 3, 2};
        // 调用方法 testC 并获得返回值(如果后者存在)
        return groovyObject.invokeMethod("mm", objects);
    }*/

    public static void main(String[] args) {
      /*  GroovyShell groovyShell = new GroovyShell();
        // 调用带参数的groovy shell时，使用bind绑定数据
        Binding binding = new Binding();
        binding.setProperty("mm", "Juxinli");

        try {
            Object result =  groovyShell.evaluate(new File("F:\\IDEADownloads\\jdk10GroovyDemo1\\src\\Groovys\\test1"));
        System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

       /* GroovyShell groovyShell = new GroovyShell();
        groovyShell.evaluate("println 'My First Groovy shell.'");*/
       /* GroovyCommonUtil gc = new GroovyCommonUtil();
        String s="--open F:\\autoFileMerge.groovy";
        try {
            gc.invokeMethod("test2", "mm",s);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        GroovyCommonUtil gc = new GroovyCommonUtil();
        String path="--open F:/autoFileMerge.groovy";
        try {
            gc.invokeMethod("test2", "mm", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}