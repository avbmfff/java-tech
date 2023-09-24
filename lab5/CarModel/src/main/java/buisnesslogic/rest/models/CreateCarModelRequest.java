package buisnesslogic.rest.models;

import buisnesslogic.models.BodyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.ValidationException;

@Getter
@Setter
@NoArgsConstructor
public class CreateCarModelRequest {
    private Double id;
    private Integer length;
    private Integer width;
    private Integer higher;
    private String brand;
    private String modelName;
    private BodyType bodyType;

    @JsonbCreator
    public CreateCarModelRequest(@JsonbProperty("id") Double id, @JsonbProperty("length") Integer length, @JsonbProperty("width") Integer width,
    @JsonbProperty("higher") Integer higher, @JsonbProperty("brand") String brand, @JsonbProperty("modelName") String modelName, @JsonbProperty("bodyType") BodyType bodyType){
        this.id = id;
        this.length = length;
        this.width = width;
        this.higher = higher;
        this.brand = brand;
        this.modelName = modelName;
        this.bodyType = bodyType;
    }

    public void validate(){
        if(this.id == null){
            throw new ValidationException("id is required");
        }

        if(this.brand == null){
            throw new ValidationException("brand is required");
        }

        if (this.length == null){
            throw new ValidationException("length is required");
        }

        if (this.width == null){
            throw new ValidationException("width is required");
        }

        if (this.higher == null){
            throw new ValidationException("higher is required");
        }

        if (this.modelName == null){
            throw new ValidationException("modelName is required");
        }

        if (this.bodyType == null){
            throw new ValidationException("bodyType is required");
        }
    }
}
