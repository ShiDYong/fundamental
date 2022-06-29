package com.mason.tacocloud02.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

/**
 * @author Mason
 * @Description TODO
 * @date 2022/6/28 21:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taco {
    //在将对象持久化到数据库到时候，通常最好将一个字段作为对象
    //作为对象的唯一标识
    private Long id;
    private Date createdAt;

    //声明校验规则：如name属性不能为空，它的值在长度上至少要有5个字符
    @NonNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min = 5, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;


}
