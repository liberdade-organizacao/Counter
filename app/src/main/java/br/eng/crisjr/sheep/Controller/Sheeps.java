package br.eng.crisjr.sheep.Controller;

import java.util.ArrayList;

import android.content.Context;
import br.eng.crisjr.sheep.Model.Database;
import br.eng.crisjr.sheep.Model.Sheep;

/**
 * Class that implements the controller. I know that the plural of sheep is sheep
 * but I needed to differentiate the model from the controller somehow. SheepController
 * would occupy too much space and make me waste time.
 */
public class Sheeps {
    private ArrayList<Sheep> sheeps = null;
    private Database data = null;

    public Sheeps() {
        this.data = new Database();
        this.sheeps = new ArrayList<Sheep>();
    }

    public Sheeps(Context context)
    {
        this();
        this.sheeps = this.data.retrieve(context);
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

    public ArrayList<Sheep> retrieve(Context context)
    {
        return setSheeps(data.retrieve(context));
    }

    /**
     * Saves the current sheep array to memory
     */
    public void store(Context context)
    {
        this.data.store(context, this.sheeps);
    }
}
