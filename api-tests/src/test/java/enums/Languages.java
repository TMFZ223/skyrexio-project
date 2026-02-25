package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Languages {
    ENGLISH("en"),
    ESPANOL("es"),
    FRANCH("fr"),
    DEUTSCH("de"),
    RUSSIAN("ru"),
    CHINESE("zh"),
    JAPANESE("ja"),
    KOREAN("ko"),
    IRISH("ir");
    private final String code;
}
