package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangeThemeRequestModel {
    private String skyrexUserUuid;
    private String theme;
}
