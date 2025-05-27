package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        // Hvis du vil initialisere noget ved spilstart, gør det her
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Fjern alle bullets, når modulet stoppes
        for (Entity e : world.getEntities(Bullet.class)) {
            world.removeEntity(e);
        }
    }
}
