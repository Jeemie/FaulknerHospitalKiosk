package Utils;

import java.util.Stack;

/**
 * Created by matt on 4/10/16.
 */
public class FixedSizedStack<T> extends Stack<T> {

    private final int maxSize;

    public FixedSizedStack(int maxSize) {

        super();

        this.maxSize = maxSize;

    }

    @Override
    public T push(T object) {

        // If the stack is too big, remove elements until it's the right size.
        while (this.size() >= maxSize) {

            this.remove(0);

        }

        return super.push(object);

    }

}