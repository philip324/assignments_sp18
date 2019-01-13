public class LinkedListDeque<T> {
    public class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    public void addFirst(T item) {
        /** no looping or recursion
         *  take constant time */
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        /** no looping or recursion
         *  take constant time */
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        /** constant time */
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
        /** no looping or recursion
         *  take constant time */
        if (size == 0) {
            return null;
        } else {
            Node pointer = sentinel.next;
            pointer.next.prev = sentinel;
            sentinel.next = pointer.next;
            pointer.prev = null;
            pointer.next = null;
            size -= 1;
            return pointer.item;
        }
    }

    public T removeLast() {
        /** no looping or recursion
         *  take constant time */
        if (size == 0) {
            return null;
        } else {
            Node pointer = sentinel.prev;
            pointer.prev.next = sentinel;
            sentinel.prev = pointer.prev;
            pointer.prev = null;
            pointer.next = null;
            size -= 1;
            return pointer.item;
        }
    }

    public T get(int index) {
        /** use iteration */
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
        /** use recursion */
        if (index >= size) {
            return null;
        } else if (index == 0) {
            return sentinel.next.item;
        } else {
            removeFirst();
            return getRecursive(index - 1);
        }
    }
}
