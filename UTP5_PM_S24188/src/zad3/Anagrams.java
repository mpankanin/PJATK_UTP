package zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    public static Map<String, List<String>> mapAnagramsFromUrl(String url) throws IOException {
        return new BufferedReader(new InputStreamReader(new URL(url).openStream())).lines().toList().stream().collect(Collectors.groupingBy(Anagrams::sortString));
    }

    public static String sortString(String str){
        char[] tmp = str.toCharArray();
        Arrays.sort(tmp);
        return String.valueOf(tmp);
    }

    public static int theBiggestListSize(Collection<List<String>> lists){
        int biggestSize = 0;
        for(List <String> list :lists)
            if(list.size() > biggestSize)
                biggestSize = list.size();

        return biggestSize;
    }

    public static void printAnagrams(List<String> list){
        System.out.println(String.join(" ", list));
    }







}
