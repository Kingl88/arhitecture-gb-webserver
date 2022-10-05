package ru.gb.domain;

import java.io.Reader;
import java.util.Map;

public class HttpResponse {

    private int statusCode;
    private Map<String, String> headers;
    private Reader body;

    public HttpResponse(int statusCode, Map<String, String> headers, Reader body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Reader getBody() {
        return body;
    }

    public void setBody(Reader body) {
        this.body = body;
    }
    // TODO
}
