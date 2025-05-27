package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        float dt = gameData.getDeltaTime();
        int w = gameData.getDisplayWidth();
        int h = gameData.getDisplayHeight();

        for (Entity en : world.getEntities(Enemy.class)) {
            Enemy e = (Enemy) en;

            // spin
            e.setRotation(e.getRotation() + Enemy.ROT_SPEED * dt);

            // move
            double x = e.getX() + e.getDx() * dt;
            double y = e.getY() + e.getDy() * dt;

            // wrap
            if (x < 0)      x = w;
            else if (x > w) x = 0;
            if (y < 0)      y = h;
            else if (y > h) y = 0;

            e.setX(x);
            e.setY(y);
        }
    }
}
