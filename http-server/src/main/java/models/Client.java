package models;

import services.*;

public class Client implements Runnable{
    private static final String WWW = "C:\\Users\\siarh\\IdeaProjects\\arhitecture-gb-webserver\\www";
    private final Parser parser = new Parser();

    private final ServerService serverService;

    public Client(ServerService serverService) {

        this.serverService = serverService;
    }

    @Override
    public void run() {
       String endpoint = parser.getEndpoint(serverService.read());
       serverService.sendResponse(WWW, endpoint);
    }
}
