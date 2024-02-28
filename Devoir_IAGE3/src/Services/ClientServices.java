package Services;

import Entities.Client;
import Repositories.ClientRepositories;
import java.util.List;

public class ClientServices {
    ClientRepositories clientRepositories=new ClientRepositories();
   
    public  List<Client>listerClient(){
     
         return clientRepositories.selectAll();
     }
     public void ajouterClient(Client client){
         clientRepositories.insert(client);
     }
 
     public  Client rechercherClientParTel(String telephone){
         return clientRepositories.selectClientByTel(telephone);
     } 
}
