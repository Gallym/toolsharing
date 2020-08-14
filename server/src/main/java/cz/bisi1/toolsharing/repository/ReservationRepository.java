package cz.bisi1.toolsharing.repository;

import cz.bisi1.toolsharing.entity.ReservationEntity;
import cz.bisi1.toolsharing.entity.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <h1>ReservationRepository</h1>
 * The ReservationRepository interface implements an application that
 * manages reservations in database.
 */
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    /**
     * Gets all reservations specified by user and state
     * @param id The number represents user's id
     * @param active The boolean contains true if we try to find active reservation and contains false otherwise
     * @return List of ReservationEntity contains all found reservations
     */
    List<ReservationEntity> findAllByUserIdAndActive(Long id, boolean active);

    /**
     * Gets all reservations specified by state and tool
     * @param active The boolean represents reservations state
     * @param tool that must be reserved in reservations
     * @return List of ReservationEntity are "active" and are containing tool if reserved tools
     */
    List<ReservationEntity> findAllByActiveAndTool(Boolean active, ToolEntity tool);
}
