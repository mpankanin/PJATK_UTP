/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad1;


public interface Mapper<T, S> { // Uwaga: interfejs musi być sparametrtyzowany
    S map(T t);
}  
