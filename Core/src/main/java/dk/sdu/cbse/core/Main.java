package dk.sdu.cbse.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ModuleConfig.class);

        ctx.getBean(Game.class).start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
