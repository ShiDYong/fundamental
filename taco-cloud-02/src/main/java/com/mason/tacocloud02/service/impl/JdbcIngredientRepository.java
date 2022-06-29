package com.mason.tacocloud02.service.impl;

import com.mason.tacocloud02.entry.Ingredient;
import com.mason.tacocloud02.service.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mason
 * @Description TODO
 * @date 2022/6/29 23:16
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    @Autowired
    private JdbcTemplate jdbc;


    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name,type from Ingredient",
                this::mapRowToIngredient); //方法引用
    }

    @Override
    public Ingredient findOne() {
        return jdbc.queryForObject(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient);

    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update(
                "insert into Ingredient(id,name,type) values(?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }


    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));

    }
}
