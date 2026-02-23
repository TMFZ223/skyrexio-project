package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangeLanguageRequestModel {
private String skyrexUserUuid;
private String language;
}
