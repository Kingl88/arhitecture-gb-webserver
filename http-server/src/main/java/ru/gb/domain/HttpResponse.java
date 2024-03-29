package ru.gb.domain;

import java.io.Reader;
import java.util.Map;

public class HttpResponse {

    private HttpCode statusCode;
    private Map<String, String> headers;
    private Reader body;

    private HttpResponse() {
    }

    public HttpCode getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Reader getBody() {
        return body;
    }
    public static ResponseBuilder createResponseBuilder(){
        return new ResponseBuilder();
    }
    public static class ResponseBuilder{
        private HttpResponse response = new HttpResponse();

        private ResponseBuilder() {
        }

        public ResponseBuilder withStatusCode(HttpCode statusCode){
            this.response.statusCode = statusCode;
            return this;
        }
        public ResponseBuilder withHeaders(Map<String, String> headers){
            this.response.headers = headers;
            return this;
        }
        public ResponseBuilder withBody(Reader body){
            this.response.body = body;
            return this;
        }
        public HttpResponse build(){
            return response;
        }
    }
}
