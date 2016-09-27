package titarenko.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MyMac on 27.09.16.
 */
@Controller
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class,args);
    }

//    @RequestMapping("/hello")
//    public String showHelloWorld(){
//        return "hello-world";
//    }
}
