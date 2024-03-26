package zad3;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {


    private final Map<String, List<String>> langsMap = new LinkedHashMap<>();
    private final Map<String, List<String>> progsMap = new LinkedHashMap<>();



    public ProgLang(String path){
        try {
            Files.readAllLines(Paths.get(path)).stream()
                    .map(s -> Arrays.asList(s.split("\t")))
                    .collect(Collectors.toList())
                    //.forEach(l -> langsMap.put(l.get(0), l.subList(1, l.size())));
                    .forEach(l -> langsMap.put(l.get(0), l.subList(1, l.size())));

            langsMap.forEach((key, value) -> langsMap.replace(key, value.stream().distinct().collect(Collectors.toList())));


            Set<String> set = new LinkedHashSet<>();
            langsMap.values().forEach(set::addAll);
            set.forEach(s -> {
                List<String> knownLangs = new ArrayList<>();
                for(String lang : langsMap.keySet()){
                    List<String> list = langsMap.get(lang);
                    if(list.contains(s))
                        knownLangs.add(lang);
                }
                progsMap.put(s, knownLangs);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static public <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comp){
        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
        entries.sort(comp);
        Map<K, V> resMap = new LinkedHashMap<>();
        entries.forEach(e -> resMap.put(e.getKey(), e.getValue()));
        return resMap;
    }

    static public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> pred){
        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
        Map<K, V> resMap = new LinkedHashMap<>();
        entries.stream().filter(pred).forEach(e -> resMap.put(e.getKey(), e.getValue()));
        return resMap;
    }


    public Map<String, List<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String, List<String>> getProgsMap() {
        return progsMap;
    }

    public Map<String, List<String>> getProgsMapSortedByNumOfLangs(){
        Map<String, List<String>> resMap;
        resMap = sorted(progsMap, Map.Entry.comparingByKey());
        resMap = sorted(resMap, (e1, e2) -> e2.getValue().size() - e1.getValue().size());
        return resMap;
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs(){
        Map<String, List<String>> resMap;
        resMap = sorted(langsMap, Map.Entry.comparingByKey());
        resMap = sorted(resMap, (e1, e2) -> e2.getValue().size() - e1.getValue().size());
        return resMap;
    }

    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n){
        Map<String, List<String>> resMap;
        resMap = filtered(progsMap, e -> e.getValue().size() > n);
        return resMap;
    }

}
