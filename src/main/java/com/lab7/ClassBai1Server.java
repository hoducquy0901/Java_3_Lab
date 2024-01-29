/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lab7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author acer
 */
public class ClassBai1Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server is connecting....");
            Socket socket = serverSocket.accept();
            System.out.println("Server is connected");
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                double number1 = inputStream.readDouble();
                double number2 = inputStream.readDouble();
                System.out.println("2 Số nhận được từ Client là " + number1
                        + +number2);
                double sum = number1 + number2;
                outputStream.writeDouble(sum);
                outputStream.flush();
                System.out.println("Tong 2 so la: " + sum);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
