package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.player.Player;
import dk.sdu.cbse.player.PlayerControlSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {
private GameData gameData;
private World world;
private PlayerControlSystem system;
private Player player;

@BeforeEach
void setUp() {
    gameData = new GameData();
    gameData.setDisplayWidth(800);
    gameData.setDisplayHeight(600);
    world = new World();
    system = new PlayerControlSystem();
    player = new Player();
    // initialize player position and rotation
    player.setX(400);
    player.setY(300);
    player.setRotation(0);
    world.addEntity(player);
}

@Test
void testRotateLeft() {
    gameData.getKeys().setKey(GameKeys.LEFT, true);
    system.process(gameData, world);
    assertEquals(-5.0, player.getRotation(), 0.0001);
}

@Test
void testRotateRight() {
    gameData.getKeys().setKey(GameKeys.RIGHT, true);
    system.process(gameData, world);
    assertEquals(5.0, player.getRotation(), 0.0001);
}

@Test
void testMoveUp() {
    gameData.getKeys().setKey(GameKeys.UP, true);
    system.process(gameData, world);
    assertEquals(401.0, player.getX(), 0.0001);
    assertEquals(300.0, player.getY(), 0.0001);
}

@Test
void testBoundaryWrapLeft() {
    player.setX(-10);
    system.process(gameData, world);
    assertEquals(1.0, player.getX(), 0.0001);
}

@Test
void testBoundaryWrapRight() {
    player.setX(gameData.getDisplayWidth() + 10);
    system.process(gameData, world);
    assertEquals(gameData.getDisplayWidth() - 1, player.getX(), 0.0001);
}

@Test
void testBoundaryWrapTop() {
    player.setY(-20);
    system.process(gameData, world);
    assertEquals(1.0, player.getY(), 0.0001);
}

@Test
void testBoundaryWrapBottom() {
    player.setY(gameData.getDisplayHeight() + 20);
    system.process(gameData, world);
    assertEquals(gameData.getDisplayHeight() - 1, player.getY(), 0.0001);
}
}
