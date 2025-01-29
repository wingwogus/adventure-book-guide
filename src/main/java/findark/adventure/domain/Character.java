package findark.adventure.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "characters")
public class Character {

    @Id @GeneratedValue
    private Long id;

    private String serverName;

    private String characterName;

    private Integer characterLevel;

    private String characterClassName;

    private String itemAvgLevel;

    private String itemMaxLevel;

    private String characterImage;

    private Integer expeditionLevel;

    private Integer totalSkillPoint;

    private String title;

    private String guildName;

    @Embedded
    private Town town;

    @Embedded
    private ArkPassivePoint arkPassivePoint;
}
