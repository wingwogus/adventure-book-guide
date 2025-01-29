package findark.adventure.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Town {

    private String townName;
    private Integer townLevel;

    public Town() {
    }

    public Town( String townName, Integer townLevel) {
        this.townName = townName;
        this.townLevel = townLevel;
    }
}
