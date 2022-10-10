import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.itheima.config.SpringConfig;
import com.itheima.controller.UserController;

//这个测试是基于spring而不是springmvc,也就是说spring扫描不到bean对象，不代表springmvc中找不到对象

/* 这个类文件主要是用来查看类的bean是否被加载了,启动该项目不需要这个类文件.
mvn tomcat7:run
在idea中配置tomcat7:run
 */

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(ctx.getBean(UserController.class));
    }
}
