package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface BaloonService {
    List<Balloon> listAll();
    Optional<Balloon> searchByNameOrDescription(String text);
    Optional<Balloon> searchByNameAndDescription(String name ,String desc);
    Optional<Balloon> findById(Long id);
    Optional<Balloon> save(Balloon balloon);
    void deleteById(Long id);
}
