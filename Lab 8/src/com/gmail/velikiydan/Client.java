package com.gmail.velikiydan;


import java.io.*;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {


        try (Socket socket = new Socket("localhost", Configuration.PORT);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Client connected to server");

            while (!socket.isOutputShutdown()) {

                if (br.ready()) {

                    String clientCommand = br.readLine();

                    if (clientCommand.equals(Configuration.SERVER_SIDE_MULTIPLY_COMMAND)) {
                        long startTime = System.nanoTime();

                        out.writeObject(clientCommand);
                        out.flush();

                        handleResult(startTime, in);
                    }

                    if (clientCommand.equals(Configuration.CLIENT_SIDE_MULTIPLY_COMMAND)) {
                        long startTime = System.nanoTime();

                        out.writeObject(clientCommand);
                        out.flush();

                        int[][] first = MatrixHelper.generateRandomMatrix(Configuration.MATRIX_SIZE, Configuration.MATRIX_SIZE);
                        int[][] second = MatrixHelper.generateRandomMatrix(Configuration.MATRIX_SIZE, Configuration.MATRIX_SIZE);

                        out.writeObject(first);
                        out.flush();

                        out.writeObject(second);
                        out.flush();

                        handleResult(startTime, in);
                    }

                    System.out.println();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void handleResult(long startTime, ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("Reading from server");
        int[][] matrix = (int[][]) in.readObject();

//        System.out.println("Matrix:");
//        MatrixHelper.printMatrix(matrix);

        long endTime = System.nanoTime();
        System.out.printf("Length: %d\n", matrix.length);
        long duration = (endTime - startTime) / 1000000;
        System.out.printf("Duration: %dms\n", duration);
    }
}