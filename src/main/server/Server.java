package main.server;

import main.server.commands.CommandParser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;

    public Server(int port) throws IOException, ExecutionException, InterruptedException {
        this.server = new ServerSocket(port);
        System.out.println("Server started.");
        this.socket = this.server.accept();
        System.out.println("Client accepted.");
        this.in = new DataInputStream(
                new BufferedInputStream(this.socket.getInputStream()));

        String command = "";
        while (!command.equalsIgnoreCase("exit")) {
            command = this.in.readUTF();
            String finalCommand = command;
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<String> future = service.submit(() -> new CommandParser(finalCommand).parse());
            String response = future.get();
            System.out.println(response);
        }

        System.out.println("Closing connection.");
        this.socket.close();
        in.close();
    }

    public static void main(String args[]) throws InterruptedException, ExecutionException, IOException {
        new Server(5000);
    }
}
