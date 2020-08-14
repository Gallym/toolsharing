package cz.bisi1.toolsharing.service;


import cz.bisi1.toolsharing.entity.ToolEntity;

import java.util.List;
import java.util.Optional;

public interface ToolService {

    /**
     * Finds all tools.
     * @return list of all tools are in the system
     */
    List<ToolEntity> findAllTools();

    /**
     * Finds tool by id
     * @param id the identifier, a number which uniquely identifies a tool
     * @return If tool with this id was found, return Optional with non-null value. Then value will be ToolEntity object.
     * Otherwise return empty Optional object.
     */
    Optional<ToolEntity> findToolById(Long id);

    /**
     * Tries to save new ToolEntity object. If it is already exists, update it,
     * otherwise add new ToolEntity object to the system
     * @param tool is object to save
     * @return saved ToolEntity object
     */
    ToolEntity saveTool(ToolEntity tool);

    /**
     * Finds the ToolEntity object identifies by id and updates it's data
     * @param id the identifier, a number which uniquely identifies a ToolEntity object should be updated
     * @param tool is object to change to
     * @return updated ToolEntity
     */
    ToolEntity updateTool(Long id, ToolEntity tool);

    /**
     * Tries to find ToolEntity object by id and delete it
     * @param id the identifier, a number that uniquely identifies a tool.
     */
    void deleteTool(Long id);

    /**
     * Finds all tools could be borrowed currently
     * @return list of all free ToolEntity objects
     */
    List<ToolEntity> findAllAvailableTools();

    /**
     * Finds all free tools are called name
     * @param name is string to find tools
     * @return list of all tools are called name
     */
    List<ToolEntity> findAllAvailableByNameLike(String name);
}
