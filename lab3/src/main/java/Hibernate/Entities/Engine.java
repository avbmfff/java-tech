package Hibernate.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Engine")
@AllArgsConstructor

public class Engine {

    @Id
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Value", nullable = false)
    private int value;

    @Column(name = "Qty", nullable = false)
    private int qty;

    @Column(name = "Cylinder", nullable = false)
    private int cylinder;

    @Column(name = "Higher", nullable = false)
    private int higher;

    @Column(name = "CarModel", nullable = false)
    private Long CarModel;

}
