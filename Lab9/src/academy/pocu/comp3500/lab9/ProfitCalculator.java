package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.Task;

public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
        int sum = 0;
        quickSort(tasks);

        /*for (int i = 0; i < tasks.length; i++) {
            System.out.println("어려움 : " + tasks[i].getDifficulty() + ", 이익 : " + tasks[i].getProfit());
        }*/

        for (int i = 0; i < skillLevels.length; i++) {
            for (int j = 0; j < tasks.length; j++) {
                if (skillLevels[i] >= tasks[j].getDifficulty()) {
                    sum += tasks[j].getProfit();
                    break;
                }
            }
        }

        return sum;
    }

    public static void quickSort(Task[] tasks) {
        quickSortRecursive(tasks, 0, tasks.length - 1);
    }
    
    public static void quickSortRecursive(Task[] tasks, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int pivotPos = partition(tasks, left, right);
        
        quickSortRecursive(tasks, left, pivotPos - 1);
        quickSortRecursive(tasks, pivotPos + 1, right);
    }

    private static int partition(Task[] tasks, int left, int right) {
        int pivot = tasks[right].getProfit();

        int i = (left - 1);
        for (int j = left; j < right; ++j) {
            if (tasks[j].getProfit() > pivot) {
                ++i;
                swap(tasks, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(tasks, pivotPos, right);

        return pivotPos;
    }

    public static void swap(Task[] tasks, int left, int right) {
        Task t = new Task(tasks[left].getDifficulty(), tasks[left].getProfit());
        tasks[left] = new Task(tasks[right].getDifficulty(), tasks[right].getProfit());
        tasks[right] = new Task(t.getDifficulty(), t.getProfit());
    }
}