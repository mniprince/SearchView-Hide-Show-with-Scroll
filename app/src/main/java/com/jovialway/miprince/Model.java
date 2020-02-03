package com.jovialway.miprince;

public class Model {
    String poemName;
    String poemWriter;
    String poem;



    public Model(String poemName, String poemWriter, String poem) {
        this.poemName = poemName;
        this.poemWriter = poemWriter;
        this.poem = poem;

    }




    public String getPoemName() {
        return poemName;
    }

    public String getPoemWriter() {
        return poemWriter;
    }

    public String getPoem() {
        return poem;
    }


}