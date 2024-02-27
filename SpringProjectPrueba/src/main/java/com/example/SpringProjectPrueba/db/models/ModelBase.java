package com.example.SpringProjectPrueba.db.models;

abstract class ModelBase {
    public ModelBase(){

    }

    public ModelBase(String json){
        
    }

    public abstract String toString();

    public abstract String toJSON();
}
