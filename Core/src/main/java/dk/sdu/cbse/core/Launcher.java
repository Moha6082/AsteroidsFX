package dk.sdu.cbse.core;

import java.lang.ModuleLayer;                // <— CORRECT import
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Launcher {
    public static void main(String[] args) throws Exception {
        // ── 1) Boot-layer: Core + Player JARs
        Path coreJar   = Paths.get("Core/target/Core-1.0-SNAPSHOT.jar");
        Path playerJar = Paths.get("Player/target/Player-1.0-SNAPSHOT.jar");
        ModuleFinder bootFinder = ModuleFinder.of(coreJar, playerJar);

        Configuration bootConfig = ModuleLayer.boot()
                .configuration()
                .resolve(bootFinder,
                        ModuleFinder.of(),
                        List.of("dk.sdu.cbse.core", "dk.sdu.cbse.player"));

        ModuleLayer bootLayer = ModuleLayer.boot()
                .defineModulesWithOneLoader(bootConfig,
                        ClassLoader.getSystemClassLoader());

        // ── 2) Plugin-layer: Bullet JAR fra Plugins\
        Path bulletJar = Paths.get("Plugins/Bullet-1.0-SNAPSHOT.jar");
        ModuleFinder plugFinder = ModuleFinder.of(bulletJar);

        Configuration plugConfig = bootConfig
                .resolve(plugFinder,
                        ModuleFinder.of(),
                        List.of("dk.sdu.cbse.bullet"));

        ModuleLayer pluginLayer = bootLayer
                .defineModulesWithOneLoader(plugConfig,
                        ClassLoader.getSystemClassLoader());

        // ── 3) Invoke Core.Main fra bootLayer
        Class<?> mainCls = Class.forName(
                "dk.sdu.cbse.core.Main", true,
                bootLayer.findLoader("dk.sdu.cbse.core")
        );
        mainCls.getMethod("main", String[].class)
                .invoke(null, (Object) args);
    }
}
