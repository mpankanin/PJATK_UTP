package zad1;

import java.util.function.Function;

class InputConverter<T> {

    private final T t;


    public InputConverter(T t){
        this.t = t;
    }

    public <S> S convertBy(Function... funcs){

        Object res = t;

        for(Function f : funcs)
            res = f.apply(res);

        return (S) res;
    }


}
