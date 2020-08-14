package cz.bisi1.toolsharing.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * <h1>BoxEntity</h1>
 * The BoxEntity class represents box.
 */
@Entity
@Table(name = "BOX")
public class BoxEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOX_ID", nullable = false)
    private Long id;
    @Column(name = "NUMBER", nullable = false)
    private Long number;
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOX_ID", nullable = true)
    @JsonManagedReference
    private List<ReservationEntity> reservations;

    public BoxEntity() {
    }

    /** Creates a box with the specified number, address and list of reservations with this box.
     * @param number The box'es number.
     * @param address The box'es location.
     * @param reservations The list of reservations with this box.
     */
    public BoxEntity(Long number, String address, List<ReservationEntity> reservations) {
        this.number = number;
        this.address = address;
        this.reservations = reservations;
    }

    /** Sets the box'es data.
     * @param box The instance of BoxEntity  class containing the box'es
     *     data.
     */
    public void set(BoxEntity box) {
        if(box.number!=null) this.number = box.number;
        if(box.address!=null) this.address = box.address;
        if(box.reservations!=null) this.reservations = box.reservations;
    }

    /** Gets the box'es id.
     * @return A long number representing the box'es id.
     */
    public Long getId() {
        return id;
    }

    /** Gets the box'es number.
     * @return A long number representing the box'es number.
     */
    public Long getNumber() {
        return number;
    }

    /** Sets the box'es number.
     * @param number The number containing the box'es
     *     number.
     */
    public void setNumber(Long number) {
        this.number = number;
    }


    /** Gets the box'es address.
     * @return A string representing the box'es address.
     */
    public String getAddress() {
        return address;
    }

    /** Sets the box'es address.
     * @param address The string containing the box'es
     *     location address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Gets box'es reservations.
     * @return A list of ReservationEntity representing the box'es reservations.
     */

    @JsonManagedReference
    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    /** Sets the box'es reservations.
     * @param reservations The list of ReservationEntity containing the box'es
     *     reservations.
     */
    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
