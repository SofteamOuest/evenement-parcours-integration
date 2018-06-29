package fr.softeam.evenementparcoursintegration.ressource;


import fr.softeam.evenementparcoursintegration.dto.EvenementGenerique;
import fr.softeam.evenementparcoursintegration.dto.EvenementRappel;
import fr.softeam.evenementparcoursintegration.dto.Personne;
import fr.softeam.evenementparcoursintegration.exception.EvenementParcoursIntegrationException;
import fr.softeam.evenementparcoursintegration.service.EvenementParcoursIntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EvenementParcoursIntegrationRessource {

    private final EvenementParcoursIntegrationService evenementParcoursIntegrationService;

    public EvenementParcoursIntegrationRessource(EvenementParcoursIntegrationService evenementParcoursIntegrationService){
        this.evenementParcoursIntegrationService = evenementParcoursIntegrationService;
    }

    @PostMapping(path = "/evenement")
    @ResponseStatus(HttpStatus.CREATED)
    public List<EvenementGenerique> creationEvenementsParcoursIntegration(@RequestBody @Valid Personne personne) throws EvenementParcoursIntegrationException {
        return evenementParcoursIntegrationService.creationEvenementParcoursIntegration(personne.getIdPersonne(),personne.getDateArrivee(), personne.getDateVisiteMedicale());
    }

    @GetMapping(path = "/evenement")
    public Map<String, List<EvenementGenerique>> getEvenementsTypeParcoursIntegration() throws EvenementParcoursIntegrationException {
        Map<String, List<EvenementGenerique>> evenementGeneriqueMap= new HashMap<>();
        evenementGeneriqueMap = evenementParcoursIntegrationService.recuperationEvenementsParcoursIntegrationPersonne();
        return evenementGeneriqueMap;
    }

    @GetMapping(path = "/evenement/rappel")
    public List<EvenementRappel> getEvenementARappele() throws EvenementParcoursIntegrationException {
        return evenementParcoursIntegrationService.getEvenementsARappele();
    }

    @GetMapping(path = "/evenement/personne/{idPersonne}")
    public List<EvenementGenerique> getEvenementByIdPersonne(@PathVariable String idPersonne) throws EvenementParcoursIntegrationException {
        return evenementParcoursIntegrationService.getListEvenementByIdPersonne(idPersonne);
    }

    @PutMapping(path = "/evenement/{idEvenement}")
    public EvenementGenerique modifierEvenementGenerique(@PathVariable Integer idEvenement,
                                                          @RequestBody @Valid EvenementGenerique evenementGenerique) throws EvenementParcoursIntegrationException {
        return evenementParcoursIntegrationService.modifierEvenement(idEvenement,evenementGenerique);
    }

    @DeleteMapping(path = "/evenement/{idEvenement}")
    public void supprimerEvenementGenerique(@PathVariable Integer idEvenement) throws EvenementParcoursIntegrationException {
        evenementParcoursIntegrationService.supprimerEvenement(idEvenement);
    }
}
