package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.springproject.entities.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {


    @Query("select e from Etudiant e where e.nomE =?1 and e.prenomE =?2")
    Etudiant retrieveEtudiantNomEtPrenom(String nom, String prenom);

    Etudiant findByNomEAndPrenomE(String nom, String prenom);

    List<Etudiant> findByDepartementNomDepart(String nom);


}
