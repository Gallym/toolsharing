package cz.bisi1.toolsharing.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * <h1>ToolEntity</h1>
 * The ToolEntity class represents tool.
 */
@Entity
@Table(name = "TOOL")
public class ToolEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "TOOL_ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(length = 8, nullable = false, name = "STATE")
    private ToolState state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="RESERVATION_ID", nullable = true)
    @JsonManagedReference
    private List<ReservationEntity> reservations;

    public ToolEntity() {
        state=ToolState.FREE;
    }


    /** Creates a tool with the specified name, rent prace and list of reservations with this tool.
     * @param name The tool's name.
     * @param price The tool's rent price.
     * @param reservations The List of ReservationEntity containing  reservations with this tool
     */
    public ToolEntity(String name, Integer price, List<ReservationEntity> reservations) {
        this.name = name;
        this.price = price;
        this.reservations = reservations;
    }

    /** Creates a tool with the specified name, rent prace, state and list of reservations with this tool.
     * @param name The tool's name.
     * @param price The tool's rent price.
     * @param state The tool's actual state.
     * @param reservations The List of ReservationEntity containing  reservations with this tool
     */
    public ToolEntity(String name, Integer price, ToolState state, List<ReservationEntity> reservations) {
        this.name = name;
        this.price = price;
        this.state = state;
        this.reservations = reservations;
    }

    /**
     * Sets the tool's data
     * @param tool The instance of ToolEntity class  repre
     */
    public void set(ToolEntity tool) {
        if(tool.name!=null)  this.name = tool.name;
        if(tool.price!=null)  this.price = tool.price;
        if(tool.state!=null)  this.state = tool.state;
        if(tool.reservations!=null)  this.reservations = tool.reservations;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonManagedReference
    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public ToolState getState() {
        return state;
    }

    public void setState(ToolState state) {
        this.state = state;
    }
}
