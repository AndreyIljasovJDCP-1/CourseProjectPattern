package ru.netology.javacore;

public interface TaskHandler {
    void addTask(Request request);
    void removeTask(Request request);
    void restoreTask();
    String getAllTasks();
}
