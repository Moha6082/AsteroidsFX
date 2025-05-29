module Core {
    requires javafx.controls;
    requires javafx.graphics;

    requires Common;

    requires spring.core;
    requires spring.beans;
    requires spring.context;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;

    opens dk.sdu.cbse.core
            to spring.core, spring.beans, spring.context;

    exports dk.sdu.cbse.core;
}