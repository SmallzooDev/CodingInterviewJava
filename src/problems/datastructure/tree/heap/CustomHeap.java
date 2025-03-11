package problems.datastructure.tree.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomHeap<T> {
    private final List<T> heap;
    private final Comparator<T> comparator;

    public CustomHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void add(T element) {
        heap.add(element);
        siftUp(heap.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap.getFirst();
    }

    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T result = heap.getFirst();
        T lastElement = heap.removeLast();

        if (!isEmpty()) {
            heap.set(0, lastElement);
            siftDown(0);
        }

        return result;
    }

    private void siftUp(int index) {
        if (index <= 0) return;

        int parentIndex = parent(index);
        if (comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            siftUp(parentIndex);
        }
    }

    private void siftDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < heap.size()
                && comparator.compare(heap.get(left), heap.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < heap.size() &&
                comparator.compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
