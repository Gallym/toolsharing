package cz.bisi1.toolsharing.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.bisi1.toolsharing.controller.dto.ReservationDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <h1>ReservationEntity</h1>
 * The ReservationEntity class represents reservation.
 */
@Entity
@Table(name = "RESERVATION")
public class ReservationEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID", nullable = false)
    private Long id;

    @Column(name = "DATE_OF_RESERVATION", nullable = false)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfReservation;


    @Column(name = "DATE_OF_RETURN", nullable = false)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfReturn;

    @Column(name = "ACTIVE", nullable = true)
    private Boolean active;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOX_ID", nullable = true)
    @JsonBackReference
    private BoxEntity box;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TOOL_ID", nullable = false)
    @JsonBackReference
    private ToolEntity tool;

    public ReservationEntity(ReservationDto dto) {
        this.dateOfReservation = dto.getDateOfReservation();
        this.dateOfReturn = dto.getDateOfReturn();
        this.active = true;
    }

    public ReservationEntity() {active=true;}

    /** Creates a reservation with the specified date  of reservation, date of return, user, boox and list of reserved tools.
     * @param dateOfReservation The reservation's date of start.
     * @param dateOfReturn The reservation's date of return.
     * @param user The instance of UserEntity class created this reservation.
     * @param box The instance of BoxEntity class used in this reservation.
     * @param tool reserved tool
     */
    public ReservationEntity(Date dateOfReservation, Date dateOfReturn, UserEntity user, BoxEntity box, ToolEntity tool) {
        this.dateOfReservation = dateOfReservation;
        this.dateOfReturn = dateOfReturn;
        this.user = user;
        this.box = box;
        this.tool = tool;
        this.active = true;
    }

    /** Creates a reservation with the specified date  of reservation, date of return, state(active or not), user, boox and list of reserved tools.
     * @param dateOfReservation The reservation's date of start.
     * @param dateOfReturn The reservation's date of return.
     * @param active The boolean represents if reservation is active or not
     * @param user The instance of UserEntity class created this reservation.
     * @param box The instance of BoxEntity class used in this reservation.
     * @param tool reserved tool
     */
    public ReservationEntity(Date dateOfReservation, Date dateOfReturn, Boolean active, UserEntity user, BoxEntity box, ToolEntity tool) {
        this.dateOfReservation = dateOfReservation;
        this.dateOfReturn = dateOfReturn;
        this.active = active;
        this.user = user;
        this.box = box;
        this.tool = tool;
    }

    /** Sets the reservation's data.
     * @param reservation The instance of ReservationEntity  class containing the reservation's
     *     data.
     */
    public void set(ReservationEntity reservation) {
        if(reservation.dateOfReservation!=null) this.dateOfReservation = reservation.dateOfReservation;
        if(reservation.dateOfReturn!=null) this.dateOfReturn = reservation.dateOfReturn;
        if(reservation.active!=null) this.active = reservation.active;
        if(reservation.user!=null) this.user = reservation.user;
        if(reservation.box!=null) this.box = reservation.box;
        if(reservation.tool!=null) this.tool = reservation.tool;
    }

    /** Gets the reservation's id.
     * @return A long number representing the reservation's id.
     */
    public Long getId() {
        return id;
    }

    /** Sets the reservation's id.
     * @param id The number containing the reservation's
     *     id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** Gets the date when reservation was created.
     * @return A Date representing the reservation's start date .
     */
    public Date getDateOfReservation() {
        return dateOfReservation;
    }

    /** Sets the date when reservation was created
     * @param dateOfReservation The Date containing the date of reservation creation.
     */
    public void setDateOfReservation(Date dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    /** Gets the date of return resrved tools.
     * @return A Date representing the reservation's return date .
     */
    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    /** Sets the reservation's return date.
     * @param dateOfReturn The Date containing the reservation's
     *     return date.
     */
    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    /**
     * Gets the user who created the reservation
     * @return A UserEntity representing the user who created the reservaion
     */
    @JsonBackReference
    public UserEntity getUser() {
        return user;
    }

    /** Sets the user who created the reservation
     * @param user The UserEntity containing the user who created the reservation.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Gets the box is in the reservation
     * @return A BooxEntity representing the box is in the reservaion
     */
    @JsonBackReference
    public BoxEntity getBox() {
        return box;
    }

    /** Sets the box for the reservation
     * @param box The BoxEntity containing the box for the reservation.
     */
    public void setBox(BoxEntity box) {
        this.box = box;
    }

    /**
     * Gets the list of tools was reserved in  the reservation
     * @return A list of ToolEntity representing tools war reserved in the reservation
     */
    @JsonBackReference
    public ToolEntity getTool() {
        return tool;
    }

    /** Sets tools for the reservation
     * @param tool The list of ToolEntity containing tools for the reservation.
     */
    public void setTool(ToolEntity tool) {
        this.tool = tool;
    }

    /**
     * Gets the state of the reservation
     * @return true if reservation is active. Otherwise return false.
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the state of the reservation
     * @param active represents the current state of the reservation
     */
    public void setActive(Boolean active) {
        this.active = active;
    }
}
