package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import root.controller.MainController;

@SpringBootApplication
public class App implements CommandLineRunner {

    MainController mainController;

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    public App(MainController mainController) {
        this.mainController = mainController;
    }

    public static void main(String[] args) {

        logger.info("L0G: main(): Begin.");

        SpringApplication.run(App.class, args);

        logger.info("L0G: main(): End.");

    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("L0G: run(): Begin.");

        mainController.major();

        logger.info("L0G: run(): End.");

/*
        logger.info("EXECUTING : command line runner");

        for (int i = 0; i < args.length; ++i) {
            logger.info("args[{}]: {}", i, args[i]);
        }
*/

    }

/*
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            logger.info("L0G: commandLineRunner(): " +
                    "Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
*/

}
