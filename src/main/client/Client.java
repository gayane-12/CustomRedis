package main.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client(String address, int port) {
        try {
            this.socket = new Socket(address, port);
            System.out.println("Connected");
            System.out.println("Write Redis commands...");
            System.out.println("You an write 'exit' to quit.");
            this.input = new DataInputStream(System.in);
            this.out = new DataOutputStream(this.socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
        String command = "";

        while (!command.equals("exit")) {
            try {
                    command = this.input.readLine();
                    this.out.writeUTF(command);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            this.input.close();
            this.out.close();
            this.socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }
}
