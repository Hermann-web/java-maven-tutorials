package com.favtuts.mavenwebapp.web;

public class TextReconstructor {
    public static String reconstructText(String subtext1, String subtext2) {
        String reconstructedText = "";
        int i = 0;
        int j = 0;
        boolean match = false;
        while (i < subtext1.length() && j < subtext2.length()) {
            if (subtext1.charAt(i) == subtext2.charAt(j)) {
                reconstructedText += subtext1.charAt(i);
                i++;
                j++;
                match = true;
            } else if (subtext1.length() - i >= subtext2.length() - j) {
                reconstructedText += subtext1.charAt(i);
                i++;
            } else {
                reconstructedText += subtext2.charAt(j);
                j++;
            }
        }
        if (!match) return subtext1 + subtext2;
        if (i < subtext1.length()) {
            reconstructedText += subtext1.substring(i);
        }
        if (j < subtext2.length()) {
            reconstructedText += subtext2.substring(j);
        }
        return reconstructedText;
    }
}
