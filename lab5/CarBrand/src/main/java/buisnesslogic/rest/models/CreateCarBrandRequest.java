package buisnesslogic.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.ValidationException;

import javax.json.bind.annotation.JsonbProperty;

@Getter
@Setter
@NoArgsConstructor
public class CreateCarBrandRequest {
    private Double id;
    private String brand;
    private String date;

    @JsonbCreator
    public CreateCarBrandRequest(@JsonbProperty("id") Double id, @JsonbProperty("brand") String brand, @JsonbProperty("date") String date){
        this.brand = brand;
        this.date = date;
        this.id = id;
    }

    public void validate(){
        if(this.id == null){
            throw new ValidationException("id is required");
        }

        if(this.brand == null){
            throw new ValidationException("brand is required");
        }

        if (this.date == null){
            throw new ValidationException("date is required");
        }
    }
}
