/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lab7;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author acer
 */
public class ClassBai1Server {

    public static void main(String[] args) {
        try {
            ServerSocket welcomeSocket = new ServerSocket(8888);
            System.out.println("Server is connecting....");
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Server is connected");
            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            while (true) {
                double number1 = inFromClient.readDouble();
                double number2 = inFromClient.readDouble();
                System.out.println("So thu nhat: "+number1);
                System.out.println("So thu hai: "+number2);
                double sum = number1 + number2;
                outToClient.writeDouble(sum);
                outToClient.flush();
                System.out.println("Tong 2 so la: " + sum);
            }
        } catch (Exception ex) {
            
        }
    }
}
