/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad1;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;



public class Anagrams {

    private final Path path;


    public Anagrams(String path){
        this.path = Paths.get(path);
    }


    public List<List<String>> getSortedByAnQty(){

        List<List<String>> retList = new ArrayList<>();

        List<String> sList = collectFromFile();
        List<String> tmp;



        if(!sList.isEmpty()){
            tmp = new ArrayList<>();
            tmp.add(sList.get(0));
            retList.add(tmp);
            sList.remove(0);
        }

        if(!sList.isEmpty()) {
            for (String s : sList) {
                boolean added = false;
                char[] charArr = s.toCharArray();
                Arrays.sort(charArr);
                for (List<String> strings : retList) {
                    char[] charArr2 = strings.get(0).toCharArray();
                    Arrays.sort(charArr2);
                    if (Arrays.equals(charArr, charArr2)) {
                        strings.add(s);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    tmp = new ArrayList<>();
                    tmp.add(s);
                    retList.add(tmp);
                }
            }
        }

        //retList.sort((a, b) -> b.size() - a.size());
        retList.sort(Comparator.comparing(l -> l.get(0)));
        retList.sort((a, b) -> b.size() - a.size());

        return retList;
    }

    public String getAnagramsFor(String word){

        List<String> anagrams = new ArrayList<>();
        List<String> sList = collectFromFile();

        char[] charArr = word.toCharArray();
        Arrays.sort(charArr);
        boolean wordInfile = false;

        for(String str : sList){
            if(str.equals(word)){
                wordInfile = true;
                continue;
            }


            char[] charArr2 = str.toCharArray();
            Arrays.sort(charArr2);

            if(Arrays.equals(charArr, charArr2))
                anagrams.add(str);
        }


        return wordInfile ? word + ": " + anagrams : word + ": [null]";
    }

    public List<String> collectFromFile(){

        List<String> sList = new ArrayList<>();

        try {
            Files.readAllLines(path).forEach(s -> sList.addAll(Arrays.asList(s.split(" "))));;
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return sList;
    }





}  
