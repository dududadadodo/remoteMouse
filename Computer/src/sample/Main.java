package sample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Main extends Frame implements ActionListener {
    // Frame
    static JFrame f;

    // textField
    static TextField x, y;

    // default constructor
    Main()
    {
    }

    // main function
    public static void main(String args[])
    {
        // object of class
        Main rm = new Main();
        Server server = new Server("serverThread",9999);
        server.start();
        // create a frame
        f = new JFrame("robomouse");

        // set the frame to close on exit
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x = new TextField(7);
        y = new TextField(7);
        Button b = new Button("OK");
        b.addActionListener(rm);
        Panel p = new Panel();
        p.add(x);
        p.add(y);
        p.add(b);
        f.add(p);
        f.setSize(300, 300);
        f.show();
    }

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        try {
            Robot r = new Robot();
            int xi1, yi1, xi, yi;

            Point p = MouseInfo.getPointerInfo().getLocation();
            xi = p.x;
            yi = p.y;

            xi1 = Integer.parseInt(x.getText());
            yi1 = Integer.parseInt(y.getText());
            int i = xi, j = yi;

            while (i != xi1 || j != yi1) {
                r.mouseMove(i, j);
                if (i < xi1)
                    i++;
                if (j < yi1)
                    j++;
                if (i > xi1)
                    i--;
                if (j > yi1)
                    j--;
                Thread.sleep(30);
            }
        }
        catch (Exception evt) {
            System.err.println(evt.getMessage());
        }
    }
}