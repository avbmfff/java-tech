package buisnesslogic.rest.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorModel {
    private String message;

    public ErrorModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

