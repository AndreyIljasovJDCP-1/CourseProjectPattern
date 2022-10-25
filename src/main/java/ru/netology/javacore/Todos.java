package ru.netology.javacore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Todos {
    List<String> listTask;

    public Todos() {
        this.listTask = new ArrayList<>();
    }

    public void addTask(String task) {
        if (listTask.size() < 7) {
            listTask.add(task);
        }
    }

    public void removeTask(String task) {
        listTask.remove(task);
    }

    public String getAllTasks() {
        return listTask.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.joining(" "));
    }

}
