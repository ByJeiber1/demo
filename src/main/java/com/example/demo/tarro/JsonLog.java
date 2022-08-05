package com.example.demo.tarro;
import com.example.demo.tarro.TarroProductos;
import java.util.Date;

public class JsonLog {
        public int cantidad ;
        public int id;
        public Date date;
        public boolean producer;

        
        public boolean isProducer() {
            return producer;
        }
    
        public void setProducer(boolean producer) {
            this.producer = producer;
        }
    
        public JsonLog(int cantidad, int id, boolean producer) {
            this.cantidad = cantidad;
            this.id = id;
            this.date = new Date();
            this.producer = producer;
        }
    
    
        
}
