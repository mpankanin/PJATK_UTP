package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private final T t;

    public Maybe(){
        this.t = null;
    };

    public Maybe(T t){
        this.t = t;
    };

    public static <T> Maybe<T> of (T t){
        return t == null ? new Maybe<>(null) : new Maybe<>(t);
    }

    public void ifPresent(Consumer<? super T> cons){

        if(isPresent())
            cons.accept(this.t);

    }

    public <S> Maybe<S> map(Function<? super T, ? extends S> func){

        if(isPresent())
            return new Maybe<>(func.apply(this.t));
        else
            return new Maybe<>();

    }

    public T get(){
        if(!isPresent())
            throw new NoSuchElementException("maybe is empty");
        return this.t;
    }

    public boolean isPresent(){
        return this.t != null;
    }

    public T orElse(T defVal){
        return this.t != null ? t : defVal;
    }

    public Maybe<T> filter(Predicate<? super T> predicate){
        if(predicate.test(t))
            return this;
        else
            return new Maybe<>();
    }

    @Override
    public String toString() {
        return this.t != null ? "Maybe has value " + this.t : "Maybe is empty";
    }
}
