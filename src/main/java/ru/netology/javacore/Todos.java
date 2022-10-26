package ru.netology.javacore;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Todos implements TaskHandler {
    List<String> listTask;
    Deque<Request> requestDeque;
    private static final int size = 7;

    public Todos() {
        this.listTask = new ArrayList<>();
        this.requestDeque = new ArrayDeque<>();
    }

    @Override
    public void addTask(Request request) {
        if (listTask.size() < size) {
            listTask.add(request.getTask());
            requestDeque.offerLast(request);
        }
    }

    @Override
    public void removeTask(Request request) {
        if (listTask.remove(request.getTask())) {
            requestDeque.offerLast(request);
        }
    }

    @Override
    public void restoreTask() {
        if (!requestDeque.isEmpty()) {
            Request request = requestDeque.pollLast();
            switch (request.getType()) {
                case ADD:
                    listTask.remove(request.getTask());
                    break;
                case REMOVE:
                    listTask.add(request.getTask());
            }
        }
    }

    @Override
    public String getAllTasks() {
        return listTask.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.joining(" "));
    }

}
