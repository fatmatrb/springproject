package tn.esprit.springproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.ContratRepository;
import tn.esprit.springproject.Repository.DepartementRepository;
import tn.esprit.springproject.Repository.EquipeRepository;
import tn.esprit.springproject.Repository.EtudiantRepository;
import tn.esprit.springproject.entities.Contrat;
import tn.esprit.springproject.entities.Departement;
import tn.esprit.springproject.entities.Equipe;
import tn.esprit.springproject.entities.Etudiant;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantService implements Iservice<Etudiant> {
    private final EtudiantRepository etudiantRepository;
    DepartementRepository departementRepository;
    ContratRepository contratRepository;
    EquipeRepository equipeRepository;
    private final DepartementService departementService;

    private final ContratService contratService;
    private final EquipeService equipeService;

    @Override
    public Etudiant Create(Etudiant T) {
        return etudiantRepository.save(T);
    }

    @Override
    public List<Etudiant> Read() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant Update(int ID, Etudiant T) {
        return etudiantRepository.findById(ID).map(T2 -> {
            T2.setContrats(T.getContrats());
            T2.setOption(T.getOption());
            T2.setDepartement(T.getDepartement());
            T2.setEquipes(T.getEquipes());
            T2.setPrenomE(T.getPrenomE());
            T2.setNomE(T.getNomE());

            return etudiantRepository.save(T2);
        }).orElseThrow(() -> new RuntimeException("Etudiant non trouvé !"));
    }

    @Override
    public String Delete(int ID) {
        etudiantRepository.deleteById(ID);
        return "Supprimer Etudiant avec succes";
    }

    @Override
    public Etudiant getOne(Integer ID) {
        return etudiantRepository.findById(ID).get();
    }

    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId){
    Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
    Departement departement = departementRepository.findById(departementId).orElse(null);
    etudiant.setDepartement(departement);
        etudiantRepository.save(etudiant);

    }

    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe){

        Contrat contrat = contratRepository.findById(idContrat).orElse(null);
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);
        contrat.setEtudiant(e);
        e.getEquipes().add(equipe);
        return etudiantRepository.save(e);

    }

}
