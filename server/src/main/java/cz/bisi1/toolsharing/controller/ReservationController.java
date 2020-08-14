package cz.bisi1.toolsharing.controller;

import cz.bisi1.toolsharing.controller.dto.ReservationDto;
import cz.bisi1.toolsharing.entity.ReservationEntity;
import cz.bisi1.toolsharing.entity.ToolEntity;
import cz.bisi1.toolsharing.entity.ToolState;
import cz.bisi1.toolsharing.service.ReservationService;
import cz.bisi1.toolsharing.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <h1>ReservationController</h1>
 * The ReservationController class implements an application that
 * manages reservations in frontend.
 */
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Returns all reservations from database.
     * @return List of ReservationEntity in json format with status 200.
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> getAllReservations() {
        List<ReservationDto> reservations = new ArrayList<>();
        for (ReservationEntity res:reservationService.findAllReservations()) {
            reservations.add(new ReservationDto(res));
        }
        return ResponseEntity.ok(reservations);
    }

    /**
     * Returns all active reservations of user.
     * @param id Id of user.
     * @return List of ReservationEntity in json format with status 200.
     */
    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> getUserReservations(@AuthenticationPrincipal @RequestParam Long id) {
        return ResponseEntity.ok(reservationService.findAllActiveReservationsOfUser(id));
    }

    /**
     * If reservation with that id exists method will return it.
     * @param id of the reservation that method will return.
     * @return ReservationEntity in json format with status 200 or nothing with status 404.
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getReservationById(@PathVariable(value = "id") Long id) {
        Optional<ReservationEntity> reservation = reservationService.findReservationById(id);
        if (!reservation.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ReservationDto res = new ReservationDto(reservation.get());
        return ResponseEntity.ok(res);
    }

    /**
     * Creates new reservation and saves it in the database.
     * Checks if list of tools for reservation not empty, checks if dates are correct and fit in 30 days from today.
     * Sets all the tools in the reservation to TAKEN state and saves it in the database.
     * @param reservation that method will save in database.
     * @return ReservationEntity in json format with status 200.
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationDto reservation) {
        try {
            ReservationEntity dtout = reservationService.saveReservation(reservation);
            return ResponseEntity.ok(dtout);
        } catch (Exception e) {
            System.out.println("Was unable to create reservation = [" + reservation + "] ");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/cancel/{id}", produces = "application/json")
    public ResponseEntity<?> cancelReservation (@PathVariable(value = "id") Long id) {
        Optional<ReservationEntity> reservation=reservationService.findReservationById(id);
        if (!reservation.isPresent()) {
            return  ResponseEntity.notFound().build();
        }

        reservation.get().setActive(false);
        return  ResponseEntity.ok().build();
    }

    /**
     * If reservation with that id do exists changes data in reservation with id in first parameter
     * for data in second parameter.
     * @param id of the reservation that method will update.
     * @param reservation all the new data.
     * @return ReservationEntity in json format with status 200 or nothing with status 404.
     */
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateReservation(@PathVariable(value = "id") Long id, @Valid @RequestBody ReservationEntity reservation) {
        if (!reservationService.findReservationById(id).isPresent()) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    /**
     * If reservation with that id do exists deletes it from database.
     * @param id of the reservation that method will delete.
     * @return nothing with status 200 or nothing with status 404.
     */
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") Long id) {
        if (!reservationService.findReservationById(id).isPresent()) {
            return  ResponseEntity.notFound().build();
        }
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

}


