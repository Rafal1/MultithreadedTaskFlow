package recruitmenttask;

/**
 * @author Rafał Zawadzki
 */
public interface SingletonCoverQueue<E> {
    public E offer(E t);

    public E poll();
}
