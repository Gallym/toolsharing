package cz.bisi1.toolsharing.service;

import cz.bisi1.toolsharing.entity.BoxEntity;

import java.util.List;
import java.util.Optional;

public interface BoxService {
    /**
     * Finds all boxes are in the system
     * @return list of all boxes
     */
    List<BoxEntity> findAllBoxes();

    /**
     * Finds box by id
     * @param id the identifier, a number which uniquely identifies a box
     * @return If box with this id was found, return Optional with non-null value. Then value will be box object.
     * Otherwise return empty Optional object.
     */
    Optional<BoxEntity> findBoxById(Long id);

    /**
     * Tries to save new BoxEntity object. If it is already exists, update it,
     * otherwise add new BoxEntity object to the system
     * @param box is object to save
     * @return saved BoxEntity object
     */
    BoxEntity saveBox(BoxEntity box);

    /**
     * Finds the box object identifies by id and update it's data
     * @param id the identifier, a number which uniquely identifies a box should be updated
     * @param box is object to change to
     * @return updated BoxEntity
     */
    BoxEntity updateBox(Long id, BoxEntity box);

    /**
     * Tries to find box by id and delete it from the system
     * @param id the identifier, a number that uniquely identifies a box.
     */
    void deleteBox(Long id);
}
