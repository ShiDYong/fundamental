package com.mason.tacocloud02.service;

import com.mason.tacocloud02.entry.Ingredient;

/**
 * @author Mason
 * @Description 声明接口
 * @date 2022/6/29 23:11
 */
public interface IngredientRepository {
    /**
     * 查询所有Ingredient的接库
     * @return
     */
    Iterable<Ingredient> findAll();

    /**
     * 查找一个的接口
     * @return
     */
    Ingredient findOne();

    /**
     * 增加一个的接口
     * @param ingredient
     * @return
     */
    Ingredient save(Ingredient ingredient);
}
