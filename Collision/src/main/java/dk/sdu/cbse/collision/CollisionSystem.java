package dk.sdu.cbse.collision;

/*
import dk.sdu.cbse.common.asteroid.IAsteroidSplitter;      // SPI for splitting large asteroids
import dk.sdu.cbse.common.bullet.Bullet;                  // Bullet entity type
import dk.sdu.cbse.common.data.Entity;                    // Base entity
import dk.sdu.cbse.common.data.GameData;                  // Game state container
import dk.sdu.cbse.common.data.Health;                    // Optional health component
import dk.sdu.cbse.common.data.World;                     // World holding all entities
import dk.sdu.cbse.common.services.IPostEntityProcessingService; // Runs after all movement
import dk.sdu.cbse.enemy.Enemy;                           // Enemy entity type
import dk.sdu.cbse.playersystem.Player;                   // Player entity type
import dk.sdu.cbse.asteroid.Asteroid;                     // Asteroid entity type

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class CollisionSystem implements IPostEntityProcessingService {

    // SPI loader: find an IAsteroidSplitter implementation if present,
    // otherwise use a no-op lambda so split calls are safe.
    private final IAsteroidSplitter splitter = ServiceLoader
            .load(IAsteroidSplitter.class)
            .findFirst()
            .orElse((asteroid, w) -> {});

    @Override
    public void process(GameData gameData, World world) {
        // Copy lists to avoid concurrent-modification when removing entities
        List<Entity> bullets   = new ArrayList<>(world.getEntities(Bullet.class));
        List<Entity> players   = new ArrayList<>(world.getEntities(Player.class));
        List<Entity> enemies   = new ArrayList<>(world.getEntities(Enemy.class));
        List<Entity> asteroids = new ArrayList<>(world.getEntities(Asteroid.class));

        // 1) Bullet vs Enemy
        for (Entity b : bullets) {
            for (Entity e : enemies) {
                if (collide(b, e)) {
                    world.removeEntity(b);
                    world.removeEntity(e);
                    break;
                }
            }
        }

        // 2) Bullet vs Player
        for (Entity b : bullets) {
            for (Entity p : players) {
                if (collide(b, p)) {
                    world.removeEntity(b);
                    damage(p, world);
                    break;
                }
            }
        }

        // 3) Bullet vs Asteroid
        for (Entity b : bullets) {
            for (Entity a : asteroids) {
                if (collide(b, a)) {
                    world.removeEntity(b);
                    split(a, world);
                    break;
                }
            }
        }

        // 4) Player vs Enemy
        for (Entity p : players) {
            for (Entity e : enemies) {
                if (collide(p, e)) {
                    world.removeEntity(p);
                    world.removeEntity(e);
                    break;
                }
            }
        }

        // 5) Player vs Asteroid
        for (Entity p : players) {
            for (Entity a : asteroids) {
                if (collide(p, a)) {
                    world.removeEntity(p);
                    split(a, world);
                    break;
                }
            }
        }

        // 6) Enemy vs Asteroid
        for (Entity e : enemies) {
            for (Entity a : asteroids) {
                if (collide(e, a)) {
                    world.removeEntity(e);
                    break;
                }
            }
        }
    }

    // Circle-vs-circle collision check
    private boolean collide(Entity a, Entity b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.hypot(dx, dy) < (a.getRadius() + b.getRadius());
    }

    // Split and remove an asteroid
    private void split(Entity asteroid, World world) {
        splitter.SplitAsteroid(asteroid, world);
        world.removeEntity(asteroid);
    }

    // Reduce health by 1; remove entity if health ≤ 0
    private void damage(Entity target, World world) {
        Health h = target.getComponent(Health.class);
        if (h != null) {
            h.setHealth(h.getHealth() - 1);
            if (h.getHealth() <= 0) {
                world.removeEntity(target);
            }
        }
    }
}
*/
