package com.example.demo.tarro;

import java.io.DataOutputStream;
import java.net.Socket;
import java.rmi.Naming;

import org.springframework.stereotype.Service;

@Service("ServicioServidor")
public class ServicioServidor {

    public void llenado(Productor productor) {
        try {
            Inter service = (Inter) Naming.lookup("rmi://localhost:5099/hello");
            service.put(productor.cantidad, productor.idproductor);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Container is not ready");
        }
    }
    
    public String consultar() {
        try {
            Inter service = (Inter) Naming.lookup("rmi://localhost:5099/hello");
            return service.getTransactions();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Container is not ready");
        }
        return "";
    }



    public String consumir(Consumidor consumidor) {
        try {
            Inter service = (Inter) Naming.lookup("rmi://localhost:5099/hello");
            System.out.println(consumidor.tipo);
            return service.get(consumidor.cantidad,  consumidor.tipo, consumidor.idConsumidor);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Container is not ready");
        }
        return "";
    }

    //replica
    public void activateServerReplyBackup(Productor productor) {
        try {
            Socket s = new Socket("localhost", 4200);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("STORAGE");
            dout.flush();
            dout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //replica
    public void restoreBackup(){
        try {
            Socket s = new Socket("localhost", 4200);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("RESTORE");
            dout.flush();
            dout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}