package eu.veldsoft.fish.rings;

import java.util.Arrays;
import java.util.Random;

/**
 * Class representing the rings.
 */
class Rings implements Cloneable, Comparable<Rings> {
    /**
     * Pseudorandom number generator instance.
     */
    private static final Random PRNG = new Random();

    /**
     * Rings data structure.
     */
    private int rings[][] = new int[3][12];

    /**
     * Coordinates of the rings' pieces.
     */
    private int coordiantes[][][] = new int[3][12][2];

    /**
     * Width of the filed.
     */
    private int width;

    /**
     * Height of the field.
     */
    private int height;

    /**
     * Diameter of a single piece.
     */
    private int pieceDiameter;

    /**
     * Diameter of a single ring.
     */
    private int ringDiameter;

    /**
     * Moves counter.
     */
    private int moves;

    /**
     * Constructor without parameters.
     */
    Rings() {
        init(0, 0);
    }

    /**
     * Constructor with parameters for dimensions.
     *
     * @param width  Width of the field.
     * @param height Height of the field.
     */
    Rings(int width, int height) {
        init(width, height);
    }

    /**
     * Initialization procedure.
     *
     * @param width  Width of the field.
     * @param height Height of the field.
     */
    void init(int width, int height) {
        this.width = width;
        this.height = height;

        pieceDiameter = (int) (Math.min(width, height) * 0.1);
        ringDiameter = (int) (2.5 * pieceDiameter);

        for (double i = 0, alpha = +3.5; i < 12; i++, alpha++) {
            coordiantes[0][(int) i][0] = (int) (Math.min(width, height) / 2.0
                    + ringDiameter * Math.cos(alpha * 2 * Math.PI / 12));
            coordiantes[0][(int) i][1] = (int) (Math.min(width, height) / 2.0 - Math.sqrt(2.0) * ringDiameter / 2.0
                    + ringDiameter * Math.sin(alpha * 2 * Math.PI / 12));
        }

        for (double i = 0, alpha = -3.5; i < 12; i++, alpha--) {
            coordiantes[1][(int) i][0] = (int) (Math.min(width, height) / 2.0 + Math.sqrt(2.0) * ringDiameter / 2.0
                    + ringDiameter * Math.cos(alpha * 2 * Math.PI / 12));
            coordiantes[1][(int) i][1] = (int) (Math.min(width, height) / 2.0
                    + ringDiameter * Math.sin(alpha * 2 * Math.PI / 12));
        }

        for (double i = 0, alpha = -2.5; i < 12; i++, alpha++) {
            coordiantes[2][(int) i][0] = (int) (Math.min(width, height) / 2.0 - Math.sqrt(2.0) * ringDiameter / 2.0
                    + ringDiameter * Math.cos(alpha * 2 * Math.PI / 12));
            coordiantes[2][(int) i][1] = (int) (Math.min(width, height) / 2.0
                    + ringDiameter * Math.sin(alpha * 2 * Math.PI / 12));
        }

        rings[0][0] = 4;
        for (int i = 1; i < 11; i++)
            rings[0][i] = 1;
        rings[0][11] = 4;

        for (int i = 0; i < 5; i++) {
            rings[1][i] = 4;
            rings[2][i] = 4;
        }
        for (int i = 5; i < 11; i++) {
            rings[1][i] = 2;
            rings[2][i] = 3;
        }
        rings[1][11] = 1;
        rings[2][11] = 1;

        moves = 0;
    }

    /**
     * Clockwise rotation of the ring A.
     */
    void cwa() {
        int temp = rings[0][0];
        for (int i = 0; i < 11; i++)
            rings[0][i] = rings[0][i + 1];
        rings[0][11] = temp;

        rings[1][3] = rings[0][0];
        rings[1][11] = rings[0][8];
        rings[2][11] = rings[0][3];
        rings[2][3] = rings[0][11];
    }

    /**
     * Counter-clockwise rotation of the ring A.
     */
    void ccwa() {
        int temp = rings[0][11];
        for (int i = 11; i > 0; i--)
            rings[0][i] = rings[0][i - 1];
        rings[0][0] = temp;

        rings[1][3] = rings[0][0];
        rings[1][11] = rings[0][8];
        rings[2][11] = rings[0][3];
        rings[2][3] = rings[0][11];
    }

