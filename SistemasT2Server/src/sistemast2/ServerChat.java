package sistemast2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diogo
 */
public class ServerChat extends UnicastRemoteObject implements IServer {
    public ArrayList<IChat> listaAtLivres = new ArrayList<>();
    public ArrayList<IChat> listaAtOcupados = new ArrayList<>();
    public ArrayList<IChat> listaClientesEspera = new ArrayList<>();
    int idAt = 0;
    int idC = 0;
    public JTable listaAtLivresJT;
    public JTable listaAtOcupadosJT;
    public JTable listaClientesEsperaJT;

    public ServerChat(JTable listaAtLivresJT, JTable listaAtOcupadosJT, JTable listaClientesEsperaJT) throws RemoteException {
        this.listaAtLivresJT = listaAtLivresJT;
        this.listaAtOcupadosJT = listaAtOcupadosJT;
        this.listaClientesEsperaJT = listaClientesEsperaJT;
    }

    @Override
    public void zeraListas(int flag) throws RemoteException{
        DefaultTableModel tableModel;
        tableModel = new DefaultTableModel() {
            public boolean istCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");

        int tamanho = 0;
        if(flag == 0)
            tamanho = listaAtLivres.size();
        else if(flag == 1)
            tamanho = listaAtOcupados.size();
        else if(flag == 2)
            tamanho = listaClientesEspera.size();
        
        int i;
        for (i = 0; i < tamanho; i++) {
            tableModel.addRow(new Object[]{null, null});
        }
        
        if(flag == 0) {
            System.out.println("lista atendente livre");
            listaAtLivresJT.setModel(tableModel);
            System.out.println("tamanho" + tamanho);
            for(i = 0; i < listaAtLivres.size(); i++){    
                listaAtLivresJT.setValueAt(listaAtLivres.get(i).getId(), i, 0);
                listaAtLivresJT.setValueAt(listaAtLivres.get(i).getName(), i, 1);                  
            }
        } else if(flag == 1) {
            listaAtOcupadosJT.setModel(tableModel);
            System.out.println("lista atendente ocupado");
            for(i = 0; i < listaAtOcupados.size(); i++){
                listaAtOcupadosJT.setValueAt(listaAtOcupados.get(i).getId(), i, 0);
                listaAtOcupadosJT.setValueAt(listaAtOcupados.get(i).getName(), i, 1);                  
            }
        } else if(flag == 2) {
            listaClientesEsperaJT.setModel(tableModel);
            System.out.println("lista cliente espera");
            for(i = 0; i < listaClientesEspera.size(); i++){
                listaClientesEsperaJT.setValueAt(listaClientesEspera.get(i).getId(), i, 0);
                listaClientesEsperaJT.setValueAt(listaClientesEspera.get(i).getName(), i, 1);                  
            }
        }
    }
    @Override
    public IChat requestAtendent(IChat chat_client) throws RemoteException{
        System.out.println("==== Chegou chamada [requestAtendent] ====");
        chat_client.setId(idC);
        idC++;
        if(listaAtLivres.size() > 0){
            listaAtOcupados.add(listaAtLivres.remove(0));
            System.out.println("> add lista ocupados: "+listaAtOcupados.get(listaAtOcupados.size()-1).getName());
            System.out.println("==== Fim chamada [requestAtendent] ====");
            zeraListas(0); // At livre
            zeraListas(1); // At ocupado
            return listaAtOcupados.get(listaAtOcupados.size()-1); // aqui mudei
        }
        listaClientesEspera.add(chat_client);
        zeraListas(2); // Add lista Cl Espera
        return null;
    }

    public void verificaListaEspera() throws RemoteException{
        if(listaClientesEspera.size() > 0){
            IChat at = listaAtLivres.remove(0);
            listaAtOcupados.add(at);
            IChat cl = listaClientesEspera.remove(0);
            zeraListas(0); // At livre
            zeraListas(1); // At ocupado
            zeraListas(2); // Cl espera
            at.setConexao(cl);
            cl.setConexao(at);
        }
    }

    @Override
    public void requestJoin(IChat chat_atendent) throws RemoteException{
        System.out.println("==== Chegou chamada [requestJoin] ====");

        listaAtLivres.add(chat_atendent);
        chat_atendent.setId(idAt);
        idAt++;
        System.out.println("> add lista: "+chat_atendent.getName());
        
        System.out.println("==== Fim chamada [requestJoin] ====");
        zeraListas(0); // At livre
        verificaListaEspera();
    }

    @Override
    public void requestLeave(IChat chat_atendent) throws RemoteException{
        listaAtOcupados.remove(chat_atendent);
        listaAtLivres.remove(chat_atendent);
        if(chat_atendent.getConectado()){
            IChat cl = chat_atendent.getConexao();
            cl.closeConexao();
            chat_atendent.closeConexao();
        }
        zeraListas(0); // At livre
        zeraListas(1); // At Ocupado
    }

    @Override
    public void freeAtendent(IChat chat_atendent) throws RemoteException{
        listaAtOcupados.remove(chat_atendent);
        listaAtLivres.add(chat_atendent);
        System.out.println("Atendente livre: "+chat_atendent.getName());
        chat_atendent.setConectado(false);
        zeraListas(0); // At livre
        zeraListas(1); // At ocupado
        verificaListaEspera();
    }

}
