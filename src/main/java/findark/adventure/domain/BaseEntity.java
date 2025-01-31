package findark.adventure.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter@Setter
public class BaseEntity {

    private LocalDateTime lastModifiedAt;
}
