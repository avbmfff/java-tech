package Entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBrand {

    @NonNull
    private double id;

    @NonNull
    private String brand;

    @NonNull
    private String date;

}
