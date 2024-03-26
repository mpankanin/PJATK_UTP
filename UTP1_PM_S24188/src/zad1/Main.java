/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad1;



import java.util.*;


public class Main {
  public Main() {
    List<Integer> src1 = new ArrayList<>(Arrays.asList(1, 7, 9, 11, 12));/*<-- tu dopisać inicjację elementów listy */
    System.out.println(test1(src1));

    List<String> src2 = new ArrayList<>(Arrays.asList("a", "zzzz", "vvvvvvv"));/*<-- tu dopisać inicjację elementów listy */
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = new Selector<>() {
      @Override
      public boolean select(Integer i) {
        return i < 10;
      }
    }; /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    Mapper<Integer, Integer> map = new Mapper<>() {
      @Override
      public Integer map(Integer i) {
        return i+10;
      }
    };   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    return ListCreator.  /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */  collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector<String> sel = new Selector<>() {
      @Override
      public boolean select(String s) {
        return s.length() > 3;
      }
    }; /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    Mapper<String, Integer> map = new Mapper<>() {
      @Override
      public Integer map(String s) {
        return s.length() + 10;
      }
    };   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    return ListCreator.  /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */  collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
