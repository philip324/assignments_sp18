public class hw0 {
    public static void main(String[] args) {
        /** Draw Triangle */
        // int N = 5;
        // drawTriangle(N);

        /** Find Maximum */
        // int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        // int ret = max(numbers);
        // System.out.println(ret);

        /** windowPosSum */
        // int[] a = new int[]{1, 2, -3, 4, 5, 4};
        // int n = 3;
        int[] a = new int[]{1, -1, -1, 10, 5, -1};
        int n = 2;
        int[] ret = windowPosSum(a, n);
        System.out.println(java.util.Arrays.toString(ret));
    }

    public static void drawTriangle(int N) {
        for (int i = 0; i < N; i = i + 1) {
            for (int j = 0; j <= i; j = j + 1) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static int max(int[] m) {
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < m.length; i = i + 1) {
            if (m[i] > ret) {
                ret = m[i];
            }
        }
        return ret;
    }

    public static int[] windowPosSum(int[] a, int n) {
        /** Requirements:
          * replace a[i] with the sum of a[i] through a[i + n]
          * ignore if a[i] < 0
          * sum to min(a.length, i+n)
          */
        int[] ret = new int[a.length];
        for (int i = 0; i < a.length; i = i + 1) {
            if (a[i] < 0) {
                ret[i] = a[i];
                continue;
            }
            int curr_sum = 0;
            for (int j = i; j <= i+n; j = j + 1) {
              if (j == a.length) {
                  break;
              }
              curr_sum = curr_sum + a[j];
            }
            ret[i] = curr_sum;
        }
        return ret;
    }
}
