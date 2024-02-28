package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Adresse;
import Entities.Client;

public class AdresseRepositories {
    public void insert(Adresse adresse){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir2_iage3"
                    , "root", "");
                    Statement statement = conn.createStatement();
            
            String sql=String.format("INSERT INTO `adresse` `ville`, `quartier`, `numVilla`) "
                      + " VALUES ('"+adresse.getVille()+"' , '"+adresse.getQuartier()+"' , '"+adresse.getNumVilla()+"')");
             int nbreLigne=statement.executeUpdate(sql);
             statement.close();
             conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        }
        catch(SQLException e) {
          System.out.println("Erreur de Connexion a la BD");
    }
        
      }

      public List<Adresse> selectAll(){
         List<Adresse> adresses=new ArrayList<>();
          try {
    
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir2_iage3" 
                   , "root", "");
             Statement statement = conn.createStatement();
             String sql="SELECT * FROM `adresse` a, client cl WHERE a.client_id=cl.id_client;";
             ResultSet rs=statement.executeQuery(sql);
            while (rs.next()) {
               //Une ligne ==> rs de la requete
                Client client=new Client();
                client.setId(rs.getInt("client_id"));
                client.setNomComplet(rs.getString("nomComplet"));
                client.setTelephone(rs.getString("telephone"));
                client.setEmail(rs.getString("email"));
                

                 Adresse ad=new Adresse();
                 ad.setId(rs.getInt("id-adresse"));
                 ad.setVille(rs.getString("ville"));
                 ad.setQuartier(rs.getString("quartier"));
                 ad.setNumVilla(rs.getString("numVilla"));
                 adresses.add(ad);
                
    
            }
            statement.close();
            rs.close();
            conn.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Erreur de chargement de Driver");
       }
       catch (SQLException e) {
         System.out.println("Erreur de Connexion a la BD");
       }
       return  adresses;
      }
}
