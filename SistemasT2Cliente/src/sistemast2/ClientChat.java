package sistemast2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ClientChat extends UnicastRemoteObject implements IChat  {
    public Client cliente;
    public IChat conexao = null;
    public JTextArea areaChat;
    public JButton b;
    public boolean conectado = false;
    public JLabel nome2;

    public ClientChat() throws RemoteException{
    }
    public ClientChat(JTextArea areaChat, JButton b, JLabel nome2) throws RemoteException{
        this.areaChat = areaChat;
        this.b = b;
        this.nome2 = nome2;
    }
    
    public boolean getConectado() throws RemoteException{
        return conectado;
    }
    
    public void setConexao(IChat c) throws RemoteException{
        this.conexao = c;
        areaChat.setText(areaChat.getText()+"====== Atendente Encontrado ======\n");
        nome2.setText(conexao.getName()+" "+conexao.getId());
        conectado = true;
        b.setEnabled(true);
    }
    public IChat getConexao() throws RemoteException{
        return this.conexao;
    }
    public void closeConexao() throws RemoteException{
        this.conexao = null;
        b.setEnabled(false);
        areaChat.setText(areaChat.getText()+"===> Atendimento Cancelado Pelo Atendente <===\n");
        conectado = false;
    }
    
    public String getName() throws RemoteException {
        return cliente.getNome();
    }
    public int getId() throws RemoteException {
        return cliente.getId();
    }
    public void setId(int id) throws RemoteException {
        this.cliente.setId(id);
    }
    
    @Override
    public void deliver(IMessage msg) throws RemoteException{
        System.out.println("cliente deliver = "+msg.getMessage());
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy '/' HH:mm:ss");
        areaChat.setText(areaChat.getText()+" "+ft.format(msg.getDate().getTime())+" | "+conexao.getName()+"_"+conexao.getId()+" > "+msg.getMessage()+"\n");
    }
}
