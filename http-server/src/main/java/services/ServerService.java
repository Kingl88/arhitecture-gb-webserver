package services;

import interfaces.Logger;
import models.LoggerToConsole;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerService implements Closeable {
    private final Logger logger = new LoggerToConsole();
    private final Socket socket;

    public ServerService(Socket socket) {
        this.socket = socket;
    }

    public void sendResponse(String url, String endpoint) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Path path = Paths.get(url, endpoint);
            if (!Files.exists(path)) {
                writer.println("HTTP/1.1 404 NOT_FOUND");
                writer.println("Content-Type: text/html; charset=utf-8");
                writer.println();
                writer.println("<h1>Файл не найден!</h1>");
                writer.flush();
                return;
            }

            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html; charset=utf-8");
            writer.println();

            Files.newBufferedReader(path).transferTo(writer);
            writer.flush();

            logger.writeMessage("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            while (!reader.ready()) ;
            String firstLine = reader.readLine();
            logger.writeMessage(firstLine);
            while (reader.ready()) {
                logger.writeMessage(reader.readLine());
            }
            return firstLine;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}
