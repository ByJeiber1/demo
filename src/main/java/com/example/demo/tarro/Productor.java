package com.example.demo.tarro;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/*
 * 
 * CAMBIAaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
 */
public class Productor {

    public  int idproductor;
    public int cantidad;

    public Productor(int idproductor, int cantidad) {
        this.idproductor = idproductor;
        this.cantidad = cantidad;
    }

    public static void produceProducts(String url){
        /*System.out.println("Inserte un nombre");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("PUT");
            //Send a json body to the request
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);;
            try(OutputStream os = conn.getOutputStream()) {
                ProducerDto producerDto = new ProducerDto(name, 0);
                Gson gson = new Gson();
                String body = gson.toJson(producerDto);
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }*/
    }

    public static void replicar(String url){
        /*System.out.println("Inserte un nombre");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("POST");
            //Send a json body to the request
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);;
            try(OutputStream os = conn.getOutputStream()) {
                ProducerDto producerDto = new ProducerDto(name, 0);
                Gson gson = new Gson();
                String body = gson.toJson(producerDto);
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }*/
    }
    
    public static void main (String[] args) throws IOException {
        String url = "http://localhost:8080/";
        boolean check = true;
        //create a menu
        while (check){
            System.out.println("1. Producir Productos");
            System.out.println("2. Crear Replica");
            System.out.println("3. Salir");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    produceProducts(url +"add");
                    break;
                case 2:
                    replicar(url+"activateServerReply");
                    break;
                case 3:
                    check = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

}
