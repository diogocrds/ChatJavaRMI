/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemast2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

/**
 *
 * @author Diogo
 */
public class Message extends UnicastRemoteObject implements IMessage {
    private String msg;
    private Calendar date;

    public Message() throws RemoteException {
    }
    public Message(String msg) throws RemoteException {
        this.msg = msg;
    }
    
    @Override
    public void setMessage(String msg) throws RemoteException{
        this.msg = msg;
    }
    @Override
    public String getMessage() throws RemoteException{
        return this.msg;
    }
    public void setDate(Calendar c) throws RemoteException{
        this.date = c;
    }
    @Override
    public Calendar getDate() throws RemoteException{
        return date;
    }
}
