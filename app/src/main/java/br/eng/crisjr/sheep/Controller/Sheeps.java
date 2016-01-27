package br.eng.crisjr.sheep.Controller;

import java.util.ArrayList;
import br.eng.crisjr.sheep.Model.Sheep;

/**
 * Class that implements the controller. I know that the plural of sheep is sheep
 * but I needed to differentiate the model from the controller somehow. SheepController
 * would occupy too much space and make me waste time.
 */
public class Sheeps {
    private ArrayList<Sheep> sheeps = null;

    public Sheeps() {
        this.sheeps = new ArrayList<Sheep>();
    }

    public Sheeps(String path) {
        this();
    }

    public void addSheep(String name) {
        Sheep sheep = new Sheep(name);
        this.sheeps.add(sheep);
    }

    /**
     * @return the current arraylist of sheeps
     */
    public ArrayList<Sheep> getSheeps() {
        return this.sheeps;
    }
    public ArrayList<Sheep> setSheeps(ArrayList<Sheep> sheeps)
    {
        this.sheeps = sheeps;
        return this.sheeps;
    }

    public Sheep removeSheep(int index) {
        Sheep sheep = this.sheeps.get(index);
        this.sheeps.remove(index);
        return sheep;
    }

    public Sheep createSheep(String name, int count) {
        Sheep sheep = new Sheep();
        sheep.setName(name);
        sheep.setCount(count);
        return sheep;
    }
}
