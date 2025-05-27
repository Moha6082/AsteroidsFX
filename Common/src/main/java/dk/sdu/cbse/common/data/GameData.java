package dk.sdu.cbse.common.data;

public class GameData {

    private int displayWidth  = 800;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();

    // ----- NYT felt til tids-delta -----
    private float deltaTime;

    public GameKeys getKeys() {
        return keys;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    // ----- NYE metoder -----
    public float getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public double getDelta() {
        return deltaTime;
    }
}
