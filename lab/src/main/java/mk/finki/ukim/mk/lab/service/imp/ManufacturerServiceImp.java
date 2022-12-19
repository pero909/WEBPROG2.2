package mk.finki.ukim.mk.lab.service.imp;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
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
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {

        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByName(String manufacturer) {
        return this.manufacturerRepository.findByName(manufacturer);
    }


}
