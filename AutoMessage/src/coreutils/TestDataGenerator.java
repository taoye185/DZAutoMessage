package coreutils;

import java.sql.Timestamp;

public class TestDataGenerator {

    /**
     * Returns a random number between min (inclusive) and max (exclusive)
     * 
     * @param minOffsetValue : the minimum value this method should return
     * @param maxOffsetValue : the maximum value this method should return
     */
    public double getRandomValueBetweenMaxAndMin(int minOffsetValue, int maxOffsetValue) {
        return Math.random() * (maxOffsetValue - minOffsetValue) + minOffsetValue;
    }

    /**
     * Returns a random number 0 to maxOffsetValue (inclusive)
     * 
     * @param text : if you pass -5 you will get back a value between 0-5
     */
    public double getRandomNumber(int maxOffsetValue) {
        return (int) (Math.random() * maxOffsetValue + 1);
    }

    /**
     * Pass a string and the function will append and return it with a random
     * integer
     * 
     * @param text : if you pass -test you will get back something like "test4981"
     */
    public String getCharacterNumericString(String text) { /* TO-DO */
        String finalText = text +  (int)(getRandomValueBetweenMaxAndMin(1001, 9999));
        return finalText;
    }

    /**
     * This method return a system time in milli seconds as a string. This method is
     * useful in generating unique random number
     */
    public String timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }

    /**
     * This method return a system time in milli seconds as a string. This method is
     * useful in generating unique random number as a string.
     * 
     * @param length : length of the random number to return
     */
    public String timestamp(int length) {
        String value = timestamp();
        if (value.length() > length) {
            value = value.substring((value.length() - length));
        }
        return value;
    }

}