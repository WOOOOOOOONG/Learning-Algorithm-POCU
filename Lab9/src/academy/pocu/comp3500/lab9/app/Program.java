package academy.pocu.comp3500.lab9.app;

import academy.pocu.comp3500.lab9.CodingMan;
import academy.pocu.comp3500.lab9.ProfitCalculator;
import academy.pocu.comp3500.lab9.PyramidBuilder;
import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;

public class Program {

    public static void main(String[] args) {
        VideoClip[] clips = new VideoClip[]{
                new VideoClip(0, 15),
                new VideoClip(10, 20),
                new VideoClip(30, 35)
        };

        int count = CodingMan.findMinClipsCount(clips, 10); // 1
        System.out.println(count);
        count = CodingMan.findMinClipsCount(clips, 20); // 2
        System.out.println(count);
        count = CodingMan.findMinClipsCount(clips, 25); // -1
        System.out.println(count);
        count = CodingMan.findMinClipsCount(clips, 35); // -1
        System.out.println(count);
    }
}
