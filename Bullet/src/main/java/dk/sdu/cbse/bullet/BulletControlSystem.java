package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setPolygonCoordinates(
                1, -1,
                1,  1,
                -1,  1,
                -1, -1
        );
        double angleRad = Math.toRadians(shooter.getRotation());
        double spawnDistance = 12;
        bullet.setX(shooter.getX() + Math.cos(angleRad) * spawnDistance);
        bullet.setY(shooter.getY() + Math.sin(angleRad) * spawnDistance);

        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(2);
        return bullet;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity proj : world.getEntities(Bullet.class)) {
            double velocityX = Math.cos(Math.toRadians(proj.getRotation())) * 4;
            double velocityY = Math.sin(Math.toRadians(proj.getRotation())) * 4;
            proj.setX(proj.getX() + velocityX);
            proj.setY(proj.getY() + velocityY);

           
        }
    }
}
