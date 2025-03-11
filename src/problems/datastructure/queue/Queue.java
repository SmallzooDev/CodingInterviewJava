package problems.datastructure.queue;

public class Queue {
    private static int MAX_QUEUE_SIZE = 10;
    private int rear, front, size;
    private int[] data = new int[MAX_QUEUE_SIZE];

    public Queue() {
        front = rear = size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int item) {
        rear = (rear + 1) % data.length;
        data[rear] = item;
        size++;
    }

    public int pop() throws Exception {
        if (isEmpty()) throw new Exception("queue empty");
        front = (front + 1) % data.length;
        int item = data[front];
        data[front] = 0;
        size--;
        return item;
    }

    public int peek() throws Exception {
        if (isEmpty()) throw new Exception("queue empty");
        return data[front];
    }
}
