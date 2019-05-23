package GroovyCommonUtils;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

public class GroovyCommonUtil2 {
    /*private static final Logger log = LoggerFactory.getLogger(GroovyCommonUtil.class);*/
    //该变量用于指明groovy脚本所在的父目录
    static String root ="src/groovys/";
    static GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    /**
     * 用于调用指定Groovy脚本中的指定方法
     *
     * @param scriptName 脚本名称
     * @param methodName 方法名称
     * @param params     方法参数
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public Object invokeMethod(String scriptName, String methodName, Object... params) throws Exception {
        Object ret = null;
        Class scriptClass = null;
        GroovyObject scriptInstance = null;
        String scriptPath = root+scriptName;
        System.out.println(scriptPath+methodName+params);
        try {
            scriptClass = groovyClassLoader.parseClass(new File(scriptPath));
            scriptInstance = (GroovyObject) scriptClass.newInstance();//
        } catch (InstantiationException | IllegalAccessException e1) {
            //log.warn("加载脚本["+scriptName+"]出现异常", e1);
            throw new Exception("加载脚本" + scriptName + "失败");
        }

        try {
            ret = scriptInstance.invokeMethod(methodName, params);
        } catch (IllegalArgumentException e) {

            throw new Exception("调用方法[" + methodName + "]失败，因参数不合法");
        } catch (Exception e) {

            throw new Exception("调用方法[" + methodName + "]失败");
        }


        return ret;
    }
}