package itsm.springGreetingsClient.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SimpleGreetingsResponse {

    @JsonProperty("message")
    private String message;

    public SimpleGreetingsResponse() {

    }

    public SimpleGreetingsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public SimpleGreetingsResponse setMessage(String message) {
        this.message = message;return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleGreetingsResponse that = (SimpleGreetingsResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
