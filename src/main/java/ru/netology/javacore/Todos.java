package ru.netology.javacore;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Todos implements Executable {
    private List<String> taskList;
    private final Deque<Order> orderDeque;
    private static final int SIZE = 7;

    public Todos() {
        this.taskList = new ArrayList<>();
        this.orderDeque = new ArrayDeque<>();
    }

    @Override
    public void addTask(Order order) {
        if (taskList.size() < SIZE) {
            taskList.add(order.getTask());
            //orderDeque.offerLast(order);
            orderDeque.push(order);
        }
    }

    @Override
    public void removeTask(Order order) {
        if (taskList.remove(order.getTask())) {
            //orderDeque.offerLast(order);
            orderDeque.push(order);
        }
    }

    @Override
    public void restoreTask() {
        if (!orderDeque.isEmpty()) {
            //Order order = orderDeque.pollLast();
            Order order = orderDeque.pop();
            switch (order.getType()) {
                case ADD:
                    taskList.remove(order.getTask());
                    break;
                case REMOVE:
                    taskList.add(order.getTask());
            }
        }
    }

    @Override
    public String getAllTasks() {
        return taskList.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.joining(" "));
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

    public Deque<Order> getOrderDeque() {
        return orderDeque;
    }
}
