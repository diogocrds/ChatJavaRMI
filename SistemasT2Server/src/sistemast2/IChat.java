package sistemast2;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IChat extends Remote {
    public void deliver(IMessage msg) throws RemoteException;
    public void setConexao(IChat c) throws RemoteException;
    public IChat getConexao() throws RemoteException;
    public void closeConexao() throws RemoteException;
    public String getName() throws RemoteException;
    public int getId() throws RemoteException;
    public void setId(int id) throws RemoteException;
    public void setConectado(boolean b) throws RemoteException;
    public boolean getConectado() throws RemoteException;
}
