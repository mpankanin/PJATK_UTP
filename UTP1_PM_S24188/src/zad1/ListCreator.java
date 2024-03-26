/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    public List<T> list;

    public static <T, S> ListCreator<T> collectFrom(List<T> src) {

        ListCreator<T> tmp = new ListCreator<>();
        tmp.list = src;

        return tmp;
    }

    public ListCreator <T> when(Selector<T> s){

        for(int i = 0; i < list.size(); i++){
            T t = list.get(i);
            if(!s.select(t)){
                list.remove(i);
                i--;
            }
        }

        return this;
    }

    public <S> List<S> mapEvery(Mapper<T, S> m){

        List<S> retList = new ArrayList<>();

        for (T t : list) {

            S s = m.map(t);

            retList.add(s);
        }

        return retList;
    }

}  
