package br.eng.crisjr.sheep.Model;

public class Sheep {
    private String name = null;
    private int count = 0;

    public Sheep() {};
    public Sheep(String name) {
        this.setName(name);
    }

    /* gets and sets */
    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }

    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    public int setCount(int count) {
        this.count = count;
        return this.count;
    }
}
