package cz.bisi1.toolsharing.service.impl;

import cz.bisi1.toolsharing.entity.ToolEntity;
import cz.bisi1.toolsharing.repository.ToolRepository;
import cz.bisi1.toolsharing.entity.ToolState;
import cz.bisi1.toolsharing.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository repository;

    @Override
    public List<ToolEntity> findAllTools() {
        return repository.findAll();
    }

    @Override
    public Optional<ToolEntity> findToolById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ToolEntity saveTool(ToolEntity tool) {
        return repository.save(tool);
    }

    @Override
    public ToolEntity updateTool(Long id, ToolEntity tool) {
        ToolEntity toolDB = findToolById(id).get();
        toolDB.set(tool);
        return repository.save(toolDB);
    }

    @Override
    public void deleteTool(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ToolEntity> findAllAvailableTools() {
        return repository.findAllByState(ToolState.FREE);
    }

    @Override
    public List<ToolEntity> findAllAvailableByNameLike(String name) {
        return repository.findAllByNameContainingAndState(name,ToolState.FREE);
    }

}
