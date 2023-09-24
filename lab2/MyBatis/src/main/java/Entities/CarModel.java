package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarModel {

    public CarModel(){
        super();
    }

    @NonNull
    private double id;

    @NonNull
    private String modelName;

    @NonNull
    private int length;

    @NonNull
    private int width;

    @NonNull
    private BodyType bodyType;

    @NonNull
    private String brand;
}
