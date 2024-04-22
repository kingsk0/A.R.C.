import java.net.*;
import java.io.*;

class MyServer {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(3333);

        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("Host Name: " + ip.getHostName());
        System.out.println("IP Address: " + ip.getHostAddress());

        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Client connected");
        dout.writeUTF("Connected");
        dout.flush();
        
        String str = "", str2 = "";
        while (!str.equals("stop")) {
            str = din.readUTF();
            System.out.println("client says: " + str);
            str2 = br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        
        din.close();
        s.close();
        ss.close();
    }
}