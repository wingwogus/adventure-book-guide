package findark.adventure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PortalDto {
    private Long id;
    private String name;
    private int x;
    private int y;

    public PortalDto(Long id, String name, int x, int y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}