package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final Todos todos;
    private final int port;

    public TodoServer(int port, Todos todos) {
        this.todos = todos;
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Starting server at " + port + "...");
            Gson gson = new Gson();

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(
                             clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(
                             clientSocket.getInputStream()))) {

                    Order order = gson.fromJson(in.readLine(), Order.class);
                    switch (order.getType()) {
                        case ADD:
                            todos.addTask(order);
                            break;
                        case REMOVE:
                            todos.removeTask(order);
                            break;
                        case RESTORE:
                            todos.restoreTask();
                    }
                    out.println(todos.getAllTasks());
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
