package ru.netology.javacore;

public class Order {
    private  Operation type;
    private  String task;

    public Order() {
    }

    public Order(Operation type, String task) {
        this.type = type;
        this.task = task;
    }
    public Operation getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

}
