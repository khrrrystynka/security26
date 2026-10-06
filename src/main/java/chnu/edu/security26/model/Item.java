package chnu.edu.security26.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document
public class Item extends Auditable {
    private String id;
    private String name;
    private String description;

    public Item (String description, String name) {
        this.description = description;
        this.name = name;
    }
}
