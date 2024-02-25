import java.net.*;
import java.io.*;
import java.util.Scanner;

class MyClient {
    public static void main(String args[]) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ip or host name:");
        String host = scan.nextLine();

        Socket s = new Socket(host, 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(din.readUTF());

        String str = "", str2 = "";
        while (!str.equals("stop")) {
            str = br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2 = din.readUTF();
            System.out.println("Server says: " + str2);
        }
        scan.close();
        dout.close();
        s.close();
    }
}