package problems.datastructure.tree.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomHeap<T> {
    private List<T> heap;
    private Comparator<T> comparator;

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
        System.out.println("\n----- 요소 추가: " + element + " -----");
        heap.add(element);
        System.out.println("추가 후 상태: " + heap);
        siftUp(heap.size() - 1);
        System.out.println("siftUp 후 최종 상태: " + heap);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        System.out.println("\n----- 요소 추출 -----");
        T result = heap.get(0);
        System.out.println("추출된 요소: " + result);

        T lastElement = heap.remove(heap.size() - 1);
        System.out.println("마지막 요소를 루트로 이동: " + lastElement);

        if (!isEmpty()) {
            heap.set(0, lastElement);
            System.out.println("루트 변경 후 상태: " + heap);
            siftDown(0);
            System.out.println("siftDown 후 최종 상태: " + heap);
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

        // 왼쪽 자식이 현재 요소보다 "작은지" 비교 (최소 힙 기준)
        if (left < heap.size() &&
                comparator.compare(heap.get(left), heap.get(smallest)) < 0) {
            smallest = left;
        }

        // 오른쪽 자식이 현재 가장 작은 요소보다 "작은지" 비교
        if (right < heap.size() &&
                comparator.compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    // 두 요소의 위치 교환
    private void swap(int i, int j) {
        T elementI = heap.get(i);
        T elementJ = heap.get(j);

        System.out.println("SWAP: 인덱스 " + i + "(" + elementI + ") <-> 인덱스 " + j + "(" + elementJ + ")");
        System.out.println("교환 전 상태: " + heap);

        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);

        System.out.println("교환 후 상태: " + heap);
    }

    public void printHeap() {
        System.out.println("현재 힙 상태: " + heap);
    }

    public static void main(String[] args) {
        System.out.println("===== 최소 힙 테스트 =====");
        CustomHeap<Integer> minHeap = new CustomHeap<>((a, b) -> a - b);

        minHeap.add(5);
        minHeap.add(3);
        minHeap.add(8);
        minHeap.add(1);
        minHeap.add(10);
        minHeap.add(2);

        System.out.println("\n최종 힙 상태: " + minHeap.heap);

        System.out.println("\n===== 요소 추출 테스트 =====");
        while (!minHeap.isEmpty()) {
            minHeap.poll();
        }

        System.out.println("\n===== 최대 힙 테스트 =====");
        CustomHeap<Integer> maxHeap = new CustomHeap<>((a, b) -> b - a);

        maxHeap.add(5);
        maxHeap.add(3);
        maxHeap.add(8);
        maxHeap.add(1);
        maxHeap.add(10);
        maxHeap.add(2);

        System.out.println("\n최종 힙 상태: " + maxHeap.heap);

        System.out.println("\n===== 요소 추출 테스트 =====");
        while (!maxHeap.isEmpty()) {
            maxHeap.poll();
        }
    }
}
