/*
 * Sarah Ferguson
 * CS4050 - Assignment 4
 * Write your own random number generator
 */
public class RandomNumber {
    EmptyObject[] array = new EmptyObject[1000];
    String prefix = "RandomNumber$EmptyObject@";

    String object;
    int randomNumber;

    private void createArrayofObjects() {
        for (int i = 0; i < array.length; i++) {
            array[i] = new EmptyObject();
        }
    }

    private void getNumberFromArray() {
        for (int i = 0; i < array.length; i++) {
            object = array[i].toString();
            randomNumber += convertStringToNumber(object);
        }
    }

    private int convertStringToNumber(String input) {
        String number = input.replace(prefix, "");
        number = number.substring(number.length()-1);
        return Integer.parseInt(number, 16);
    }

    public static void main (String[] args) {
        RandomNumber runner = new RandomNumber();
        runner.createArrayofObjects();
        runner.getNumberFromArray();
        System.out.println(runner.randomNumber);
    }

    /*
     * Class to represent a new object,
     * each object will contain the time it was created
     */
    class EmptyObject {

        public EmptyObject() {
            // do nothing
        }

    }
}
