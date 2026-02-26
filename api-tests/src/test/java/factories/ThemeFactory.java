package factories;

import models.ChangeThemeRequestModel;
import utils.PropertyReader;

import static enums.Themes.*;

public class ThemeFactory {

    public static ChangeThemeRequestModel swichToDark() {
        return new ChangeThemeRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), DARK.getName());
    }

    public static ChangeThemeRequestModel swichToLight() {
        return new ChangeThemeRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), LIGHT.getName());
    }

    public static ChangeThemeRequestModel swichToDim() {
        return new ChangeThemeRequestModel(PropertyReader.getProperty("skyrex.user.uuid"), DIM.getName());
    }
}
