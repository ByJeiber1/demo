public class TarrService {

    public void llenado(ProducerDto producerDto) {
        try {
            ITarr service = (ITarr) Naming.lookup("rmi://localhost:5099/hello");
            service.put(producerDto.getAmount(), producerDto.getName());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Container is not ready");
        }
    }
    
    public String consultar() {
        try {
            ITarr service = (ITarr) Naming.lookup("rmi://localhost:5099/hello");
            return service.getTransactions();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Container is not ready");
        }
        return "";
    }



    public String consumir(ConsumerDto consumerDto) {
        try {
            ITarr service = (ITarr) Naming.lookup("rmi://localhost:5099/hello");
            System.out.println(consumerDto.getType());
            return service.get(consumerDto.getAmount(),  consumerDto.getType(), consumerDto.getName());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Container is not ready");
        }
        return "";
    }

    //replica
    public void activateServerReplyBackup(ProducerDto producerDto) {
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