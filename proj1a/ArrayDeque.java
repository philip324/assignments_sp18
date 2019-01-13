public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = nextFirst + 1;
    }

    private static int resizeFactor = 2;
    private static double usageRatio = 0.25;

    public void addFirst(T item) {
        if (size + 1 == items.length) {
            resize(items.length * resizeFactor);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size + 1 == items.length) {
            resize(items.length * resizeFactor);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
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
            int index = (nextFirst + 1) % items.length;
            int count = 1;
            System.out.print(items[index]);
            while (count < size) {
                index = (index + 1) % items.length;
                System.out.print(" ");
                System.out.print(items[index]);
                count += 1;
            }
        }
    }

    public T removeFirst() {
        nextFirst = (nextFirst + 1) % items.length;
        T res = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        double ratio = (double) size / items.length;
        if (ratio < usageRatio) {
            int newLength = Math.max(8, items.length / resizeFactor);
            resize(newLength);
        }
        return res;
    }

    public T removeLast() {
        nextLast = (nextLast - 1) % items.length;
        T res = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        double ratio = (double) size / items.length;
        if (ratio < usageRatio) {
            int newLength = Math.max(8, items.length / resizeFactor);
            resize(newLength);
        }
        return res;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            int shiftedIndex = (nextFirst + 1 + index) % items.length;
            return items[shiftedIndex];
        }
    }

    private void resize(int cap) {
        T[] a = (T []) new Object[cap];
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, a, 1, size);
        } else {
            int l1 = items.length - (nextFirst + 1);
            System.arraycopy(items, nextFirst + 1, a, 1, l1);
            int l2 = size - l1;
            System.arraycopy(items, 0, a, l1 + 1, l2);
        }
        nextFirst = 0;
        nextLast = (nextFirst + size + 1) % a.length;
        items = a;
    }
}
