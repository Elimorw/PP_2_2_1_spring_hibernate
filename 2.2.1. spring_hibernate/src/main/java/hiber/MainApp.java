package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car lada = new Car("Calina", 32);
      Car bmw = new Car("M5", 123);
      Car volvo = new Car("90", 342);
      Car mazda = new Car("RX-7", 2);

      userService.add(user1.setCar(lada).setUser(user1));
      userService.add(user2.setCar(bmw).setUser(user2));
      userService.add(user3.setCar(volvo).setUser(user3));
      userService.add(user4.setCar(mazda).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }
      System.out.println(userService.getUserByCar("M5", 123));

      context.close();
   }
}
