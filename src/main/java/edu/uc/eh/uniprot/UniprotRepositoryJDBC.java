package edu.uc.eh.uniprot;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by shamsabz on 1/8/19.
 */
//public class UniprotRepositoryJDBC {
//}
public interface UniprotRepositoryJDBC extends CrudRepository<Uniprot, Integer> {

    Uniprot findByName(String name);

}