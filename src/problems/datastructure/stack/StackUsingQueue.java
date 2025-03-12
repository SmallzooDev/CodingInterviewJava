package problems.datastructure.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    public StackUsingQueue() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(T value) {
        queue1.add(value);
    }

    public T pop() {
        if (!isEmpty()) {
            throw new IllegalArgumentException("stack empty");
        }

        int size = queue1.size();
        for (int i = 0; i < size - 1; i++) {
            queue2.add(queue1.remove());
        }

        T result = queue1.remove();
        swap();
        return result;
    }
    public T peek() {
        if (!isEmpty()) {
            throw new IllegalArgumentException("stack empty");
        }

        int size = queue1.size();
        for (int i = 0; i < size - 1; i++) {
            queue2.add(queue1.remove());
        }

        T result = queue1.peek();

        queue2.add(queue1.remove());

        swap();

        return result;
    }

    private void swap() {
        Queue<T> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public int size() {
        return queue1.size();
    }
}
