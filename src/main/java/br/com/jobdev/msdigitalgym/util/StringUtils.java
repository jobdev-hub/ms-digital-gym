package br.com.jobdev.msdigitalgym.util;

public class StringUtils {

    public static String formatCpf(String cpf) {
        String cpfFormateded = cpf.replaceAll("[^0-9]", "");
        if (cpfFormateded.length() == 11) {
            cpfFormateded = cpfFormateded.substring(0, 3) + "." + cpfFormateded.substring(3, 6) + "." + cpfFormateded.substring(6, 9) + "-" + cpfFormateded.substring(9, 11);
        }
        return cpfFormateded;
    }

}
