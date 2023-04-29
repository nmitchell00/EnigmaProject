package com.enigma;

public interface Encryptor {

    Position encryptForward(Position letter);

    Position encryptReverse(Position letter);

    String changeArray(char[] plugSettings);
}
