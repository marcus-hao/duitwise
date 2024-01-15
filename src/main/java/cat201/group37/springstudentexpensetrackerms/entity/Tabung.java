package cat201.group37.springstudentexpensetrackerms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tabung")
public class Tabung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tabung_id;

    @Column(name = "tabung_name", nullable = false)
    private String name;

    @Column(name = "tabung_type", nullable= false)
    private String type;

    @Column(name = "target_amount", nullable = false)
    private Double target_amount;

    @Column(name = "remaining_amount", nullable = false)
    private Double remaining_amount;

    @Column(name = "start_date", nullable = false)
    private LocalDate start_date;

    @Column(name = "end_date", nullable = false)
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    public Long getTabung_id() {
        return tabung_id;
    }

    public void setTabung_id(Long tabung_id) {
        this.tabung_id = tabung_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTarget_amount() {
        return target_amount;
    }

    public void setTarget_amount(Double target_amount) {
        this.target_amount = target_amount;
    }

    public Double getRemaining_amount() {
        return remaining_amount;
    }

    public void setRemaining_amount(Double remaining_amount) {
        this.remaining_amount = remaining_amount;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
