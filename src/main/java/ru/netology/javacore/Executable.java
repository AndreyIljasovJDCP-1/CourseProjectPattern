package ru.netology.javacore;

public interface Executable {
    void addTask(Order order);
    void removeTask(Order order);
    void restoreTask();
    String getAllTasks();
}
