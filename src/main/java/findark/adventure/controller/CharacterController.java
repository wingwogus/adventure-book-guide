package findark.adventure.controller;

import findark.adventure.domain.Character;
import findark.adventure.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/{characterName}")
    public String index(@PathVariable String characterName, Model model) {

        Character character = characterService.getCharacterByName(characterName);

        if (character == null) {
            characterService.searchCharacterAndSave(characterName);
            character = characterService.getCharacterByName(characterName);
        }

        model.addAttribute("characterInfo", character);

        return "character";
    }
}
