package tarro;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Inter extends Remote {
    public void put(int num, String producer) throws IOException, RemoteException;
    public String get(int num, String type, String consumer) throws IOException, RemoteException;
    public String getTransactions() throws IOException, RemoteException;

    public String getAllProducts() throws IOException, RemoteException;

}