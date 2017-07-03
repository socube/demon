package menagerie.collections;

import java.util.*;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public class CompositeIterator<T> implements Iterator<T> {


    private final Queue<Iterator<T>> iterators;

    public CompositeIterator(Iterator<T>... iterators) {
        this.iterators = new LinkedList<Iterator<T>>();
        this.iterators.addAll(Arrays.asList(iterators));
    }

    public CompositeIterator(Collection<? extends Iterator<T>> iterators) {
        this.iterators = new LinkedList<Iterator<T>>(iterators);
    }

    @Override
    public boolean hasNext() {
        if (iterators.size() <= 0) return false;

        while (iterators.size() > 0) {
            Iterator<T> iterator = iterators.peek();
            if (iterator.hasNext()) return true;
            else {
                iterators.poll();
            }
        }
        return false;
    }

    @Override
    public T next() {
        if (iterators.size() <= 0) throw new NoSuchElementException();
        Iterator<T> iterator = iterators.peek();
        return iterator.next();
    }


    @Override
    public void remove() {
        if (iterators.size() <= 0)
            throw new IllegalStateException("This iterator has been exhausted and cannot be removed");

        //call remove on that iterator
        Iterator<T> iterator = iterators.peek();
        iterator.remove();
    }

}
