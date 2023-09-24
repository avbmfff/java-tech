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
@Table(name = "car_model")
@AllArgsConstructor
public class CarModel {

    public CarModel(){
        super();
    }

    @Id
    private double id;

    @Column(name = "ModelName", nullable = false)
    private String modelName;

    @Column(name = "Length", nullable = false)
    private int length;

    @Column(name = "Width", nullable = false)
    private int width;

    @Column(name = "Higher", nullable = false)
    private int higher;

    @Column(name = "Body", nullable = false)
    private BodyType bodyType;

    @Column(name = "Brand", nullable = false)
    private String brand;

}
