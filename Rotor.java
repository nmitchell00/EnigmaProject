package com.enigma;

/**
 * Interface to capture the unique behaviour of the rotors
 */
public interface Rotor extends Encryptor {

    void setRotorPosition(Position pos);

    void setRingPosition(Position pos);

    boolean rotate();

    int getRotorPosition();

}
