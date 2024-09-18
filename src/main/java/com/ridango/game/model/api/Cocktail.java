package com.ridango.game.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cocktail {

    @JsonProperty("idDrink")
    public int id;
    @JsonProperty("strDrink")
    public String name;

    @JsonProperty("strCategory")
    public String category;
    @JsonProperty("strAlcoholic")
    public String alcoholic;
    @JsonProperty("strGlass")
    public String glass;
    @JsonProperty("strInstructions")
    public String instructions;
}
