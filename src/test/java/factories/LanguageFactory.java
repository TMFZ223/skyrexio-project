package factories;

import models.ChangeLanguageRequestModel;
import utils.PropertyReader;

import static enums.Languages.*;

public class LanguageFactory {

    public static ChangeLanguageRequestModel swichToEnglish() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), ENGLISH.getCode());
    }

    public static ChangeLanguageRequestModel swichToEspanol() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), ESPANOL.getCode());
    }

    public static ChangeLanguageRequestModel swichToFranch() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), FRANCH.getCode());
    }

    public static ChangeLanguageRequestModel swichToDeutsch() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), DEUTSCH.getCode());
    }

    public static ChangeLanguageRequestModel swichToRussian() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), RUSSIAN.getCode());
    }

    public static ChangeLanguageRequestModel swichToChinese() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), CHINESE.getCode());
    }

    public static ChangeLanguageRequestModel swichToJapanese() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), JAPANESE.getCode());
    }

    public static ChangeLanguageRequestModel swichToKorean() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), KOREAN.getCode());
    }

    public static ChangeLanguageRequestModel swichToIrish() {
        return new ChangeLanguageRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), IRISH.getCode());
    }
}
