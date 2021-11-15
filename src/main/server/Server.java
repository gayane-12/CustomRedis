package main.server;

import main.server.commands.CommandParser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(int port) {
        try {
            this.server = new ServerSocket(port);
            System.out.println("Server started");
            this.socket = this.server.accept();
            System.out.println("Client accepted");
            this.in = new DataInputStream(
                    new BufferedInputStream(this.socket.getInputStream()));
            // TODO think about concurrency
            String command = "";
            while (!command.equals("exit")) {
                try {
                    command = this.in.readUTF();
                    String response = new CommandParser(command).parse();
                    System.out.println(response);
                } catch (IOException i) {
                    System.out.println(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Closing connection");
            this.socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }
}
