package problems.datastructure.stack;


public class StackV2<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int top;
    private Object[] elements;
    private int capacity;

    public StackV2() {
        this(DEFAULT_CAPACITY);
    }

    public StackV2(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("초기 용량은 양수여야 합니다");
        }
        this.elements = new Object[initialCapacity];
        this.capacity = initialCapacity;
        this.top = -1;
    }

    public void push(T item) {
        if (isFull()) {
            resize(capacity * 2);
        }
        elements[++top] = item;
    }

    public T pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("stack empty");
        }

        T item = (T) elements[top];
        elements[top--] = null;

        if (top > 0 && top < capacity / 4) {
            resize(capacity / 2);
        }

        return item;
    }

    public T peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("stack empty");
        }
        return (T) elements[top];
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public int capacity() {
        return capacity;
    }

    private void resize(int newCapacity) {
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i <= top; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        capacity = newCapacity;
    }
}
