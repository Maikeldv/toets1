package be.thomasmore.party.repositories;


import be.thomasmore.party.model.Collaborator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CollaboratorRepository extends CrudRepository<Collaborator, Integer>{


}
