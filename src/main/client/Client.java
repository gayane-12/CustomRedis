package main.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream out;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket(address, port);

        System.out.println("Welcome to CustomRedis.");
        System.out.println("Store, fetch, update and delete data with Redis commands.");
        System.out.println("Type 'exit' anytime to quit.");
        System.out.println("Write Redis command...");

        this.input = new DataInputStream(System.in);
        this.out = new DataOutputStream(this.socket.getOutputStream());

        String command = "";
        while (!command.equalsIgnoreCase("exit")) {
            command = this.input.readLine();
            this.out.writeUTF(command);
        }

        this.input.close();
        this.out.close();
        this.socket.close();
    }

    public static void main(String args[]) throws IOException {
        new Client("127.0.0.1", 5000);
    }
}
