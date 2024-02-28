package Repositories;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Client;

public class ClientRepositories {
    public void insert(Client client){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir2_iage3"
                    , "root", "");
                    Statement statement = conn.createStatement();
            
            String sql=String.format("INSERT INTO `client` (`nomComplet`, `telephone`, `email`) "
                      + " VALUES ('"+client.getNomComplet()+"' , '"+client.getTelephone()+"' , '"+client.getEmail()+"')",
                      client.getNomComplet(),client.getTelephone(),client.getEmail());
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

    public List<Client> selectAll(){
         List<Client> clients=new ArrayList<>();
        try {
    
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir2_iage3" 
                   , "root", "");
           Statement statement = conn.createStatement();
           String sql="Select * from client";
           ResultSet rs=statement.executeQuery(sql);
            while (rs.next()) {
               //Une ligne ==> rs de la requete
                Client client=new Client();
                client.setId(rs.getInt("id_client"));
                client.setNomComplet(rs.getString("nomComplet"));
                client.setTelephone(rs.getString("telephone"));
                client.setEmail(rs.getString("email"));
                clients.add(client);
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
       return clients;
      }
      public Client selectClientByTel(String telephone){
        Client client=null;
        try {
    
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devoir2_iage3" 
                   , "root", "");
           Statement statement = conn.createStatement();
           String sql=String.format("Select * from client where telephone like '%s' ",telephone);
           ResultSet rs=statement.executeQuery(sql);
            if (rs.next()) {
               //Une ligne ==> rs de la requete
                client=new Client();
                client.setId(rs.getInt("id_client"));
                client.setNomComplet(rs.getString("nomComplet"));
                client.setTelephone(rs.getString("telephone"));
                client.setEmail(rs.getString("email"));
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
           return client;
      }
}
