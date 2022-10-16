package ru.gb;

import ru.gb.handler.MethodHandlerFactory;
import ru.gb.handler.RequestHandler;
import ru.gb.logger.Logger;
import ru.gb.logger.LoggerFactory;
import ru.gb.service.SocketService;
import ru.gb.service.SocketServiceFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final String WWW = "C:/Users/siarh/IdeaProjects/arhitecture-gb-webserver/www/";
    private static final Logger logger = LoggerFactory.create("ServerLog.txt");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");
                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                new Thread(new RequestHandler(RequestParserImpl.createRequestParser(),
                        socketService,
                        MethodHandlerFactory.create(socketService,
                        WWW))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
