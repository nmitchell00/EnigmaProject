package com.enigma;

/**
 * Interface to encapsulate the similar behaviour of all components
 */

public interface Encryptor {

    Position encryptForward(Position letter);

    Position encryptReverse(Position letter);

    String changeArray(char[] plugSettings);
}
