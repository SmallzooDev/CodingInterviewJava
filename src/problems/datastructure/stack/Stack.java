package problems.datastructure.stack;

public class Stack {
    private static final int MAX_STACK_SIZE = 10;
    private int top;
    private final int[] data = new int[MAX_STACK_SIZE];

    public Stack() {
        top = -1;
    }

    public void push(int data_) throws Exception {
        if (isFull()) {
            throw new Exception("stack full !!");
        }
        data[++top] = data_;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("stack empty");
        }
        return data[top--];
    }

    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("stack empty");
        }
        return data[top];
    }

    private boolean isFull() {
        return top == MAX_STACK_SIZE - 1;
    }

    private boolean isEmpty() {
        return top == -1;
    }

    private int size() {
        return top + 1;
    }
}
