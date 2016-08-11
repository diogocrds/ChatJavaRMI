/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemast2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import javax.swing.JFrame;

/**
 *
 * @author Diogo
 */
public class SistemasT2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window = new JFrame("Servidor");
        window.setContentPane(new JPanelServer());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
