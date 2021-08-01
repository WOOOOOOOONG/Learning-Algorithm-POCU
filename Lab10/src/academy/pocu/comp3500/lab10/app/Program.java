package academy.pocu.comp3500.lab10.app;

import academy.pocu.comp3500.lab10.Project;
import academy.pocu.comp3500.lab10.project.Task;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        Task[] tasks = createTasks();

        List<String> schedule = Project.findSchedule(tasks, false);

        assert (schedule.size() == 6);
        assert (schedule.get(0).equals("A"));
        assert (schedule.get(1).equals("B"));
        assert (schedule.get(2).equals("C"));
        assert (schedule.get(3).equals("E"));
        assert (schedule.get(4).equals("F"));
        assert (schedule.get(5).equals("I"));

        for (int i = 0; i < schedule.size(); i++) {
            System.out.print(schedule.get(i) + " ");
        }
        System.out.println();
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);
        Task h = new Task("H", 13);
        Task i = new Task("I", 6);

        i.addPredecessor(f);
        f.addPredecessor(e);
        e.addPredecessor(b, c);
        d.addPredecessor(c, h);
        c.addPredecessor(b);
        b.addPredecessor(a);
        h.addPredecessor(g);
        g.addPredecessor(d);

        return new Task[]{
                a, b, c, d, e, f, g, h, i
        };
    }
}
