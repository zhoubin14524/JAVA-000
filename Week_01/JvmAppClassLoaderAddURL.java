import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 拿到当前执行类的ClassLoader，反射调用addUrl方法添加jar或者路径
 *
 * @author: ZhouBin
 * @date: 2020/10/16 12:28 下午
 */
public class JvmAppClassLoaderAddURL {
    public static void main(String[] args) {
        String appPath = "file:/Users/zhoubin/IdeaProjects/zhoubin-foodie/foodie-api/src/test/java";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();

        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader,url);
            Class.forName("Hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}