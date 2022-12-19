package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    public List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> findByName(String manufacturer);
}
