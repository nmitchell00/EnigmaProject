package com.enigma;

public interface Rotor extends Encryptor {

    void setRotorPosition(Position pos);

    void setRingPosition(Position pos);

    boolean rotate();

    int getRotorPosition();

}
