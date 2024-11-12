package lk.ijse.green_shadow_backend.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class NameIdentifier {
    private String firstName;
    private String lastName;
}
