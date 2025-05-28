package dk.sdu.cbse.asteroids;

import dk.sdu.CommonAsteroids.Asteroids;
import dk.sdu.CommonAsteroids.SplitAsteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import java.util.Random;

public class IAsteroidSplitter implements SplitAsteroids {

    private static final Random rnd = new Random();
    private static final double MIN_SIZE = 5.0; // Ændret til double

    @Override
    public void createSplitAsteroid(Entity original, World world, GameData gameData) {
        if (!(original instanceof Asteroids)) {
            return;
        }

        double originalRadius = original.getRadius(); // Brug double konsekvent

        if (originalRadius <= MIN_SIZE) {
            world.removeEntity(original);
            return;
        }

        double newRadius = originalRadius / 1.5; // Brug double
        double x = original.getX();
        double y = original.getY();

        int fragments = 2 + rnd.nextInt(2);

        for (int i = 0; i < fragments; i++) {
            Asteroids fragment = new Asteroids();
            fragment.setRadius((float)newRadius);

            float polyRadius = (float)newRadius;
            fragment.setPolygonCoordinates(
                    polyRadius, -polyRadius,
                    -polyRadius, -polyRadius,
                    -polyRadius, polyRadius,
                    polyRadius, polyRadius
            );

            // Placering med offset
            double offsetX = (rnd.nextDouble() - 0.5) * originalRadius * 1.5;
            double offsetY = (rnd.nextDouble() - 0.5) * originalRadius * 1.5;
            fragment.setX(x + offsetX);
            fragment.setY(y + offsetY);

            // Rotation og bevægelse
            fragment.setRotation(rnd.nextDouble() * 360);

            double angle = Math.atan2(offsetY, offsetX);
            double speed = 50.0 + rnd.nextDouble() * 50.0;
            fragment.setDx(Math.cos(angle) * speed);
            fragment.setDy(Math.sin(angle) * speed);

            world.addEntity(fragment);
        }

        world.removeEntity(original);
    }
}