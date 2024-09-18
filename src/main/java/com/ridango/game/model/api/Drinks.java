package com.ridango.game.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Drinks {
    @Getter
    @Setter

    @JsonProperty("drinks")
    List<Cocktail> drinks;
}
