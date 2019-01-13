public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T it, Node p, Node n) {
            item = it;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, sentinel, sentinel);
        size = 0;
    }

    public void addFirst(T item) {
        Node firstNode = sentinel.next;
        Node newNode = new Node(item, sentinel, firstNode);
        firstNode.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node lastNode = sentinel.prev;
        Node newNode = new Node(item, lastNode, sentinel);
        lastNode.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size > 0) {
            Node pointer = sentinel.next;
            System.out.print(pointer.item);
            for (int i = 1; i < size; i += 1) {
                pointer = pointer.next;
                System.out.print(" ");
                System.out.print(pointer.item);
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T res = sentinel.next.item;
            Node firstNode = sentinel.next.next;
            sentinel.next.next = null;
            sentinel.next.prev = null;
            sentinel.next = firstNode;
            firstNode.prev = sentinel;
            size -= 1;
            return res;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T res = sentinel.prev.item;
            Node lastNode = sentinel.prev.prev;
            sentinel.prev.prev = null;
            sentinel.prev.next = null;
            sentinel.prev = lastNode;
            lastNode.next = sentinel;
            size -= 1;
            return res;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            Node pointer = sentinel.next;
            for (int i = 0; i < index; i += 1) {
                pointer = pointer.next;
            }
            return pointer.item;
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            return getHelper(sentinel.next, index);
        }
    }

    private T getHelper(Node pointer, int index) {
        if (index == 0) {
            return pointer.item;
        } else {
            return getHelper(pointer.next, index - 1);
        }
    }
}
