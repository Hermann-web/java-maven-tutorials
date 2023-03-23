package com.favtuts.mavenwebapp.web;
import java.io.Console;
import java.util.*;

public class TextReconstructorCopy {
    private static boolean isSublist(String[] sublist1, String[] sublist2) {
        String str1 = String.join(" ", sublist1);
        String str2 = String.join(" ", sublist2);
        return str1.contains(str2);
    }

    public static Map<String, String> reconstructText(String subtext1, String subtext2) {
        return reconstructText(subtext1, subtext2, "", 0, false, 0, false);
    }
    
    private static Map<String, String> reconstructText(String subtext1, String subtext2, String reconstructedText, int id, boolean debug, double deb, boolean keep_order) {
        System.out.println(Integer.toString(id)+"--"+reconstructedText+"--"+subtext1+"--"+subtext2);
        if (deb == 0) {
            System.out.println("deb--"+subtext1+"--"+subtext2);
            deb = System.currentTimeMillis();
        }
        id = 0;
        if ((subtext1.length() > subtext2.length())&&(keep_order==false)) {
            String temp = subtext2;
            subtext2 = subtext1;
            subtext1 = temp;
        }
        if (subtext2.contains(subtext1) || subtext1.contains(subtext2)) {
            double end = System.currentTimeMillis();
            String longest = subtext2.contains(subtext1) ? subtext2 : subtext1;
            String finalText = reconstructedText + longest;
            String laps = Double.toString((end - deb) / 1000.0);
            Map<String, String> result = new HashMap<>();
            result.put("res", finalText);
            result.put("laps", laps);
            System.out.println(id);
            System.out.println("res--"+finalText);
            return result;
        }
        id++;
        boolean match = false;
        int k_ = 0, l_ = 0;
        for (int k = 2; k < Math.min(subtext1.length(), 5); k++) {
            for (int l = Math.min(subtext2.length(), k+2); l > Math.max(k-1, -1); l--) {
                if (subtext2.substring(0, l).contains(subtext1.substring(0, k)) && subtext1.substring(0, k).replace(" ", "").length() > 0) {
                    match = true;
                    k_ = k;
                    l_ = l;
                    
                }
            }
        }
        if(match==true){
            String newReconstructedText = reconstructedText + subtext2.substring(0, l_);
            return reconstructText(subtext1.substring(k_), subtext2.substring(l_), newReconstructedText, id, debug, deb, true);
        }

        // id++;
        // match = false;
        // String[] sublist1 = subtext1.split(" ");
        // String[] sublist2 = subtext2.split(" ");
        // int n1 = sublist1.length;
        // int n2 = sublist2.length;
        // for (int k = 1; k < Math.min(n1, 5); k++) {
        //     for (int l = Math.min(n2, k+5); l >= Math.max(-1, k-1); l--) {
        //         if (isSublist(Arrays.copyOfRange(sublist1, 0, k), Arrays.copyOfRange(sublist2, 0, l))) {
        //             match = true;
        //             k_ = k;
        //             l_ = l;
        //         }
        //     }
        // }
        // if (match) {
        //     String newReconstructedText = reconstructedText + String.join(" ", Arrays.copyOfRange(sublist2, 0, l_));
        //     return reconstructText(String.join(" ", Arrays.copyOfRange(sublist1, k_, n1)), String.join(" ", Arrays.copyOfRange(sublist2, l_, n2)), newReconstructedText, id, debug, deb, true);
        // }



        
        id++;
        if (subtext1.charAt(0) == subtext2.charAt(0)) {
            String newReconstructedText = reconstructedText + subtext1.charAt(0);
            return reconstructText(subtext1.substring(1), subtext2.substring(1), newReconstructedText, id, debug, deb, false);
        }
        id++;
        String newReconstructedText = reconstructedText + subtext1.substring(0, 1);
        return reconstructText(subtext1.substring(1), subtext2.substring(0), newReconstructedText, id, debug, deb, true);
    }
}
