package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class CarBrand {


    @Id
    private Double id;

    @Column(name = "Brand", nullable = false)
    private String brand;

    @Column(name = "Date", nullable = false)
    private String date;

}