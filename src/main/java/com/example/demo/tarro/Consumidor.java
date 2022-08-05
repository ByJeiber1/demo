package tarro;

import com.example.distservice.dtos.ConsumerDto;
import com.example.distservice.dtos.ProducerDto;
import com.google.gson.Gson;
import com.sun.jndi.toolkit.url.Uri;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
    //CAMBIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
    //CAMBIAR
    //CAMBIAR
public class Consumidor {
    public int idConsumidor;
    public int cantidad;
    public String tipo;

    public Consumidor(int idConsumidor, int cantidad, String tipo) {
        this.idConsumidor = idConsumidor;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }
    //CAMBIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
    //CAMBIAR
    //CAMBIAR
    //
    //
    //
    //
    public static void consultar(String url){
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode != 200) {
                throw new RuntimeException("Error: " + responseCode);
            }
            StringBuilder result = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while(scanner.hasNextLine()) {
                result.append(scanner.nextLine());
            }
            scanner.close();
            System.out.println(result);

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static void consumir(String url){
        System.out.println("Inserte un nombre");
        Scanner scanner = new Scanner(System.in);
        int idConsumidor = scanner.nextInt();
        System.out.println("Inserte una cantidad");
        int cantidad = scanner.nextInt();
        System.out.println("Inserte un tipo (A o B)");
        String tipo = scanner.nextLine();
        
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("PUT");
            //Send a json body to the request
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                    Consumidor consumidor = new Consumidor(idConsumidor, cantidad, tipo);
                    Gson gson = new Gson();
                    gson.toJson(consumidor);
            /*try(OutputStream os = conn.getOutputStream()) {
                Consumidor consumidor = new Consumidor(idConsumidor, cantidad, tipo);
                Gson gson = new Gson();
                //String body = gson.toJson(consumidor);
                gson.toJson(consumidor);
                //byte[] input = body.getBytes("utf-8");
                //os.write(input, 0, input.length);
            }*/
            try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static void  main(String[] args) {
        String URL="http://localhost/;8080/";
        //Asisgancion de la direccion ip de Spring Boot
        boolean menuCheck = true;
        //variable para salir del menu

        while (menuCheck) {
            System.out.println("Presiona 1 para consumir el producto");
            System.out.println("Presiona 2 para consultar las transacciones e inventario");
            System.out.println("Presiona 3 para salir del menu");
            Scanner scan = new Scanner(System.in);
            int s = scan.nextInt();
            switch (s) {
                case 1:
                    consumir(URL+"consumir");
                    menuCheck = false;
                    break;

                case 2:
                    consultar(URL+"consultar");
                    menuCheck = false;
                    break;

                case 3:
                    menuCheck = false;
                    break;

                default:
                    System.out.println("Debe escoger solo los numeros 1, 2 y 3");
                    break;
            }

        }
    }

}
