package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import java.net.http.HttpResponse.BodyHandler;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {
        String todoUrl = "https://jsonplaceholder.typicode.com/todos";
        
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
            .newBuilder()
            .GET()
            .uri(URI.create(todoUrl))
            .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        
        // var data = """
        //         {
        //             "userId": 1,
        //             "id": 1,
        //             "title": "delectus aut autem",
        //             "completed": false
        //         }
        //         """;

        List<Todo> todos = objectMapper.readValue(responseBody, new TypeReference<List<Todo>>() {

        });

        for (Todo todo : todos) {
            System.out.println(todo.toString());
        }
        
    }
}

class Todo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public Todo() {
        super();
    }

    public Todo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo [userId=" + userId + ", id=" + id + ", title=" + title + ", completed=" + completed + "]";
    }
}