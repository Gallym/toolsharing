package cz.bisi1.toolsharing.repository;

import cz.bisi1.toolsharing.entity.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * <h1>BoxRepository</h1>
 * The BoxRepository interface implements an application that
 * manages boxes in database.
 */
@Repository
public interface BoxRepository extends JpaRepository<BoxEntity, Long> {

}
