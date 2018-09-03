package itsm.clientMain;

import itsm.springGreetingsClient.client.SpringGreetingsClient;
import itsm.springGreetingsClient.configurations.ClientConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        String message;
        if (args.length == 0) {
            System.out.println("add 1 param to this command");
        }
        else {
            message = args[0];
           String response = context.getBean(SpringGreetingsClient.class).sendRequest(message);
           System.out.println(response);
        }

    }
}
