package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBalloonRepository {
    public List<Balloon> findAllBallons(){
        return DataHolder.balloonList;
    }
    public Optional<Balloon> findAllByNameOrDescription(String text){
        return DataHolder.balloonList.stream().filter(balloon ->
                balloon.getName().equals(text)
                        || balloon.getDescription().equals(text))
                .findFirst();
    }
    public Optional<Balloon> findAllByNameAndDescription(String name,String desc){
        return DataHolder.balloonList.stream().filter(balloon ->
                        balloon.getName().equals(name)
                                && balloon.getDescription().equals(desc))
                .findFirst();
    }
    public Optional<Balloon> findById(Long id){
        return DataHolder.balloonList
                .stream()
                .filter(balloon -> balloon.getId().equals(id))
                .findFirst();
    }
    public Optional<Balloon> save(String name, Long id,String description, Manufacturer manufacturer){
        DataHolder.balloonList.removeIf(i->i.getId().equals(id));
        Balloon balloon=new Balloon(name,description,manufacturer);
        DataHolder.balloonList.add(balloon);
        return Optional.of(balloon);
    }
    public void deleteById(Long id){
        DataHolder.balloonList.removeIf(i->i.getId().equals(id));
    }
}
