package com.recipe.services;

import com.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created on 13/04/2022
 */

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
