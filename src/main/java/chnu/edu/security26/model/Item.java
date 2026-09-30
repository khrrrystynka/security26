package chnu.edu.security26.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Item {
    private String id;
    private String name;
    private String description;
}
