package buisnesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModel {
    private Double id;
    private Integer length;
    private Integer width;
    private Integer higher;
    private String brand;
    private String modelName;
    private BodyType bodyType;

}
