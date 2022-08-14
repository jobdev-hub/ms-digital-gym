package br.com.jobdev.msdigitalgym.util;

public class StringUtils {

    public static String keepNumbersOnly(String value) {
        return value.replaceAll("[^0-9]", "");
    }

}
