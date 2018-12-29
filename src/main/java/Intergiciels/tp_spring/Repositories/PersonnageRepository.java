package Intergiciels.tp_spring.Repositories;

import Intergiciels.tp_spring.Entities.Personnage;
import Intergiciels.tp_spring.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PersonnageRepository extends CrudRepository<Personnage, Integer> {

}