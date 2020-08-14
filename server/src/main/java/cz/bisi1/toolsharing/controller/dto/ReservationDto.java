package cz.bisi1.toolsharing.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.bisi1.toolsharing.entity.ReservationEntity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <h1>ReservationDto</h1>
 * The ReservationDto class implements an application that
 * contains basic information about reservation.
 */
public class ReservationDto {
    @JsonFormat(pattern="dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfReservation;


    @JsonFormat(pattern="dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfReturn;

    private Long userId;

    private Long toolId;

    private String toolName;


    public ReservationDto(Date dateOfReservation, Date dateOfReturn, Boolean active, Long userId, Long toolIds) {
        this.dateOfReservation = dateOfReservation;
        this.dateOfReturn = dateOfReturn;
        this.userId = userId;
        this.toolId = toolIds;
    }

    public ReservationDto(ReservationEntity reservation) {
        this.dateOfReservation = reservation.getDateOfReservation();
        this.dateOfReturn = reservation.getDateOfReturn();
        this.userId = reservation.getId();
        this.toolId = reservation.getTool().getId();
        this.toolName = reservation.getTool().getName();
    }

    public Date getDateOfReservation() {
        return dateOfReservation;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public void setDateOfReservation(Date dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }
}
