package com.gmail.velikiydan;

import com.gmail.velikiydan.fox.ForkAndJoinFoxAlgo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private static Socket client;
    private static ServerSocket server;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    public static void main(String[] args) throws IOException {


        try {
            server = new ServerSocket(Configuration.PORT);
            System.out.print("Server started");

            client = server.accept();
            System.out.print("Connection accepted");

            try {
                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream(client.getInputStream());

                while (client.isConnected()) {

                    System.out.println("Server reading from client");

                    String entry = (String) in.readObject();

                    handleClientMessage(entry);

                    out.flush();
                }

            } finally {
                client.close();
                in.close();
                out.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server shutdown");
            server.close();
        }


    }

    private static void handleClientMessage(String entry) throws IOException, ClassNotFoundException {
        if (entry.equals(Configuration.SERVER_SIDE_MULTIPLY_COMMAND)) {
            System.out.println("Server side matrix multiply starts");

            int[][] result = new int[Configuration.MATRIX_SIZE][Configuration.MATRIX_SIZE];
            int[][] first = MatrixHelper.generateRandomMatrix(Configuration.MATRIX_SIZE, Configuration.MATRIX_SIZE);
            int[][] second = MatrixHelper.generateRandomMatrix(Configuration.MATRIX_SIZE, Configuration.MATRIX_SIZE);

            MultiplicationAlgorithm foxForkJoinAlgorithm = new ForkAndJoinFoxAlgo();
            foxForkJoinAlgorithm.multiplyMatrix(first, second, result);

            System.out.println("Server side matrix multiply is finished");

            out.writeObject(result);
        }

        if (entry.equals(Configuration.CLIENT_SIDE_MULTIPLY_COMMAND)) {
            System.out.println("Client side matrix multiply starts");

            int[][] result = new int[Configuration.MATRIX_SIZE][Configuration.MATRIX_SIZE];

            int[][] first = (int[][]) in.readObject();
            int[][] second = (int[][]) in.readObject();

            MultiplicationAlgorithm foxForkJoinAlgorithm = new ForkAndJoinFoxAlgo();
            foxForkJoinAlgorithm.multiplyMatrix(first, second, result);

            System.out.println("Client side matrix multiply is finished");

            out.writeObject(result);
        }
    }
}