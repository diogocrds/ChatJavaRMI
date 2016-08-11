/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemast2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 *
 * @author Diogo
 */
public class Atendent {
    private String nome;
    private int id;
    
    public Atendent(String n) {
        this.nome = n;
    }
    
    public String getAtendentName() {
        return nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
