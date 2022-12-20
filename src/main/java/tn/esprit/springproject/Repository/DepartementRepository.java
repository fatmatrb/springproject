package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springproject.entities.Departement;


public interface DepartementRepository extends JpaRepository <Departement ,Integer>{
}
