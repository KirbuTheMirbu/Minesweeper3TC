package org.example;

public class Field extends Abstract_Field {
    Field(){
        super();
        FieldDefinition();
    }

    @Override
    void FieldDefinition2() {
        return;
    }

    @Override
    public void FieldDefinition() {
        setMine(false);
    }
}
