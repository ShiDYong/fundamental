package com.mason.tacocloud.entries;

import java.util.List;
// end::allButValidation[]
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.*;

/**
 * @author Mason
 * @Description TODO
 * @date 2022/6/28 21:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taco {
    @NonNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min = 5, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;


}
