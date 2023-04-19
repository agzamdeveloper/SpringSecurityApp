package uzb.asadullaev.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzb.asadullaev.FirstSecurityApp.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository <Person, Integer> {
    Optional<Person> findByUsername(String username);
}
