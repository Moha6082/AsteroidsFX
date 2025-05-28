module Asteroids {
    requires Common;
    requires CommonAsteroids;
    requires javafx.graphics;
    requires javafx.controls;

    exports dk.sdu.cbse.asteroids;

    uses dk.sdu.CommonAsteroids.SplitAsteroids;

    provides dk.sdu.CommonAsteroids.SplitAsteroids
            with dk.sdu.cbse.asteroids.IAsteroidSplitter;

    provides dk.sdu.cbse.common.services.IGamePluginService
            with dk.sdu.cbse.asteroids.AsteroidsPlugin;

    provides dk.sdu.cbse.common.services.IEntityProcessingService
            with dk.sdu.cbse.asteroids.AsteroidMovementSystem;

    provides dk.sdu.cbse.common.services.IPostEntityProcessingService
            with dk.sdu.cbse.asteroids.AsteroidsControlSystem;

}
