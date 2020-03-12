package sample;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;
public class Server implements  Runnable{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Thread thread;
    private String name;
    private int port;
    public Server(String name,int port){
        this.name = name;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(this.port);
            System.out.println("Server started");
            socket = server.accept();
            System.out.println("Client accepted");
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("mal");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            Robot r = new Robot();


            while(!line.equals("over")){
                try {
                    System.out.println("dada" + in.read());
                    r.keyPress(KeyEvent.VK_UP);
//                    r.keyRelease(KeyEvent.VK_WINDOWS)r
                }
                catch(IOException e){
                    System.out.println("dada" + e.getMessage());
                }
            }
            System.out.println("Closing server");
            socket.close();
            in.close();
        }
        catch(IOException | AWTException e ){
            System.out.println(e);
        }
    }
    public void start(){
        System.out.println("Starting " + this.name);
        if(this.thread == null) {
            this.thread = new Thread(this, name);
            thread.start();
        }

    }
}
