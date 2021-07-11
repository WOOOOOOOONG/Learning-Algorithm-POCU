package academy.pocu.comp3500;

public class DP {
    public class Item {
        private int space;
        private int value;

        public Item(int space, int value) {
            this.space = space;
            this.value = value;
        }

        public int getSpace() {
            return this.space;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static int getMaxValue(int numSpace, Item[] items) {
        int numItems = items.length;

        int cache[][] = new int[numItems][numSpace + 1];

        for (int s = 1; s <= numSpace; ++s) {
            if (items[0].getSpace() > s) {
                continue;
            }

            cache[0][s] = items[0].getValue();
        }

        for (int i = 1; i < numItems; ++i) {
            for (int s = 1; s <= numSpace; ++s) {
                if (items[i].getSpace() > s) {
                    cache[i][s] = cache[i - 1][s];
                    continue;
                }

                int remainingSpace = s - items[i].getSpace();
                int remainingMaxValue = cache[i - 1][remainingSpace];

                int choice1 = cache[i - 1][s];
                int choice2 = items[i].getValue() + remainingMaxValue;

                cache[i][s] = Math.max(choice1, choice2);
            }
        }

        return cache[numItems - 1][numSpace];
    }
}
