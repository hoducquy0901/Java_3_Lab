/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lab7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class ClassBai1Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Client is Connecting....");
            Socket socket = new Socket("localhost", 8888);
            System.out.println("Client is Connected");
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            while (true) {
                System.out.print("Nhap vao so thu 1: ");
                outputStream.writeDouble(sc.nextDouble());
                outputStream.flush();
                System.out.print("Nhap vao so thu 2: ");
                outputStream.writeDouble(sc.nextDouble());
                outputStream.flush();
                double sum  = inputStream.readDouble();
                System.out.println("Tong 2 so: " + sum);
                System.out.print("Tiep tuc?(y/n): ");
                String traloi = sc.next();
                if (traloi.equals("n") || traloi.equals("N")) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
