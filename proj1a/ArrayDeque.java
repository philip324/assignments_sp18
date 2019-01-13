public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int center;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        center = 0;
    }

    private static int resizeFactor = 2;
    private static double usageRatio = 0.25;

    public void addFirst(T item) {
        /** no looping or recursion
         *  take constant time */
        if (size + 1 == items.length) {
            resize(items.length * resizeFactor);
        }
        items[center] = item;
        center = (center - 1) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        /** no looping or recursion
         *  take constant time */
        if (size + 1 == items.length) {
            resize(items.length * resizeFactor);
        }
        int index = (center + 1 + size) % items.length;
        items[index] = item;
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
            System.out.print(items[center + 1]);
            int index = center + 2;
            int count = 1;
            while (count < size) {
                System.out.print(" ");
                System.out.print(items[index]);
                index += 1;
                count += 1;
            }
        }
    }

    public T removeFirst() {
        /** no looping or recursion
         *  take constant time */
        double ratio = (double) size / items.length;
        if (ratio < usageRatio) {
            resize(items.length / resizeFactor);
        }
        center = (center + 1) % items.length;
        T res = items[center];
        items[center] = null;
        size -= 1;
        return res;
    }

    public T removeLast() {
        /** no looping or recursion
         *  take constant time */
        double ratio = (double) size / items.length;
        if (ratio < usageRatio) {
            resize(items.length / resizeFactor);
        }
        int index = (center + size) % items.length;
        T res = items[index];
        items[index] = null;
        size -= 1;
        return res;
    }

    public T get(int index) {
        /** use iteration */
        if (index >= size) {
            return null;
        } else {
            return items[(center + 1 + index) % items.length];
        }
    }

    private void resize(int cap) {
        /** change items and center */
        T[] a = (T []) new Object[cap];
        int copyLength = Math.min(size, items.length - center);
        System.arraycopy(items, center, a, 0, copyLength);
        System.arraycopy(items, 0, a, copyLength, size - copyLength);
        center = 0;
        items = a;
    }
}
