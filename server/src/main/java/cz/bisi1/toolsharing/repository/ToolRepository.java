package cz.bisi1.toolsharing.repository;

import cz.bisi1.toolsharing.entity.ToolEntity;
import cz.bisi1.toolsharing.entity.ToolState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h1>ToolRepository</h1>
 * The ToolRepository interface implements an application that
 * manages tool in database.
 */
@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, Long> {

    /**
     * Gets all tools specified by substring of name and state
     * @param name The string containing substring of tool's name
     * @param state The ToolState containing current tool's state
     * @return List of ToolEntity containing all found tools
     */
    List<ToolEntity> findAllByNameContainingAndState(String name, ToolState state);

    /**
     * Gets all tools specified by
     * @param state The ToolState containing current tool's state
     * @return List of ToolEntity containing all found tools
     */
    List<ToolEntity> findAllByState(ToolState state);
}
