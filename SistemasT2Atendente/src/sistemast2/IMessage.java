/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemast2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

/**
 *
 * @author Diogo
 */
public interface IMessage extends Remote {
    public void setMessage(String msg) throws RemoteException;
    public String getMessage() throws RemoteException;
    public void setDate(Calendar c) throws RemoteException;
    public Calendar getDate() throws RemoteException;
}
