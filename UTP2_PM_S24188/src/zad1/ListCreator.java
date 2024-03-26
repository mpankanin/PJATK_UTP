package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator <T>{


    public List<T> list;


    static <T> ListCreator<T> collectFrom(List<T> src){

        ListCreator<T> tmp = new ListCreator<>();
        tmp.list = src;

        return tmp;
    }


    ListCreator<T> when(Predicate<T> filter){

        List<T> retList = new ArrayList<>();

        for(T t : list) {
            if (filter.test(t)) {
                retList.add(t);
            }
        }

        list = retList;

        return this;
    }


    <S> List<S> mapEvery(Function<T, S> func){

        List<S> retList = new ArrayList<>();

        for(T t : list) {

            retList.add(func.apply(t));

        }

        return retList;
    }

}
