package cz.bisi1.toolsharing.service;

import cz.bisi1.toolsharing.controller.dto.ReservationDto;
import cz.bisi1.toolsharing.entity.ReservationEntity;
import cz.bisi1.toolsharing.entity.ToolEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    /**
     * Finds all existing reservations.
     * @return list of all reservations
     */
    List<ReservationEntity> findAllReservations();

    /**
     * Finds reservation by id
     * @param id the identifier, a number which uniquely identifies a reservation
     * @return If reservation with this id was found, return Optional with non-null value. Then value will be ReservationEntity object.
     * Otherwise return empty Optional object.
     */
    Optional<ReservationEntity> findReservationById(Long id);

    /**
     * Tries to save new ReservationEntity object. If it is already exists, update it, otherwise add new ReservationEntity object
     * @param reservation is object to save.
     * @return saved ReservationEntity object.
     * @throws Exception if parameters of reservation are invalid.
     */
    ReservationEntity saveReservation(ReservationDto reservation) throws Exception;

    /**
     * Finds the ReservationEntity object identifies by id and update it's data
     * @param id the identifier, a number which uniquely identifies a ReservationEntity should be updated
     * @param reservation is object to change to
     * @return updated ReservationEntity object
     */
    ReservationEntity updateReservation(Long id, ReservationEntity reservation);

    /**
     * Tries to find reservation by id and delete it
     * @param id the identifier, a number that uniquely identifies a reservation.
     */
    void deleteReservation(Long id);

    /**
     * Finds all active reservations of one user
     * @param id  the identifier, a number which uniquely identifies a user
     * @return list of all user's active reservations
     */
    List<ReservationEntity> findAllActiveReservationsOfUser(Long id);

    /**
     * Finds all active reservations of that tool.
     * @param tool
     * @return list of ReservationEntity.
     */
    /**
     * Gets tool availability for thirty days
     * @param tool is object to get availability for thirty days
     * @return List containing 30 booleans. Each element represents availability for one day.
     */
    List<Boolean> getThirtyDaysOfTool(ToolEntity tool);
}
