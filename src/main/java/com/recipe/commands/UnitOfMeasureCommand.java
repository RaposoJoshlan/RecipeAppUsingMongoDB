package com.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on 11/04/2022
 */

@Setter
@Getter
@NoArgsConstructor
public class UnitOfMeasureCommand {
    private Long id;
    private String description;
}
