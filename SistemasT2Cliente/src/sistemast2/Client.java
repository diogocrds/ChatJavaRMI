/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemast2;

import java.util.Random;

/**
 *
 * @author Diogo
 */
public class Client {
    private String nome;
    private int id;
    
    public static int randInt(int min, int max) {
        Random rand = new Random();
        
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Client(String n){
        this.nome = n;
    }
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
