package zad3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class XList<T> extends ArrayList<T> {

    public XList(Collection<? extends T> coll) {
        this.addAll(coll);
    }

    @SafeVarargs
    public XList(T... ts) {
        this.addAll(Arrays.asList(ts));
    }

    @SafeVarargs
    public static <T> XList<T> of(T... args) {
        return new XList<T>(args);
    }

    public static <T> XList<T> of(Collection<? extends T> coll) {
        XList<T> retList = new XList<T>();
        retList.addAll(coll);
        return retList;
    }

    public static XList<String> charsOf(String str) {
        return new XList<>(str.split(""));
    }

    public static XList<String> tokensOf(String str, String sep) {
        return new XList<>(str.split(sep));
    }

    public static XList<String> tokensOf(String str) {
        return new XList<>(str.split(" "));
    }

    public XList<T> union(Collection<? extends T> coll) {

        XList<T> retList = new XList<>();

        retList.addAll(this);
        retList.addAll(coll);

        return retList;
    }

    @SafeVarargs
    public final XList<T> union(T... args) {

        XList<T> retList = new XList<>();

        retList.addAll(this);
        retList.addAll(Arrays.asList(args));

        return retList;
    }

    public XList<T> diff(Collection<? extends T> coll) {
        return new XList<>(this.stream().filter(v -> !coll.contains(v)).collect(Collectors.toList()));
    }

    public XList<T> unique() {
        return new XList<>(new HashSet<>(this));
    }

    public <R> XList<R> collect(Function<T, R> function){
        return new XList<>(this.stream().map(function).collect(Collectors.toList()));
    }

    public String join (String separator){
        return this.stream().map(Object::toString).collect(Collectors.joining(separator));
    }

    public String join (){
        return this.stream().map(Object::toString).collect(Collectors.joining());
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer){
        for(int i = 0; i < size(); i++)
            consumer.accept(get(i), i);
    }

    public XList<XList<String>> combine(){

        XList<XList<String>> retXList = new XList<>();

        List<Object> tmp = new ArrayList<>(this);

        for(Object o : tmp) {
            retXList.add(XList.of(o.toString()));
        }

        return retXList;
    }

}

