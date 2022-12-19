package finki.ukim.mk.webapp.service.impl;

import finki.ukim.mk.webapp.model.Manufacturer;
import finki.ukim.mk.webapp.repository.jpa.ManufacturerRepository;
import finki.ukim.mk.webapp.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImp implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImp(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;

    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name,address)));
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);
    }
}
