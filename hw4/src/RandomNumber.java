/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Write your own random number generator,
 * positive ints up to 1000 only
 */
public class RandomNumber {
    RandomObject[] array = new RandomObject[1000000];
    String prefix = "RandomNumber$RandomObject@";

    /*
     * Creates a set array of objects,
     * for each array entry, a new object is created
     */
    private void createArrayOfObjects() {
        for (int i = 0; i < array.length; i++) {
            array[i] = new RandomObject();
        }
    }

    /*
     * Takes the toString value from created object, uses for objectNum
     * Takes the getTime value from created object, uses for timeNum
     * returns a "random number" for each entry of the array
     */
    private void getNumbersFromArray() {
        String object;
        int objectNum;
        int timeNum;

        for (RandomObject randomObject : array) {
            object = randomObject.toString();
            objectNum = convertStringToNumber(object);
            timeNum = randomObject.getTime();

            System.out.println(getRandomNumber(objectNum, timeNum));
        }
    }

    /*
     * Takes the toString value of object, only takes the last value of it,
     * it's originally in hexadecimal so its converted to integer
     */
    private int convertStringToNumber(String input) {
        String number = input.replace(prefix, "");
        number = number.substring(number.length() - 1);
        return Integer.parseInt(number, 16);
    }

    /*
     * Takes the numbers that was derived from object and current time,
     * multiplies them together,
     * if value is > 1000, returns the leftover from 1000
     */
    private int getRandomNumber(int objNum, int timeNum) {
        int num = objNum * timeNum;
        if (num > 1000) {
            num = num - 1000;
        }
        return num;
    }

    public static void main (String[] args) {
        RandomNumber runner = new RandomNumber();
        runner.createArrayOfObjects();
        runner.getNumbersFromArray();
    }

    /*
     * Class to represent a new object,
     * each object will contain the time it was created
     */
    static class RandomObject {
        Long time;

        public RandomObject() {
            setTime();
        }

        private void setTime() {
            time = System.nanoTime();
        }

        private int getTime() {
            int value = time.intValue();
            return Math.abs(value % 100);
        }
    }
}
