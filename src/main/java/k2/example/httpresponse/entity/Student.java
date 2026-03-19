package k2.example.httpresponse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Student {
    private String reference;
    private String firstName;
    private String lastName;
    private int age;
}
