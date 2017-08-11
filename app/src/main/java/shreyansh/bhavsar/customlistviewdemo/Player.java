package shreyansh.bhavsar.customlistviewdemo;

/**
 * Created by shreyansh.bhavsar on 6/11/2017.
 */

class Player {

    public final String name;
    public final int runs;

    public Player(String name, int runs) {
        this.name = name;
        this.runs = runs;
    }

    public String getName() {
        return name;
    }

    public int getRuns() {
        return runs;
    }
}
