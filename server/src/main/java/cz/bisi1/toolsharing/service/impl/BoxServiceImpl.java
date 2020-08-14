package cz.bisi1.toolsharing.service.impl;

import cz.bisi1.toolsharing.entity.BoxEntity;
import cz.bisi1.toolsharing.repository.BoxRepository;
import cz.bisi1.toolsharing.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoxServiceImpl implements BoxService {
    @Autowired
    private BoxRepository repository;

    @Override
    public List<BoxEntity> findAllBoxes() {
        return repository.findAll();
    }

    @Override
    public Optional<BoxEntity> findBoxById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BoxEntity saveBox(BoxEntity box) {
        return repository.save(box);
    }

    @Override
    public BoxEntity updateBox(Long id, BoxEntity box) {
        BoxEntity boxDB = findBoxById(id).get();
        boxDB.set(box);
        return repository.save(boxDB);
    }

    @Override
    public void deleteBox(Long id) {
        repository.deleteById(id);
    }
}
