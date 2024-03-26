/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad3;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {

    try {
      Map<String, List<String>> anagrams = Anagrams.mapAnagramsFromUrl("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
      int bSize = Anagrams.theBiggestListSize(anagrams.values());
      anagrams.values().stream().filter(l -> l.size() == bSize).sorted(Comparator.comparing(l -> l.get(0))).collect(Collectors.toList()).forEach(Anagrams::printAnagrams);
    }catch (IOException ex){
      ex.printStackTrace();
    }

  }
}






