/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Write your own random number generator
 */
public class RandomNumber {
    RandomObject[] array = new RandomObject[1000];
    String prefix = "RandomNumber$RandomObject@";

    /*
     * Creates a set array of objects,
     * for each array entry, a new object is created
     */
    private void createArrayofObjects() {
        for (int i = 0; i < array.length; i++) {
            array[i] = new RandomObject();
        }
    }

    /*
     * Takes the toString value from created object
     * and multiplies it with the time from created object
     * returns a "random number" for each entry of the array
     */
    private void getNumberFromArray() {
        String object = "";
        int randomNumber = 0;
        for (int i = 0; i < array.length; i++) {
            object = array[i].toString();
            randomNumber = convertStringToNumber(object) * array[i].getTime();
            System.out.println(randomNumber);
        }
    }

    /*
     * Takes the toString value of object, only takes the last 3 values of it,
     * it's originally in hexadecimal so its converted to integer
     */
    private int convertStringToNumber(String input) {
        String number = input.replace(prefix, "");
        number = number.substring(number.length()-3);
        return Integer.parseInt(number, 16);
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
        Long time;

        public RandomObject() {
            setTime();
        }

        private void setTime() {
            time = System.nanoTime();
        }

        private int getTime() {
            return time.intValue();
        }
    }
}
