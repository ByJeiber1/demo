package com.example.demo.tarro;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Inter extends Remote {
    
    public void put(int cantidad, int idproductor) throws IOException, RemoteException;
    public String get(int cantidad, String tipoString, int idConsumidor) throws IOException, RemoteException;
    public String getTransactions() throws IOException, RemoteException;
    public String getAllProducts() throws IOException, RemoteException;

}