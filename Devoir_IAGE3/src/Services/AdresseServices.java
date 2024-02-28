package Services;
import java.util.List;

import Entities.Adresse;
import Repositories.AdresseRepositories;

public class AdresseServices {
    AdresseRepositories adresseRepositories=new AdresseRepositories();

    public void ajouterAdresse(Adresse adresse){
        adresseRepositories.insert(adresse);
    }

    public List<Adresse> listerAdresse(){
        return  adresseRepositories.selectAll();
    }
}
