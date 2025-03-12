package problems.datastructure.queue;


import java.util.Stack;

public class QueueUsingStack<T> {
    private Stack<T> stackNewest;
    private Stack<T> stackOldest;

    public QueueUsingStack() {
        stackNewest = new Stack<>();
        stackOldest = new Stack<>();
    }

    public void add(T value) {
        stackNewest.push(value);
    }

    private void shiftStack() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public T remove() {
        shiftStack();

        if (stackOldest.isEmpty()) {
            throw new IllegalStateException("queue empty");
        }

        return stackOldest.pop();
    }

    public T peek() {
        shiftStack();
        if (stackOldest.isEmpty()) {
            throw new IllegalStateException("queue empty");
        }
        return stackOldest.peek();
    }

    public boolean isEmpty() {
        return stackNewest.isEmpty() && stackOldest.isEmpty();
    }

    public int size() {
        return stackNewest.size() + stackOldest.size();
    }
}
