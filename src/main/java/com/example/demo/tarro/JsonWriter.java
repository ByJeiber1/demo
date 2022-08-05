package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import products.ProductA;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.stream.JsonReader;
import products.ProductB;
import tarr.JsonLog;

public class JsonWriter {
    static String pathProductA = "src/main/java/products/productsA.json";
    static String pathProductB = "src/main/java/products/productsB.json";
    static String pathOutput = "src/main/java/json/Output.json";

    static String pathTransactions = "src/main/java/json/Transactions.json";
    public static void AppendProductAtoJson(List<ProductA> productA) throws IOException {
        try (Writer writer = new FileWriter(pathProductA, false)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(productA, writer);
        }
    }

    public static void AppendProductBtoJson(List<ProductB> productB ) throws IOException {
        try( Writer writer = new FileWriter(pathProductB, false)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(productB, writer);
        }
    }

    public static void AppendProductAtoOutput(List<?> products) throws IOException {
        try (Writer writer = new FileWriter(pathOutput, false)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(products, writer);
        }
    }

    public static void AppendTransactiontoJson(List<JsonLog> transactions) throws IOException {
        try (Writer writer = new FileWriter(pathTransactions, false)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(transactions, writer);
        }
    }

}
