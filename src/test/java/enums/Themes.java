package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Themes {
    DARK("dark"),
    LIGHT("light"),
    DIM("DIM");
    private final String name;
}
