package com.open.dojo.builder.v4;

import com.open.dojo.builder.model.RemiseFunction;

public interface IRemiseBuilder<T> {

    /**
     * Ajoute le calcul d'une remise à un article
     * 
     * @param func
     * @return
     */
    T remise(RemiseFunction func);
}
