import java.util.List;
import java.util.Scanner;

import Entities.Client;
import Entities.Adresse;
import Services.ClientServices;
import Services.AdresseServices;

public class App {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        //Dependances
        ClientServices clientService=new ClientServices();
        AdresseServices adresseService=new AdresseServices();
       
        do {
            System.out.println("1-Créer un Client");
            System.out.println("2-Lister les Clients"); 
            System.out.println("3-Ajouter une Addresse et lui associer un Client"); 
            System.out.println("4-Lister les Adresses en affichant le nom du Client");
            System.out.println("5-Quitter"); 
             choix=sc.nextInt();
             sc.nextLine();
            switch (choix) {
                case 1:
                     System.out.println("Entrer NomComplet");
                     String nomComplet=sc.nextLine(); 
                     System.out.println("Entrer un Telephone");
                     String telephone=sc.nextLine();  
                     System.out.println("Entrer un mail");
                     String email=sc.nextLine();   
                     Client cli=new Client();
                      cli.setNomComplet(nomComplet);
                      cli.setTelephone(telephone);
                      cli.setEmail(email);
                    clientService.ajouterClient(cli);
                    break;
                
                case 2:
                    List<Client> clients=  clientService.listerClient();
                     for (Client client: clients) {
                          System.out.println("NomComplet "+ client.getNomComplet());
                          System.out.println("Telephone "+ client.getTelephone());
                          System.out.println("Emmail "+ client.getEmail());
                          System.out.println("=================================");
                     }
                    break;

                case 3:
                System.out.println("Entrer une ville ");
                String ville=sc.nextLine(); 
                System.out.println("Entrer une quartier");
                String quartier=sc.nextLine();
                System.out.println("Entrer le numero de la villa"); 
                String numVilla=sc.nextLine();
                System.out.println("Entrer le Telephone du client");
                 telephone=sc.nextLine(); 
                 //Rechercher un client a travers son tel(Use Case)
                  Client client = clientService.rechercherClientParTel(telephone);
                    if (client==null) {
                        System.out.println("Entrer NomComplet");
                        String nom=sc.nextLine(); 
                        System.out.println("Entrer un Telephone");
                        String tel=sc.nextLine();  
                        System.out.println("Entrer un mail");
                        String mail=sc.nextLine();
                        Client clie=new Client();
                      clie.setNomComplet(nom);
                      clie.setTelephone(tel);
                      clie.setEmail(mail);
                     clientService.ajouterClient(clie);
                    }
                    Adresse ad=new Adresse();
                    ad.setVille(ville);
                    ad.setQuartier(quartier);;
                    ad.setNumVilla(numVilla);;
                    adresseService.ajouterAdresse(ad);
                    break;

                    case 4:
                    List<Adresse> adresses= adresseService.listerAdresse();
                    for (Adresse adr: adresses) {
                         System.out.println("Ville :"+ adr.getVille());
                         System.out.println("Quartier :"+ adr.getQuartier());
                         System.out.println("NumVilla :"+ adr.getNumVilla());
                         System.out.println("Client :"+ adr.getClient().getNomComplet()+" ");
                         System.out.println("====================================================================");
                    }
                   break;
                default:
                    break;
            }

        } while (choix!=5);
        
    
    }
}
