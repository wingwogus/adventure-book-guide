package findark.adventure.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import findark.adventure.dto.CharacterInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String townName;

    private Integer townLevel;

    private Integer totalSkillPoint;

    private String title;

    private String guildName;
}
