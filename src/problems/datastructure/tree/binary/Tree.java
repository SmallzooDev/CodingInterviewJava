package problems.datastructure.tree.binary;

public class Tree<T> {
    public static class Node<T> {
        T data;
        Node<T> left, right;

        public Node(T data) {
            this.data = data;
        }
    }

    public Node<T> root;

    public void setRoot(Node<T> node) {
        root = node;
    }

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> createNode(T data, Node<T> left, Node<T> right) {
        Node<T> node = new Node<>(data);
        node.left = left;
        node.right = right;
        return node;
    }

    public void inOrder(Node<T> node) {
        if(node == null) return;

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public void preOrder(Node<T> node) {
        if(node == null) return;

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder(Node<T> node) {
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }
}
