public class Fibonacci {
    public int topDown(int num, int[] cache) {
        if (num <= 1) {
            return num;
        }

        if (cache[num] != 0) {
            return cache[num];
        }

        int ret = topDown(num - 1, cache) + topDown(num - 2, cache);

        cache[num] = ret;

        return ret;
    }

    public int downTop(int num) {
        int[] cache = new int[num + 1];

        cache[0] = 0;
        cache[1] = 1;

        for (int i = 2; i <= num; i++) {
            cache[i] = cache[i - 2] + cache[i - 1];
        }

        return cache[num];
    }
}
