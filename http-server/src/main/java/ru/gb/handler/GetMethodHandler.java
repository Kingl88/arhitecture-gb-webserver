package ru.gb.handler;

import ru.gb.config.Config;
import ru.gb.domain.HttpCode;
import ru.gb.service.SocketService;
import ru.gb.domain.HttpRequest;
import ru.gb.domain.HttpResponse;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
@Handler(order = 0)
public class GetMethodHandler extends MethodHandler{
    private final Config config;

    public GetMethodHandler(String method, MethodHandler next, Config config) {
        super(method, next);
        this.config = config;
    }
    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        Path path = Paths.get(config.getWwwHome(), request.getPath());
        if (!Files.exists(path)) {
            return HttpResponse.createResponseBuilder()
                    .withStatusCode(HttpCode.NOT_FOUND)
                    .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                    .withBody(new StringReader("<h1>Файл не найден!</h1>\n"))
                    .build();
        }

        try {
            return HttpResponse.createResponseBuilder()
                    .withStatusCode(HttpCode.OK)
                    .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                    .withBody(Files.newBufferedReader(path))
                    .build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
