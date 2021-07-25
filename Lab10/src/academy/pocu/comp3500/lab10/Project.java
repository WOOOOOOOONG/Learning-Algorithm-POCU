package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        HashSet<Task> discovered = new HashSet<>();
        LinkedList<String> sortedList = new LinkedList<>();
        int sccStart = -1;
        int sccEnd = -1;

        for (Task task : tasks) {
            if (discovered.contains((tasks))) {
                continue;
            }

            findScheduleRecursive(task, discovered, sortedList);
        }

        HashSet<String> hs = new HashSet<>();
        if (includeMaintenance) {
            for (int i = 0; i < sortedList.size(); i++) {
                if (hs.contains(sortedList.get(i))) {
                    if (sccStart == -1) {
                        int j = i - 1;
                        sccStart = i;
                        sccEnd = i;
                        while (sccStart != -1) {
                            if (sortedList.get(j).equals(sortedList.get(i))) {
                                sccStart = j;
                                break;
                            }
                            j--;
                        }
                    } else {
                        sccEnd = i;
                    }
                } else if (sccStart != -1) {
                    for (int j = sccStart; j <= sccEnd; j++) {
                        hs.remove(sortedList.get(j));
                    }
                    sccStart = -1;
                    sccEnd = -1;
                }
                hs.add(sortedList.get(i));
            }
        } else {
            for (int i = 0; i < sortedList.size(); i++) {
                if (hs.contains(sortedList.get(i))) {
                    continue;
                }
                hs.add(sortedList.get(i));
            }
        }

        sortedList.clear();
        Iterator<String> iter = hs.iterator();
        while (iter.hasNext()) {
            sortedList.add(iter.next());
        }

        return sortedList;
    }

    public static void findScheduleRecursive(final Task task, HashSet<Task> discovered, LinkedList<String> linkedList) {
        discovered.add(task);
        for (Task prevTask : task.getPredecessors()) {
            if (discovered.contains(prevTask)) {
                continue;
            }

            findScheduleRecursive(prevTask, discovered, linkedList);
        }

        linkedList.addLast(task.getTitle());
    }
}