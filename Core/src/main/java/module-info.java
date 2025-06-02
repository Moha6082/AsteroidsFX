module Core {
    requires Common;
    requires Player;
    requires Bullet;

    requires javafx.controls;
    requires javafx.graphics;

    exports dk.sdu.cbse.core;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;  // Tilføjet!
    uses dk.sdu.cbse.common.bullet.BulletSPI;
}