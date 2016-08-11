package sistemast2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Jéssica
 */
public class AtendentChat extends UnicastRemoteObject implements IChat  {
    public Atendent atendente;
    public IChat conexao;
    public JTextArea areaChat;
    public JButton b;
    public JButton e;
    public boolean conectado = false;
    public JLabel nome2;

    public AtendentChat(JTextArea areaChat, IChat conexao, JButton b, JButton e, JLabel nome2) throws RemoteException {
        this.areaChat = areaChat;
        this.conexao = conexao;
        this.b = b;
        this.e = e;
        this.nome2 = nome2;
    }
    
    public IChat getConexao() throws RemoteException{
        return this.conexao;
    }
    @Override
    public void setConexao(IChat c) throws RemoteException{
        this.conexao = c;
        nome2.setText(conexao.getName());
        areaChat.setText(areaChat.getText()+"====== Cliente Encontrado ======\n");
        nome2.setText(conexao.getName()+" "+conexao.getId());
        conectado = true;
        b.setEnabled(true);
        e.setEnabled(true);
    }
    @Override
    public void closeConexao() throws RemoteException{
        nome2.setText("");
        this.conexao = null;
        areaChat.setText(areaChat.getText()+"===> Atendimento Encerrado Pelo Cliente <===\n");
        conectado = false;
        b.setEnabled(false);
        e.setEnabled(false);
    }
    
    public String getName() throws RemoteException {
        return atendente.getAtendentName();
    }
    public int getId() throws RemoteException {
        return atendente.getId();
    }
    public void setId(int id) throws RemoteException {
        this.atendente.setId(id);
    }
    
    public boolean getConectado() throws RemoteException{
        return conectado;
    }
    
    public void setConectado(boolean b) throws RemoteException{
        this.conectado = b;
    }
    @Override
    public void deliver(IMessage msg) throws RemoteException{
        System.out.println("atendente deliver = "+msg.getMessage());
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy '/' HH:mm:ss");
        areaChat.setText(areaChat.getText()+" "+ft.format(msg.getDate().getTime())+" | "+conexao.getName()+"_"+conexao.getId()+" > "+msg.getMessage()+"\n");
    }
}
