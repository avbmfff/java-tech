package Hibernate.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "car_brand")
@AllArgsConstructor
public class CarBrand {

    public CarBrand(){
        super();
    }

    @Id
    private double id;

    @Column(name = "Brand", nullable = false)
    private String brand;

    @Column(name = "Date", nullable = false)
    private String date;

}
