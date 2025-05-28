package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;
public class EnemyPlugin implements IGamePluginService {

    private static final int NUM_ENEMIES = 5;
    private final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < NUM_ENEMIES; i++) {
            world.addEntity(createEnemy(gameData));
        }
    }

    private Enemy createEnemy(GameData gd) {
        Enemy e = new Enemy();
        e.setType("Enemy");

        // triangle shape
        e.setPolygonCoordinates(
                0, -8,
                6,  6,
                -6,  6
        );

        // random position on screen
        e.setX(random.nextDouble() * gd.getDisplayWidth());
        e.setY(random.nextDouble() * gd.getDisplayHeight());

        // random rotation
        e.setRotation(random.nextDouble() * 360.0);

        // set movement vector based on that heading
        double rad = Math.toRadians(e.getRotation());
        e.setDx((float)(Math.cos(rad) * Enemy.SPEED));
        e.setDy((float)(Math.sin(rad) * Enemy.SPEED));

        e.setRadius(8);

        return e;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // remove all enemies
        world.getEntities(Enemy.class).forEach(world::removeEntity);
    }
}
