package findark.adventure.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
public class ArkPassivePoint {

    private int evolution;
    private int realization;
    private int leaf;

    public ArkPassivePoint() {
    }

    public ArkPassivePoint(int evolution, int realization, int leaf) {
        this.evolution = evolution;
        this.realization = realization;
        this.leaf = leaf;
    }
}
