package dk.sdu.cbse.asteroids;

import dk.sdu.CommonAsteroids.Asteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidMovementSystem implements IEntityProcessingService {

    private double elapsedTime = 0.0;
    private static final double ACCELERATION_RATE = 0.05;

    @Override
    public void process(GameData gameData, World world) {
        double delta = gameData.getDelta();
        elapsedTime += delta;

        double speedFactor = 1 + ACCELERATION_RATE * elapsedTime;

        for (Entity e : world.getEntities(Asteroids.class)) {
            double dx = e.getDx() * delta * speedFactor;
            double dy = e.getDy() * delta * speedFactor;
            e.setX(e.getX() + dx);
            e.setY(e.getY() + dy);

            double r = e.getRadius();
            double w = gameData.getDisplayWidth();
            double h = gameData.getDisplayHeight();
            if (e.getX() < -r)       e.setX(w + r);
            else if (e.getX() > w + r) e.setX(-r);
            if (e.getY() < -r)       e.setY(h + r);
            else if (e.getY() > h + r) e.setY(-r);
        }
    }
}
