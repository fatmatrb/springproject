package tn.esprit.springproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.DepartementRepository;
import tn.esprit.springproject.Repository.UniversiteRepository;
import tn.esprit.springproject.entities.Departement;
import tn.esprit.springproject.entities.Universite;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteService implements Iservice<Universite> {
    private final UniversiteRepository universiteRepository;
    DepartementRepository departementRepository;

    private final  DepartementService departementService;

    @Override
    public Universite Create(Universite T) {
        return universiteRepository.save(T);
    }

    @Override
    public List<Universite> Read() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite Update(int ID, Universite T) {
        return universiteRepository.findById(ID).map(
                T2->{
                    T2.setDepartements(T.getDepartements());
                    T2.setNomUniv(T.getNomUniv());
                    return universiteRepository.save(T2);
                }
        ).orElseThrow(() -> new RuntimeException("Universite non trouvé !"));

    }

    @Override
    public String Delete(int ID) {

        universiteRepository.deleteById(ID);

        return "Supprimer Universite avec succes";
    }

    @Override
    public Universite getOne(Integer ID) {
        return universiteRepository.findById(ID).get();
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement){
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        Departement departement = departementRepository.findById(idDepartement).orElse(null);
        universite.getDepartements().add(departement);
        universiteRepository.save(universite);
    }
}
