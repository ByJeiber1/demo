package com.example.demo.tarro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.example.demo.tarro.JsonLog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class JsonReader {
    static String pathProducto = "src/main/java/com/example/demo/tarro/Producto.json";
    static String pathTransactions = "src/main/java/com/example/demo/tarro/Transaccion.json";
    static String pathOutput = "src/main/java/com/example/demo/tarro/Output.json";
    
    /*public static TarroProductos getProductsAFromJsonFile() throws FileNotFoundException {
        final Type productos = new TypeToken<TarroProductos>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new FileReader(pathProductA));
        List<TarroProductos> productsA = gson.fromJson(reader, productos);
        return TarroProductos;
    }*/

    /*public static ArrayList<ProductB> getProductsBFromJsonFile() throws FileNotFoundException {
        final Type ListProductB = new TypeToken<List<ProductB>>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new FileReader(pathProductB));
        List<ProductB> productsB = gson.fromJson(reader, ListProductB);
        return (ArrayList<ProductB>) productsB;
    }*/

    public static List<?> getProductsFromJsonOutput() throws FileNotFoundException {
        final Type ListProducts = new TypeToken<List<?>>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new FileReader(pathOutput));
        List<?> products = gson.fromJson(reader, ListProducts);
        return products;
    }

    public static List<JsonLog> getTransactionsFromJsonFile() throws FileNotFoundException {
        final Type ListTransactions = new TypeToken<List<JsonLog>>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new FileReader(pathTransactions));
        return gson.fromJson(reader, ListTransactions);
    }


}
