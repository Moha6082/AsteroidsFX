package dk.sdu.cbse.asteroids;

import dk.sdu.CommonAsteroids.Asteroids;
import dk.sdu.CommonAsteroids.SplitAsteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class AsteroidsControlSystem implements IPostEntityProcessingService {

    private final SplitAsteroids splitter = ServiceLoader
            .load(SplitAsteroids.class)
            .findFirst()
            .orElse((orig, w, gd) -> {});

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> copy = new ArrayList<>(world.getEntities(Asteroids.class));
        for (Entity e : copy) {
            if (e.getDeath()) {
                splitter.createSplitAsteroid(e, world, gameData);
                world.removeEntity(e);
            }
        }
        if (world.getEntities(Asteroids.class).isEmpty()) {
            new AsteroidsPlugin().start(gameData, world);
        }
    }
}
