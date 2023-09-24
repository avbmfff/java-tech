package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarBrand {

    public CarBrand(){
        super();
    }

    @NonNull
    private double id;

    @NonNull
    private String brand;

    @NonNull
    private String date;

}
