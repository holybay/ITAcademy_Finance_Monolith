package by.it_academy.jd2.finance.platform.util;

public class ApplicationUtil {

    public static int generateCode(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
