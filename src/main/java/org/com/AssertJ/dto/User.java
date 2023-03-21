package org.com.AssertJ.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Value;
@Getter
@Data
@Value(staticConstructor = "of")
public class User {
    Integer id;
    String userName;
    String password;

}
