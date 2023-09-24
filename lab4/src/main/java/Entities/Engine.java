package Entities;

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
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Value", nullable = false)
    private Integer value;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Cylinder", nullable = false)
    private Integer cylinder;

    @Column(name = "Higher", nullable = false)
    private Integer higher;

    @Column(name = "CarModel", nullable = false)
    private Long CarModel;

}