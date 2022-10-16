package ru.gb.service;

import ru.gb.ResponseSerializer;
import ru.gb.domain.HttpResponse;

import java.io.Closeable;
import java.util.List;

public interface SocketService extends Closeable {
    List<String> readRequest();
    void writeResponse(HttpResponse response, ResponseSerializer serializer);
}
