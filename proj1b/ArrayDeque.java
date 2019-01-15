public class ArrayDeque<T> implements Deque<T> {
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

    @Override
    public void addFirst(T item) {
        System.out.println(Math.floorMod(19, items.length));
        items[nextFirst] = item;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size += 1;
        if (size + 1 == items.length) {
            resize(items.length * resizeFactor);
        }
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
        if (size + 1 == items.length) {
            resize(items.length * resizeFactor);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            nextFirst = (nextFirst + 1) % items.length;
            T res = items[nextFirst];
            items[nextFirst] = null;
            size -= 1;
            if (!isEmpty()) {
                double ratio = (double) size / items.length;
                int newLength = Math.max(8, items.length / resizeFactor);
                if (ratio < usageRatio && newLength < items.length) {
                    resize(newLength);
                }
            } else {
                items = (T []) new Object[8];
                nextFirst = 0;
                nextLast = nextFirst + 1;
            }
            return res;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            nextLast = Math.floorMod(nextLast - 1, items.length);
            T res = items[nextLast];
            items[nextLast] = null;
            size -= 1;
            if (!isEmpty()) {
                double ratio = (double) size / items.length;
                int newLength = Math.max(8, items.length / resizeFactor);
                if (ratio < usageRatio && newLength < items.length) {
                    resize(newLength);
                }
            } else {
                items = (T []) new Object[8];
                nextFirst = 0;
                nextLast = nextFirst + 1;
            }
            return res;
        }
    }

    @Override
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
            if (l1 > 0) {
                System.arraycopy(items, (nextFirst + 1) % items.length, a, 1, l1);
            }
            if (size - l1 > 0) {
                System.arraycopy(items, 0, a, l1 + 1, size - l1);
            }
        }
        items = a;
        nextFirst = 0;
        nextLast = (nextFirst + size + 1) % items.length;
    }
}
