package web.LoginSecurity.global.utils;

public class RandomNumberGenerator {
    private final static int MAX_NUMBER = 99999;
    private final static int MIN_NUMBER = 10000;
    public int createRandomNumber(){
        return (int)(Math.random() * (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER);
    }
}
