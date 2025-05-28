package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import dk.sdu.cbse.player.Player;
import dk.sdu.cbse.enemy.Enemy;

import dk.sdu.CommonAsteroids.Asteroids;
import dk.sdu.CommonAsteroids.SplitAsteroids;

import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;



public class CollisionSystem implements IPostEntityProcessingService {

    private final SplitAsteroids splitter = ServiceLoader
            .load(SplitAsteroids.class)
            .findFirst()
            .orElse((ast, w, gd) -> {});

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> bullets   = new ArrayList<>(world.getEntities(Bullet.class));
        List<Entity> players   = new ArrayList<>(world.getEntities(Player.class));
        List<Entity> enemies   = new ArrayList<>(world.getEntities(Enemy.class));
        List<Entity> asteroids = new ArrayList<>(world.getEntities(Asteroids.class));


        for (Entity b : bullets) {
            for (Entity e : enemies) {
                if (collide(b, e)) {
                    world.removeEntity(b);
                    world.removeEntity(e);
                    break;
                }
            }
        }


        for (Entity b : bullets) {
            for (Entity p : players) {
                if (collide(b, p)) {
                    world.removeEntity(b);
                    break;
                }
            }
        }


        for (Entity b : bullets) {
            for (Entity a : asteroids) {
                if (collide(b, a)) {
                    world.removeEntity(b);
                    splitter.createSplitAsteroid(a, world, gameData);
                    world.removeEntity(a);
                    break;
                }
            }
        }


        for (Entity p : players) {
            for (Entity e : enemies) {
                if (collide(p, e)) {
                    world.removeEntity(p);
                    world.removeEntity(e);
                    break;
                }
            }
        }

        for (Entity p : players) {
            for (Entity a : asteroids) {
                if (collide(p, a)) {
                    world.removeEntity(p);
                    splitter.createSplitAsteroid(a, world, gameData);
                    world.removeEntity(a);
                    break;
                }
            }
        }

        for (Entity e : enemies) {
            for (Entity a : asteroids) {
                if (collide(e, a)) {
                    world.removeEntity(e);
                    break;
                }
            }
        }
    }

    private boolean collide(Entity a, Entity b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.hypot(dx, dy) < (a.getRadius() + b.getRadius());
    }
}
