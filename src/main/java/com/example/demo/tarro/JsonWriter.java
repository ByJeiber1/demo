package com.example.demo.tarro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.stream.JsonReader;
import com.example.demo.tarro.JsonLog;

public class JsonWriter {
    static String pathProducto = "src/main/java/com/example/demo/tarro/Producto.json";
    static String pathTransactions = "src/main/java/com/example/demo/tarro/Transaccion.json";

    public static void AppendProductostoJson(TarroProductos Tarro ) throws IOException {
        try (Writer writer = new FileWriter(pathProducto, false)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(Tarro, writer);
        }
    }

    public static void AppendTransactiontoJson(List<JsonLog> transactions) throws IOException {
        try (Writer writer = new FileWriter(pathTransactions, false)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(transactions, writer);
        }
    }

}
