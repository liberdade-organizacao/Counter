package br.eng.crisjr.sheep.Controller;

import java.util.ArrayList;
import br.eng.crisjr.sheep.Model.Sheep;

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

    public Sheep removeSheep(int index) {
        Sheep sheep = this.sheeps.get(index);
        this.sheeps.remove(index);
        return sheep;
    }
}
