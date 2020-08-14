package cz.bisi1.toolsharing.service.impl;

import cz.bisi1.toolsharing.controller.dto.ReservationDto;
import cz.bisi1.toolsharing.entity.ReservationEntity;
import cz.bisi1.toolsharing.entity.ToolEntity;
import cz.bisi1.toolsharing.entity.UserEntity;
import cz.bisi1.toolsharing.repository.ReservationRepository;
import cz.bisi1.toolsharing.repository.ToolRepository;
import cz.bisi1.toolsharing.repository.UserRepository;
import cz.bisi1.toolsharing.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public List<ReservationEntity> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<ReservationEntity> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public ReservationEntity saveReservation(ReservationDto dto) throws Exception {
        ReservationEntity reservation = new ReservationEntity(dto);
        LocalDate today = LocalDate.now();
        if (reservation.getDateOfReturn().equals(reservation.getDateOfReservation())) {
            throw new Exception("Invalid dates");
        }
        if (reservation.getDateOfReservation().before(java.sql.Date.valueOf(today))
                || reservation.getDateOfReturn().before(java.sql.Date.valueOf(today))) {
            throw new Exception("Invalid dates");
        }
        today = today.plusDays(30);
        if (reservation.getDateOfReservation().after(java.sql.Date.valueOf(today))
                || reservation.getDateOfReturn().after(java.sql.Date.valueOf(today))
                || reservation.getDateOfReservation().after(reservation.getDateOfReturn())) {
            throw new Exception("Invalid dates");
        }

        if (!userRepository.findById(dto.getUserId()).isPresent()) {
            throw new Exception("Invalid user");
        }
        Optional<ToolEntity> tool = toolRepository.findById(dto.getToolId());
        if (!tool.isPresent()) {
            throw new Exception("Invalid tool");
        }
        List<ReservationEntity> reservations = reservationRepository.findAllByActiveAndTool(true, tool.get());
        for (ReservationEntity it : reservations) {
            if (isDatesAreCrossing(it, reservation)) {
                throw new Exception("Tool is reserved for this dates");
            }
        }

        reservation.setTool(toolRepository.findById(dto.getToolId()).get());
        reservation.setUser(userRepository.findById(dto.getUserId()).get());
        return reservationRepository.save(reservation);
    }

    @Override
    public ReservationEntity updateReservation(Long id, ReservationEntity reservation) {
        ReservationEntity reservationDB = findReservationById(id).get();
        reservationDB.set(reservation);
        return reservationRepository.save(reservationDB);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationEntity> findAllActiveReservationsOfUser(Long id) {
        return reservationRepository.findAllByUserIdAndActive(id, true);
    }

    @Override
    public List<Boolean> getThirtyDaysOfTool(ToolEntity tool) {
        LocalDate iterator;
        LocalDate dateOfReturn;
        LocalDate dateOfReservation;
        List<ReservationEntity> reservations = reservationRepository.findAllByActiveAndTool(true, tool);
        List<Boolean> month = new ArrayList<>(30);
        int i;
        for (int j = 0; j < 30; j++) {
            month.add(false);
        }
        for (ReservationEntity reservation : reservations) {
            i = 0;
            iterator = LocalDate.now();

            dateOfReservation = Instant.ofEpochMilli(reservation.getDateOfReservation().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            dateOfReturn = Instant.ofEpochMilli(reservation.getDateOfReturn().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();


            while (iterator.isBefore(dateOfReservation)) {
                iterator = iterator.plusDays(1);
                i++;
            }
            while (!dateOfReturn.isBefore(iterator)) {
                iterator = iterator.plusDays(1);
                month.set(i++, true);
            }
        }
        return month;
    }

    /**
     * Checks if two reservation dates are crossing
     * @param res1 the reservation containing the first date
     * @param res2 the reservation containing the second date
     * @return true if dates of two reservations are crossing and return false otherwise
     */
    private Boolean isDatesAreCrossing(ReservationEntity res1, ReservationEntity res2) {
        return      isIntersects(res1, res2.getDateOfReservation()) || isIntersects(res1, res2.getDateOfReturn())
                ||  isIntersects(res2, res1.getDateOfReservation()) || isIntersects(res2, res1.getDateOfReturn());
    }

    /**
     * Checks if date is in a reservation period
     * @param res given reservation
     * @param date The Date that checks if it is in a reservation period or not
     * @return true if date is in a reservation period and return else otherwise
     */
    private Boolean isIntersects(ReservationEntity res, Date date) {
        LocalDate dateOfReservation = Instant.ofEpochMilli(res.getDateOfReservation().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate dateOfReturn = Instant.ofEpochMilli(res.getDateOfReturn().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return !(localDate.isAfter(dateOfReturn) || localDate.isBefore(dateOfReservation));
    }
}
