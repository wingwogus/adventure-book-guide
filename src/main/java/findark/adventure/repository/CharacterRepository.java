package findark.adventure.repository;
import findark.adventure.domain.Character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Character findByCharacterName(String name);
}
