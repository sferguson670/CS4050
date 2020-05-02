/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Write your own random number generator
 */
public class RandomNumber {
    RandomObject[] array = new RandomObject[10];

    private void createArrayofObjects() {
        for (int i = 0; i < array.length; i++) {
            array[i] = new RandomObject();
        }
    }

    private void getNumberFromArray() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].toString());
        }
    }

    public static void main (String[] args) {
        RandomNumber runner = new RandomNumber();
        runner.createArrayofObjects();
        runner.getNumberFromArray();
    }

    /*
     * Class to represent a new object,
     * each object will contain the time it was created
     */
    class RandomObject {
        private Long time;

        public RandomObject() {
            setTime();
        }

        private void setTime() {
            time = System.nanoTime();
        }

        private Long getTime() {
            return time;
        }
    }
}
