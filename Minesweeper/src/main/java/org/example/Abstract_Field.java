package org.example;

public abstract class Abstract_Field implements Interface_Field{
    private int size;
    private int minesAround;
    private boolean isMine;

    Abstract_Field() {
        setMinesAround(0);
    }

    abstract void FieldDefinition2();

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getisMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }
}
