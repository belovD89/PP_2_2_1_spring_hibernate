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

        //users
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        //cars
        Car car1 = new Car("CAR1", "SERIES1", user1);
        Car car2 = new Car("CAR2", "SERIES2", user2);
        Car car3 = new Car("CAR3", "SERIES3", user3);
        Car car4 = new Car("CAR4", "SERIES4", user4);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

       // Получение владельца по модели и серии авто
       System.out.println("Получение владельца по модели и серии:");
       User user = userService.getUserByCar("CAR1", "SERIES1");
       System.out.println(user);

        context.close();
    }
}
