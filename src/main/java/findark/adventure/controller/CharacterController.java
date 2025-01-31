package findark.adventure.controller;

import findark.adventure.domain.Character;
import findark.adventure.dto.CharacterDto;
import findark.adventure.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/{characterName}")
    public String index(@PathVariable String characterName, Model model) {

        Character character = characterService.getCharacterByName(characterName);

        if (character == null || ChronoUnit.HOURS.between(LocalDateTime.now(), character.getLastModifiedAt()) > 1) {
            characterService.searchCharacterAndSave(characterName);
            character = characterService.getCharacterByName(characterName);
        }

        CharacterDto characterDto = new CharacterDto(
                character.getServerName(),
                character.getCharacterName(),
                character.getCharacterLevel(),
                character.getCharacterClassName(),
                character.getItemMaxLevel(),
                character.getCharacterImage(),
                character.getExpeditionLevel(),
                character.getTitle(),
                character.getGuildName(),
                character.getTown(),
                character.getArkPassivePoint());

        model.addAttribute(
                "character",
                characterDto.getCharacterImage() != null ? characterDto : null);

        return "character";
    }
}
