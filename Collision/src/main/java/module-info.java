module Collision {
    requires Common;
    requires CommonAsteroids;
    requires dk.sdu.cbse.player;
    requires Enemy;

    exports dk.sdu.cbse.collision;

    uses dk.sdu.CommonAsteroids.SplitAsteroids;

    provides dk.sdu.cbse.common.services.IPostEntityProcessingService
            with dk.sdu.cbse.collision.CollisionSystem;
}