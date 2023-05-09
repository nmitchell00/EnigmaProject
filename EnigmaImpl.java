package com.enigma;

public class EnigmaImpl {

    public final Encryptor reflector, plugboard;
    public final Rotor[] rotors = new Rotor[3];
    private Rotor[] allRotors;

    public EnigmaImpl() {
        char[] r1array = {'E','K','M','F','L','G','D','Q','V','Z','N','T','O','W','Y','H','X','U','S','P','A','I','B','R','C','J'};
        char[] r2array = {'A','J','D','K','S','I','R','U','X','B','L','H','W','T','M','C','Q','G','Z','N','P','Y','F','V','O','E'};
        char[] r3array = {'B','D','F','H','J','L','C','P','R','T','X','V','Z','N','Y','E','I','W','G','A','K','M','U','S','Q','O'};
        char[] r4array = {'E','S','O','V','P','Z','J','A','Y','Q','U','I','R','H','X','L','N','F','T','G','K','D','C','M','W','B'};
        char[] r5array = {'V','Z','B','R','G','I','T','Y','U','P','S','D','N','H','L','X','A','W','M','J','Q','O','F','E','C','K'};
        allRotors = createRotors(r1array,r2array,r3array,r4array,r5array);
        rotors[0] = allRotors[2];
        rotors[1] = allRotors[1];
        rotors[2] = allRotors[0];

        char[] reflectorArray  = {'Y','R','U','H','Q','S','L','D','P','X','N','G','O','K','M','I','E','B','F','Z','C','W','V','J','A','T'};
        reflector = new RotorImpl((reflectorArray), null);
        char[] plugArray  = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        plugboard = new RotorImpl((plugArray), null);
    }

    private Rotor[] createRotors(char[] r1, char[] r2, char[] r3, char[] r4, char[] r5) {
        final Rotor[] rotors = new Rotor[6];
        rotors[0] = new RotorImpl(r1, Position.R);
        rotors[1] = new RotorImpl(r2, Position.F);
        rotors[2] = new RotorImpl(r3, Position.W);
        rotors[3] = new RotorImpl(r4, Position.K);
        rotors[4] = new RotorImpl(r5, Position.A);
        return rotors;
    }

    public String encrypt(final String message) {
        final char[] input = message.toCharArray();
        String toReturn = "";
        for (int index = 0; index< input.length; index++) {

            advanceRotors();
            getRotorPositions();
            Position value = Position.valueOf(String.valueOf(input[index]));
            toReturn = toReturn+value;
            value = plugboard.encryptForward(value);
            toReturn = toReturn + value;
            for (Rotor rotor: rotors) {
                value = rotor.encryptForward(value);
                toReturn = toReturn + value;
            }
            value = reflector.encryptForward(value);
            toReturn = toReturn + value;
            for (int i=2; i>-1;i--) {
                value = rotors[i].encryptReverse(value);
                toReturn = toReturn + value;
            }
            value = plugboard.encryptReverse(value);
            toReturn = toReturn + value;
        }
        System.out.println(toReturn);
        return toReturn;
    }

    private void advanceRotors() {
        for (Rotor rotor: rotors) {
            if (!rotor.rotate()) {
                break;
            }
        }
    }

    public int[] getRotorPositions() {
        final int[] positions = new int[3];
        for (int index = 0; index<3; index++) {
            positions[index] = rotors[index].getRotorPosition();
        }
        return positions;
    }

    public void setRotors(final int[] selection) {
        for (int i = 0; i<selection.length; i++) {
            rotors[i] = allRotors[selection[i]];
        }
    }

    public void setRotorPositions(final int[] positions) {
        for (int i=0; i<positions.length; i++) {
            Position p = Position.values()[positions[i]];
            rotors[i].setRotorPosition(p);
        }
    }

    public void setRingPositions(final int[] rings) {
        for (int i=0; i<rings.length; i++) {
            Position p = Position.values()[rings[i]];
            rotors[i].setRingPosition(p);
        }
    }

}
