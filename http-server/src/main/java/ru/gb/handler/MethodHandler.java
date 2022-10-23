package ru.gb.handler;

import ru.gb.ResponseSerializerImpl;
import ru.gb.domain.HttpCode;
import ru.gb.service.SocketService;
import ru.gb.domain.HttpRequest;
import ru.gb.domain.HttpResponse;

import java.io.StringReader;
import java.util.Map;
import java.util.Objects;

public abstract class MethodHandler {
    private final String method;
    private final MethodHandler next;

    public MethodHandler(String method, MethodHandler next) {
        this.method = method;
        this.next = next;
    }
    public void handle(HttpRequest request, SocketService socketService){
        HttpResponse response;
        if(method.equalsIgnoreCase(request.getMethod())){
           response = handleInternal(request);
        } else if (Objects.nonNull(next)) {
            next.handle(request, socketService);
            return;
        } else {
            response = HttpResponse.createResponseBuilder()
                    .withStatusCode(HttpCode.METHOD_NOT_ALLOWED)
                    .withHeaders(Map.of("Content-Type", "text/html; charset=utf-8"))
                    .withBody(new StringReader("<h1>Method not allowed</h1>\n"))
                    .build();
        }
        socketService.writeResponse(response, new ResponseSerializerImpl());
    }
    protected abstract HttpResponse handleInternal(HttpRequest request);
}
