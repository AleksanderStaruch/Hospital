package model;


public interface ISurgeon{

    enum Type{NEURO, CARDIO, THORACIC}


    void setType(Type type);
    Type getType();
}