    /**
     * Clockwise rotation of the ring B.
     */
    void cwb() {
        int temp = rings[1][0];
        for (int i = 0; i < 11; i++)
            rings[1][i] = rings[1][i + 1];
        rings[1][11] = temp;

        rings[0][0] = rings[1][3];
        rings[0][8] = rings[1][11];
        rings[2][1] = rings[1][1];
        rings[2][4] = rings[1][4];
    }

    /**
     * Counter-clockwise rotation of the ring B.
     */
    void ccwb() {
        int temp = rings[1][11];
        for (int i = 11; i > 0; i--)
            rings[1][i] = rings[1][i - 1];
        rings[1][0] = temp;

        rings[0][0] = rings[1][3];
        rings[0][8] = rings[1][11];
        rings[2][1] = rings[1][1];
        rings[2][4] = rings[1][4];
    }

    /**
     * Clockwise rotation of the ring C.
     */
    void cwc() {
        int temp = rings[2][0];
        for (int i = 0; i < 11; i++)
            rings[2][i] = rings[2][i + 1];
        rings[2][11] = temp;

        rings[0][3] = rings[2][11];
        rings[0][11] = rings[2][3];
        rings[1][1] = rings[2][1];
        rings[1][4] = rings[2][4];
    }

    /**
     * Counter-clockwise rotation of the ring C.
     */
    void ccwc() {
        int temp = rings[2][11];
        for (int i = 11; i > 0; i--)
            rings[2][i] = rings[2][i - 1];
        rings[2][0] = temp;

        rings[0][3] = rings[2][11];
        rings[0][11] = rings[2][3];
        rings[1][1] = rings[2][1];
        rings[1][4] = rings[2][4];
    }

    /**
     * Shuffling of the rings.
     */
    void shuffle() {
        String pieces = Arrays.deepToString(rings);
        for (int i = 0; i < pieces.length(); i++)
            switch (PRNG.nextInt(6)) {
                case 0:
                    cwa();
                    break;
                case 1:
                    ccwa();
                    break;
                case 2:
                    cwb();
                    break;
                case 3:
                    ccwb();
                    break;
                case 4:
                    cwc();
                    break;
                case 5:
                    ccwc();
                    break;
            }

        moves = 0;
    }

    /**
     * Reset the puzzle to the initial state.
     */
    void reset() {
        init(width, height);
    }

