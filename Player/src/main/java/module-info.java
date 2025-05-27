module Player {
    requires Common;
    requires java.desktop;              // til key‐events, hvis du bruger det

    // Fortæl, at vi bruger dette SPI‐interface:
    uses dk.sdu.cbse.common.bullet.BulletSPI;

    exports dk.sdu.cbse.player;

    provides dk.sdu.cbse.common.services.IGamePluginService
            with dk.sdu.cbse.player.PlayerPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService
            with dk.sdu.cbse.player.PlayerControlSystem;
}
