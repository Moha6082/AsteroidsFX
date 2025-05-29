package dk.sdu.cbse.asteroids;

import dk.sdu.CommonAsteroids.Asteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidsPlugin implements IGamePluginService {
    private static final int ASTEROID_COUNT = 10;
    private static final double MIN_SPEED = 30;
    private static final double MAX_SPEED = 80;

    private final Random rnd = new Random();

    @Override
    public void start(GameData gameData, World world) {
        int w = gameData.getDisplayWidth();
        int h = gameData.getDisplayHeight();

        for (int i = 0; i < ASTEROID_COUNT; i++) {
            Asteroids a = new Asteroids();

            // Størrelse
            float size = rnd.nextInt(7) + 6;
            a.setRadius(size);
            a.setPolygonCoordinates(
                    size, -size,
                    -size, -size,
                    -size,  size,
                    size,  size
            );

            a.setX(rnd.nextDouble() * w);
            a.setY(rnd.nextDouble() * h);

            double angle = rnd.nextDouble() * 360;
            a.setRotation(angle);

            double speed = MIN_SPEED + rnd.nextDouble() * (MAX_SPEED - MIN_SPEED);
            double rad = Math.toRadians(angle);
            a.setDx(Math.cos(rad) * speed);
            a.setDy(Math.sin(rad) * speed);

            world.addEntity(a);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Asteroids.class)
                .forEach(world::removeEntity);
    }
}
