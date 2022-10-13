package ru.gb;

import ru.gb.domain.HttpResponse;
import ru.gb.logger.Logger;
import ru.gb.logger.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandler implements Runnable {

    private static final String WWW = "C:/Users/siarh/IdeaProjects/arhitecture-gb-webserver/www/";

    private static final Logger logger = LoggerFactory.create("RequestHandlerLog.txt");

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8");

        List<String> request = socketService.readRequest();

        Path path = Paths.get(WWW, new RequestParserImpl().parse(request).getPath());
        if (!Files.exists(path)) {
            HttpResponse response = HttpResponse.createResponseBuilder()
                    .withStatusCode(404)
                    .withHeaders(headers)
                    .withBody(new StringReader("<h1>Файл не найден!</h1>\n"))
                    .build();
            socketService.writeResponse(response, new ResponseSerializerImpl());
            return;
        }

        try {
            HttpResponse response = HttpResponse.createResponseBuilder()
                    .withStatusCode(200)
                    .withHeaders(headers)
                    .withBody(Files.newBufferedReader(path))
                    .build();

            socketService.writeResponse(response, new ResponseSerializerImpl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");
    }
}
