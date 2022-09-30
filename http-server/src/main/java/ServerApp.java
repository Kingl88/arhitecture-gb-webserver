import models.Server;

public class ServerApp {
    public static void main(String[] args) {
        new Server(8089).start();
    }
}
