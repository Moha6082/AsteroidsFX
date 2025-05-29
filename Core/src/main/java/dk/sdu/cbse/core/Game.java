package dk.sdu.cbse.core;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Game {

    private final GameData gameData = new GameData();
    private final World world   = new World();

    private final List<IGamePluginService>         plugins;
    private final List<IEntityProcessingService>   processors;
    private final List<IPostEntityProcessingService> postProcessors;

    private final Map<String, Node> entityViews = new HashMap<>();
    private final Set<KeyCode>    activeKeys  = new HashSet<>();

    private Pane  gamePane;
    private Scene scene;

    public Game(
            List<IGamePluginService> plugins,
            List<IEntityProcessingService> processors,
            List<IPostEntityProcessingService> postProcessors
    ) {
        this.plugins = plugins;
        this.processors = processors;
        this.postProcessors = postProcessors;
    }

    public void start(Stage primaryStage) {
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: darkgreen;");
        scene = new Scene(gamePane, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ASTEROIDSFX");
        primaryStage.show();

        setupKeyHandling();
        plugins.forEach(p -> p.start(gameData, world));

        resizeArena();
        scene.widthProperty().addListener((obs, o, n) -> resizeArena());
        scene.heightProperty().addListener((obs, o, n) -> resizeArena());

        new AnimationTimer() {
            private long last = System.nanoTime();
            @Override
            public void handle(long now) {
                float dt = (now - last) / 1_000_000_000f;
                last = now;
                gameData.setDeltaTime(dt);
                updateKeys();

                processors.forEach(p -> p.process(gameData, world));
                postProcessors.forEach(p -> p.process(gameData, world));
                syncViews();
            }
        }.start();
    }

    private void setupKeyHandling() {
        scene.setOnKeyPressed  (e -> activeKeys.add   (e.getCode()));
        scene.setOnKeyReleased (e -> activeKeys.remove(e.getCode()));
    }

    private void updateKeys() {
        GameKeys keys = gameData.getKeys();
        keys.setKey(GameKeys.UP,activeKeys.contains(KeyCode.HOME)    || activeKeys.contains(KeyCode.UP));
        keys.setKey(GameKeys.LEFT,activeKeys.contains(KeyCode.PAGE_UP) || activeKeys.contains(KeyCode.LEFT));
        keys.setKey(GameKeys.RIGHT,activeKeys.contains(KeyCode.PAGE_DOWN)|| activeKeys.contains(KeyCode.RIGHT));
        keys.setKey(GameKeys.SPACE,activeKeys.contains(KeyCode.END)     || activeKeys.contains(KeyCode.SPACE));
        keys.update();
    }

    private void resizeArena() {
        double w=scene.getWidth(), h = scene.getHeight();
        gamePane.setPrefSize(w, h);
        gameData.setDisplayWidth ((int) w);
        gameData.setDisplayHeight((int) h);
    }

    private void syncViews() {
        entityViews.entrySet().removeIf(ent -> {
            if (world.getEntity(ent.getKey()) == null) {
                gamePane.getChildren().remove(ent.getValue());
                return true;
            }
            return false;
        });

        for (Entity e : world.getEntities()) {
            Node view = entityViews.get(e.getID());
            if (view == null) {
                Polygon poly = new Polygon(e.getPolygonCoordinates());
                poly.setStrokeWidth(3);
                if ("Enemy".equals(e.getType())) {
                    poly.setFill(Color.RED);
                } else {
                    poly.setFill(Color.WHITE);
                }
                gamePane.getChildren().add(poly);
                entityViews.put(e.getID(), poly);
                view = poly;
            }
            view.setTranslateX(e.getX());
            view.setTranslateY(e.getY());
            view.setRotate(e.getRotation());
        }
    }
}
