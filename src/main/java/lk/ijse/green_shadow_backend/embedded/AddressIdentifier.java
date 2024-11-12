package lk.ijse.green_shadow_backend.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AddressIdentifier {
    private String addressLine01; // Building no or name
    private String addressLine02; // Lane
    private String addressLine03; // Main city
    private String addressLine04; // Main state
    private String addressLine05; // Postal code
}
