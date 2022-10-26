package ru.netology.javacore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class TestClient {
    private static final int PORT = 8085;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        List<Request> list = objectMapper.readValue(
                new File("request.json"),
                new TypeReference<>() {
                });
        Gson gson = new Gson();

        for (int i = 0; i <  list.size(); i++) {

            try (Socket clientSocket = new Socket(HOST, PORT);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(
                         clientSocket.getInputStream()))) {

                String stringJson = gson.toJson(list.get(i));
                out.println(stringJson);
                System.out.println(in.readLine());
                i++;

                /*String stringJson = "";

                try (BufferedReader reader = new BufferedReader(new FileReader("request.json"))) {
                    while (reader.ready()) {
                        stringJson = reader.readLine();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                out.println(stringJson);
                System.out.println(in.readLine());*/

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