    /**
     * Execute specified commadnd.
     *
     * @param command Command it set of instructions to be executed.
     * @return True if the command succeeded, false otherwise.
     */
    boolean evaluate(String command) {
        for (int i = 0, repeats = 0; i < command.length() - 1; i++) {
            if (command.charAt(i) == '+') {
                if ('0' <= command.charAt(++i) && command.charAt(i) <= '9') {
                    repeats = command.charAt(i++) - '0';
                } else {
                    repeats = 1;
                }

                if (command.charAt(i) == 'A') {
                    for (int j = 0; j < repeats; j++) {
                        cwa();
                    }
                } else if (command.charAt(i) == 'B') {
                    for (int j = 0; j < repeats; j++) {
                        cwb();
                    }
                } else if (command.charAt(i) == 'C') {
                    for (int j = 0; j < repeats; j++) {
                        cwc();
                    }
                } else {
                    return false;
                }
            } else if (command.charAt(i) == '-') {
                if ('0' <= command.charAt(++i) && command.charAt(i) <= '9') {
                    repeats = command.charAt(i++) - '0';
                } else {
                    repeats = 1;
                }

                if (command.charAt(i) == 'A') {
                    for (int j = 0; j < repeats; j++) {
                        ccwa();
                    }
                } else if (command.charAt(i) == 'B') {
                    for (int j = 0; j < repeats; j++) {
                        ccwb();
                    }
                } else if (command.charAt(i) == 'C') {
                    for (int j = 0; j < repeats; j++) {
                        ccwc();
                    }
                } else
                    return false;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Trigger rotation of some ring.
     *
     * @param x X coordinate of the user's click.
     * @param y Y coordinate of the user's click.
     * @return True if succeed, false otherwise.
     */
    boolean action(int x, int y) {
        boolean done = false;

        for (int j = 0; j < 18; j++)
            switch (j) {
                case 1:
                case 2:
                case 4:
                case 5:
                    if ((Math.pow(x - coordiantes[0][j][0], 2.0) + Math.pow(y - coordiantes[0][j][1], 2.0)) < Math
                            .pow(pieceDiameter / 2.0, 2.0)) {
                        cwa();
                        done = true;
                    }
                    break;
                case 6:
                case 7:
                case 9:
                case 10:
                    if ((Math.pow(x - coordiantes[0][j][0], 2.0) + Math.pow(y - coordiantes[0][j][1], 2.0)) < Math
                            .pow(pieceDiameter / 2.0, 2.0)) {
                        ccwa();
                        done = true;
                    }
                    break;
            }

        for (int j = 0; j < 18; j++)
            switch (j) {
                case 0:
                case 2:
                case 5:
                    if ((Math.pow(x - coordiantes[1][j][0], 2.0) + Math.pow(y - coordiantes[1][j][1], 2.0)) < Math
                            .pow(pieceDiameter / 2.0, 2.0)) {
                        ccwb();
                        done = true;
                    }
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    if ((Math.pow(x - coordiantes[1][j][0], 2.0) + Math.pow(y - coordiantes[1][j][1], 2.0)) < Math
                            .pow(pieceDiameter / 2.0, 2.0)) {
                        cwb();
                        done = true;
                    }
                    break;
            }

        for (int j = 0; j < 18; j++)
            switch (j) {
                case 0:
                case 2:
                case 5:
                    if ((Math.pow(x - coordiantes[2][j][0], 2.0) + Math.pow(y - coordiantes[2][j][1], 2.0)) < Math
                            .pow(pieceDiameter / 2.0, 2.0)) {
                        ccwc();
                        done = true;
                    }
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    if ((Math.pow(x - coordiantes[2][j][0], 2.0) + Math.pow(y - coordiantes[2][j][1], 2.0)) < Math
                            .pow(pieceDiameter / 2.0, 2.0)) {
                        cwc();
                        done = true;
                    }
                    break;
            }

        moves++;

        return (done);
    }

    /**
     * Check the solved state of the puzzle.
     *
     * @return True if the puzzle is solved, false otherwise.
     */
    boolean isSolved() {
        for (int i = 0; i < 11; i++)
            if (rings[0][i] != rings[0][i + 1] && i != 0 && i != 10)
                return false;
            else if (rings[1][i] != rings[1][i + 1] && i != 4 && i != 10)
                return false;
            else if (rings[2][i] != rings[2][i + 1] && i != 4 && i != 10)
                return false;

        return true;
    }

    /**
     * Provides linear representation of the puzzle's state.
     *
     * @return Linear representation of the puzzle's state as an array of integers.
     */
    int[] getState() {
        int[] result = new int[rings[0].length + rings[1].length + rings[2].length];

        for (int r = 0, j = 0; r < rings.length; r++) {
            for (int i = 0; i < rings[r].length; i++, j++) {
                result[j] = rings[r][i];
            }
        }

        return result;
    }

    /**
     * Distence between two instances of the puzzle.
     *
     * @param that Another instance of the puzzle.
     * @return Mathematical distance between two instances of the puzzle.
     */
    double distance(Rings that) {
        double distance = 0;

        for (int r = 0; r < rings.length; r++) {
            for (int i = 0; i < rings[r].length; i++) {
                distance += (this.rings[r][i] - that.rings[r][i]) * (this.rings[r][i] - that.rings[r][i]);
            }
        }

        distance = Math.sqrt(distance);

        return distance;
    }

    /**
     * Compare the objects.
     *
     * @param that The object to be compared.
     * @return Integer number for the difference between the objects.
     */
    @Override
    public int compareTo(Rings that) {
        int difference = 0;

        for (int r = 0; r < rings.length; r++) {
            for (int i = 0; i < rings[r].length; i++) {
                difference += this.rings[r][i] - that.rings[r][i];
            }
        }

        return difference;
    }
}
