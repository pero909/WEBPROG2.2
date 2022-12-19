package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonRepository  extends JpaRepository<Balloon,Long> {
    Optional<Balloon> findAllByNameAndDescription(String name,String desc);
    @Query(value = "select b from Balloon b where b.description=:text or b.name=:text")
    Optional<Balloon> findAllByNameOrDescription(String text);
    void deleteByName(String name);
    List<Balloon> findAllByNameOrDescription(String name, String description);


}
