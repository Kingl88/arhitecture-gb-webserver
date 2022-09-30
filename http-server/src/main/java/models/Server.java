package models;

import interfaces.Logger;
import services.ServerService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Logger logger = new LoggerToConsole();
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.writeMessage("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.writeMessage("New client connected!");

                new Thread(new Client(new ServerService(socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
