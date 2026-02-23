package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoints {
    LOGIN("/user/login"),
    CHANGETHEME("user/changeTheme"),
    CHANGELANGUAGE("/user/changeLanguage");
    private final String path;
}
