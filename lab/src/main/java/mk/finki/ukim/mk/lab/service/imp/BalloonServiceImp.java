package mk.finki.ukim.mk.lab.service.imp;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BaloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImp implements BaloonService {
    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImp(BalloonRepository balloonRepository,
                             ManufacturerRepository manufacturerRepository) {

        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    public Optional<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Optional<Balloon> searchByNameAndDescription(String name, String desc) {
        return balloonRepository.findAllByNameAndDescription(name,desc);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }



    @Override
    public Optional<Balloon> save(Balloon balloon) {
        Long manufacturerId=balloon.getManufacturer().getId();
        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));


        return Optional.of(this.balloonRepository.save(balloon));
    }





    @Override
    public void deleteById(Long id) {
       this.balloonRepository.deleteById(id);
    }
}
