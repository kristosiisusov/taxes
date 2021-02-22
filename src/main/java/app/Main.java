package app;

import app.entityes.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
public class Main {

    public static User user;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
