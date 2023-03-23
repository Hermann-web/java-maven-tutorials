package com.favtuts.mavenwebapp.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextReconstructor {

    // public static void main(String[] args) {
    //     String[] texts = { "eee", "eee ttt", "rrr eee", "abc uuu", "rrr ppp", "jjj rrr", "eee ccc", "ddd eee" };
    //     System.out.println(reconstructText(texts));
    // }

    public static Map<String, String> reconstructText(List<String> texts) {
        String isMatch;
        double deb = System.currentTimeMillis();
        assert texts.size() > 0;
        List<Object> res = tokenizer(texts);
        List<List<String>> listListToken = (List<List<String>>) res.get(0);
        String sep = (String) res.get(1);
        listListToken = searchClusters(listListToken);
        if (listListToken.size() == 1){
            isMatch= "match";
        }
        else{
            isMatch= "no_match";
        }
        List<String> finalList = new ArrayList<>();
        for (List<String> elt : listListToken) {
            finalList.add(String.join(sep, elt));
        }
        double end = System.currentTimeMillis();
        String laps = Double.toString((end - deb) / 1000.0);
        String finalText = String.join(" ", finalList);
        Map<String, String> result = new HashMap<>();
        
        result.put("match", isMatch);
        result.put("res", finalText);
        //result.put("finalList", finalList);
        result.put("laps", laps);
        System.out.println("res--"+finalText);
        return result;
    }

    public static List<Object> tokenizer(List<String> texts) {
        String sumStr = String.join("", texts);
        List<List<String>> L;
        String sep;

        if (sumStr.contains(" ")) {
            L = new ArrayList<>();
            for (String elt : texts) {
                L.add(Arrays.asList(elt.split(" ")));
            }
            sep = " ";
        } else {
            L = new ArrayList<>();
            for (String elt : texts) {
                L.add(Arrays.asList(elt.split("")));
            }
            sep = "";
        }
        List<Object> result = new ArrayList<>();
        result.add(L);
        result.add(sep);
        return result;
    }

    public static List<List<String>> searchClusters(List<List<String>> listListToken) {
        if (listListToken.size() < 2) {
            return listListToken;
        }
        for (int i = 0; i < listListToken.size(); i++) {
            for (int j = 0; j < listListToken.size(); j++) {
                if (i >= j) {
                    continue;
                }
                List<String> listTk1 = listListToken.get(i);
                List<String> listTk2 = listListToken.get(j);
                if (listTk2.get(0).contains(listTk1.get(listTk1.size() - 1))) {
                    listListToken.set(i, new ArrayList<>(listTk1.subList(0, listTk1.size() - 1)));
                    listListToken.get(i).addAll(listTk2);
                    listListToken.remove(j);
                    return searchClusters(listListToken);
                }else if (listTk1.get(0).contains(listTk2.get(listTk2.size() - 1))) {
                    listListToken.set(i, new ArrayList<>(listTk2.subList(0, listTk2.size() - 1)));
                    listListToken.get(i).addAll(listTk1);
                    listListToken.remove(j);
                    return searchClusters(listListToken);
                }
                else if (isSublist(listTk1, listTk2)) {
                    listListToken.set(i,listTk2);
                    listListToken.remove(j);
                    return searchClusters(listListToken);
                } else if (isSublist(listTk2, listTk1)) {
                    listListToken.remove(j);
                    return searchClusters(listListToken);
                }
                List<String> cc = concatListToken(listTk1, listTk2);
                if (cc != null) {
                    listListToken.set(i, cc);
                    listListToken.remove(j);
                    return searchClusters(listListToken);
                }
            }
        }
        return listListToken;
    }

    public static boolean isSublist(List<String> sublist, List<String> fullList) {
        return String.join("", fullList).contains(String.join("", sublist));
    }

    static List<String> concatListToken(List<String> listTk1, List<String> listTk2) {
        for (int i = 1; i < listTk1.size(); i++) {
            List<String> deb1 = listTk1.subList(0, i);
            List<String> fin1 = listTk1.subList(i, listTk1.size());
            int n = deb1.size();
            if (listTk2.size() - n >= 0) {
                List<String> fin2 = listTk2.subList(listTk2.size() - n, listTk2.size());
                if (String.join("", deb1).equals(String.join("", fin2)) || fin2.get(fin2.size()-1).contains(deb1.get(0))) {
                    List<String> result = new ArrayList<>(listTk2);
                    result.addAll(fin1);
                    return result;
                }
            }
            n = fin1.size();
            if (n <= listTk2.size()) {
                List<String> deb2 = listTk2.subList(0, n);
                List<String> fin2 = listTk2.subList(n, listTk2.size());
                if (String.join("", deb2).equals(String.join("", fin1)) || fin1.get(fin1.size()-1).contains(deb2.get(0))) {
                    List<String> result = new ArrayList<>(listTk1);
                    result.addAll(fin2);
                    return result;
                }
            }
        }
        return null;
    }

}
