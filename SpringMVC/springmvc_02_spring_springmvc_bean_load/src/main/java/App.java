import com.itheima.config.SpringConfig;
import com.itheima.controller.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//这个测试是基于spring而不是springmvc,也就是说spring扫描不到bean对象，不代表springmvc中找不到对象
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(ctx.getBean(UserController.class));
    }
}
