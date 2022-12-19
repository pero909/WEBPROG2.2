package finki.ukim.mk.webapp.repository.jpa;

import finki.ukim.mk.webapp.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {

}
