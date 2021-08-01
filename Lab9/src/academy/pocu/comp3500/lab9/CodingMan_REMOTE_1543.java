package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.VideoClip;

import java.util.ArrayList;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        boolean[] bVisited = new boolean[clips.length];
        int count = 0;
        int sum = 0;
        int prevMin = 0;
        int prevMax = 0;

        quickSort(clips);

        for (int i = 0; i < clips.length; i++) {
            if (sum >= time) {
                return count;
            }

            if (i < clips.length - 1 && clips[i].getStartTime() == clips[i + 1].getStartTime() && clips[i].getEndTime() <= clips[i + 1].getEndTime()) {
                if (i >= clips.length - 1) {
                    return -1;
                }
                continue;
            }

            if (i > 0 && clips[i - 1].getStartTime() <= clips[i].getStartTime() && clips[i - 1].getEndTime() >= clips[i].getEndTime()) {
                if (i >= clips.length - 1) {
                    return -1;
                }
                continue;
            }

            if (prevMax < clips[i].getStartTime()) {
                return -1;
            }

            sum += (clips[i].getEndTime() - clips[i].getStartTime()) - (prevMax - clips[i].getStartTime());
            count++;
            prevMin = clips[i].getStartTime();
            prevMax = clips[i].getEndTime();
        }

        return count;
    }

    public static void quickSort(VideoClip[] tasks) {
        quickSortRecursive(tasks, 0, tasks.length - 1);
    }

    public static void quickSortRecursive(VideoClip[] clips, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(clips, left, right);

        quickSortRecursive(clips, left, pivotPos - 1);
        quickSortRecursive(clips, pivotPos + 1, right);
    }

    private static int partition(VideoClip[] clips, int left, int right) {
        int pivot = clips[right].getStartTime();

        int i = (left - 1);
        for (int j = left; j < right; ++j) {
            if (clips[j].getStartTime() < pivot) {
                ++i;
                swap(clips, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(clips, pivotPos, right);

        return pivotPos;
    }

    public static void swap(VideoClip[] clips, int left, int right) {
        VideoClip t = new VideoClip(clips[left].getStartTime(), clips[left].getEndTime());
        clips[left] = new VideoClip(clips[right].getStartTime(), clips[right].getEndTime());
        clips[right] = new VideoClip(t.getStartTime(), t.getEndTime());
    }
}