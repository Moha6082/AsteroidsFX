module Enemy {
    requires Common;       // to see GameData, World, Entity, etc.

    exports dk.sdu.cbse.enemy;

    provides dk.sdu.cbse.common.services.IGamePluginService
            with dk.sdu.cbse.enemy.EnemyPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService
            with dk.sdu.cbse.enemy.EnemyControlSystem;
}
