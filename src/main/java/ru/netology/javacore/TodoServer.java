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

                    Request request = gson.fromJson(in.readLine(), Request.class);
                    switch (request.getType()) {
                        case ADD:
                            todos.addTask(request);
                            break;
                        case REMOVE:
                            todos.removeTask(request);
                            break;
                        case RESTORE:
                            todos.restoreTask();
                    }
                    String response = todos.getAllTasks();
                    out.println(response+" list: "+todos.getListTask().size()+" deque: "+todos.getRequestDeque().size());
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
