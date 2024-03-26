/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad1;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*<--
 *  niezbędne importy
 */

public class Main {
  public static void main(String[] args) {

    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    Function<String, List<String>> flines = fname-> {

      try{
        return Files.lines(Paths.get(fname)).collect(Collectors.toList());
      }catch(IOException ex){
        throw new UncheckedIOException(ex);
      }

    };

    Function<List<String>, String> join = fname -> {

      StringBuilder sb = new StringBuilder();

      fname.forEach(sb::append);

      return String.valueOf(sb);
    };

    Function<String, List<Integer>> collectInts = fname -> Arrays.stream(fname.split("\\D+")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toList());

    Function<List<Integer>, Integer> sum = fname -> fname.stream().reduce(0, Integer::sum);

    // --------------------------------------------------------------------

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
