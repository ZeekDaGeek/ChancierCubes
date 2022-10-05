package tv.zeekdageek.chanciercubes.util;

import java.util.ArrayList;
import java.util.List;

public class Scheduler
{
    private static final List<Task> tasks = new ArrayList<>();

    public static boolean scheduleTask(Task task) {
        if (task.delayLeft == 0) {
            task.callback();
            return false;
        }
        return tasks.add(task);
    }

    public static boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public static void tickTasks() {
        for (int i = tasks.size() - 1; i >= 0; i--) {
            Task task = tasks.get(i);

            if (task.tickTask()) {
                task.callback();
                tasks.remove(i);
            } else if (task.shouldUpdate()) {
                task.update();
            }
        }
    }
}
