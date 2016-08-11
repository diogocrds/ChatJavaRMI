package sistemast2;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    public IChat requestAtendent(IChat chat_client) throws RemoteException;
    public void requestJoin(IChat chat_atendent) throws RemoteException;
    public void requestLeave(IChat chat_atendent) throws RemoteException;
    public void freeAtendent(IChat chat_atendent) throws RemoteException;
}
