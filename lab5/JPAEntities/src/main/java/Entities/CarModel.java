package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "car_model")
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {


    @Id
    private Double id;

    @Column(name = "ModelName", nullable = false)
    private String modelName;

    @Column(name = "Length", nullable = false)
    private Integer length;

    @Column(name = "Width", nullable = false)
    private Integer width;

    @Column(name = "Higher", nullable = false)
    private Integer higher;

    @Column(name = "Body", nullable = false)
    private BodyType bodyType;

    @Column(name = "Brand", nullable = false)
    private String brand;

}