package org.example;

public class Mine extends Abstract_Field{
    Mine(){
        super();
        FieldDefinition();
    }

    @Override
    void FieldDefinition2() {
        return;
    }

    @Override
    public void FieldDefinition() {
        setMine(true);
    }
}
