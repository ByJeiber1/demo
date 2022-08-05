package com.example.demo.tarro;
import com.example.demo.tarro.JsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/*import common.ProductsChecker;
import json.JsonReader;
import json.JsonWriter;
import products.ProductA;
import products.ProductB;
import products.ProductsToSend;*/
import com.example.demo.tarro.Inter;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
/*
 * 
 * CAMBIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
 * xdd
 * 
 */
public class TarroProductos extends UnicastRemoteObject implements Inter{
    //private int contained;
    private boolean full = false;
    int productA;
    int productB;
    List<JsonLog> transactions = new ArrayList<>();
    Gson gson = new GsonBuilder().create();

    public TarroProductos() throws RemoteException {
        super();
        try {
            productA = 600;
            productB = 400;
            transactions = JsonReader.getTransactionsFromJsonFile();
            /*if(!ProductsChecker.checkProductsAAvailable(productsA) || !ProductsChecker.checkProductsBAvailable(productsB)){
                full = false;
            }else {
                full = true;
            }*/
        } catch (FileNotFoundException e) {
            productA = 600;
            productB = 400;
            transactions = new ArrayList<>();
        }
    }

    public synchronized String get(int amount, String type, int consumer) throws IOException {
        while (!full) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(type.equals("A"));

        if (type.equals("A")) {
            int i = 0;
            for (TarroProductos producto : productos) {
                if (i < amount) {
                    if (productA.isAvailable()){
                        productsAToReturn.add(productA);
                        productA.setAvailable(false);
                        i++;
                    }

                }else {
                    break;
                }
            }


            
            System.out.println("ProductsA to return: " + productsAToReturn);
            JsonWriter.AppendProductAtoJson(productsA);
            full = ProductsChecker.checkProductsAAvailable(productsA) && ProductsChecker.checkProductsBAvailable(productsB);
            notifyAll();
            JsonLog jsonLog = new JsonLog(productsAToReturn.size(),consumer,productsA,productsB,productsAToReturn,productsBToReturn, false);
            transactions.add(jsonLog);
            JsonWriter.AppendTransactiontoJson(transactions);
            return gson.toJson(productsAToReturn);
        } else {
            int i = 0;
            for (ProductB productB : productsB) {
                if (i < amount) {
                    if (productB.isAvailable()){
                        productsBToReturn.add(productB);
                        productB.setAvailable(false);
                        i++;
                    }
                }else {
                    break;
                }
            }
            JsonWriter.AppendProductBtoJson(productsB);
            full = ProductsChecker.checkProductsAAvailable(productsA) && ProductsChecker.checkProductsBAvailable(productsB);
            notifyAll();
            JsonLog jsonLog = new JsonLog(productsBToReturn.size(),consumer,productsA,productsB,productsAToReturn,productsBToReturn, false);
            transactions.add(jsonLog);
            JsonWriter.AppendTransactiontoJson(transactions);
            System.out.println("ProductsB to return: " + productsBToReturn);
            return gson.toJson(productsBToReturn);
        }


    }

    public synchronized void put(int value, int producer) throws IOException {
        contained = value;
        if (productsB == null) {
            productsB = new ArrayList<>();
        }
        if (productsA == null) {
            productsA = new ArrayList<>();
        }
        for (int i = 0; i < 60; i++) {
            ProductA productA = new ProductA();
            productsA.add(productA);
            System.out.println("tarr.Producer produced " + productA.getName());
        }
        for (int i = 0; i < 40; i++) {
            ProductB productB = new ProductB();
            productsB.add(productB);
            System.out.println("tarr.Producer produced " + productB.getName());
        }
        JsonWriter.AppendProductAtoJson(productsA);
        JsonWriter.AppendProductBtoJson(productsB);
        JsonLog jsonLog = new JsonLog(100,producer,productsA,productsB,new ArrayList<ProductA> (),new ArrayList<ProductB>(), true);
        transactions.add(jsonLog);
        JsonWriter.AppendTransactiontoJson(transactions);
        System.out.println("tarr.Producer produced " + contained);
        full = true;
        notifyAll();
    }

    public synchronized String getTransactions() {
        return gson.toJson(transactions);
    }

    public synchronized String getAllProducts(){
        return gson.toJson(addProducts());
    }

    //add productsA with productsB and return a list of productsA and productsB
    public ProductsToSend addProducts(){
        ProductsToSend products = new ProductsToSend(productsA,productsB);
        return products;
    }  
}
