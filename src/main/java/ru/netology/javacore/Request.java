package ru.netology.javacore;

public class Request {
    private final Operation type;
    private final String task;

    public Request(Operation type, String task) {
        this.type = type;
        this.task = task;
    }

    public Request(Operation type) {
        this.type = type;
        this.task = null;
    }

    public Operation getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

}
