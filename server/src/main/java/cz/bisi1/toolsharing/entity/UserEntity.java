package cz.bisi1.toolsharing.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <h1>UserEntity</h1>
 * The UserEntity class represents the user.
 */
@Entity
@Table(name = "USER")
public class UserEntity implements Serializable, UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private Long id;
    @Column(name = "KARMA", nullable = false)
    private Integer karma;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "SECOND_NAME", nullable = true)
    private String  secondName;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PHONE", nullable = true)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID", nullable = true)
    @JsonManagedReference
    private List<ReservationEntity> reservations;

    public UserEntity() {
    }

    public UserEntity(Integer karma, List<ReservationEntity> reservations) {
        this.karma = karma;
        this.reservations = reservations;
    }

    public UserEntity(Integer karma, String firstName, String secondName, String email, List<ReservationEntity> reservations) {
        this.karma = karma;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.reservations = reservations;
    }

    public UserEntity(Integer karma, String firstName, String password, String secondName, String email, String phone, List<ReservationEntity> reservations) {
        this.karma = karma;
        this.firstName = firstName;
        this.password = password;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.reservations = reservations;
    }

    public void set(UserEntity user) {
        if(user.karma!=null) this.karma = user.karma;
        if(user.firstName!=null) this.firstName = user.firstName;
        if(user.password!=null) this.password = user.password;
        if(user.secondName!=null) this.secondName = user.secondName;
        if(user.email!=null) this.email = user.email;
        if(user.phone!=null) this.phone = user.phone;
        if(user.reservations!=null) this.reservations = user.reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonManagedReference
    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
