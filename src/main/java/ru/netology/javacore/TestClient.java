package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestClient {
    private static final int PORT = 8085;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {
        var list = Files.readAllLines(Path.of("order.txt"));
        for (var order : list) {
            try (Socket clientSocket = new Socket(HOST, PORT);
                 var out = new PrintWriter(clientSocket.getOutputStream(), true);
                 var in = new BufferedReader(new InputStreamReader(
                         clientSocket.getInputStream()))) {
                out.println(order);
                System.out.println(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
