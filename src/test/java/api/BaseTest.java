package api;

import steps.ChangeLanguageSteps;
import steps.ChangeThemeSteps;
import steps.LoginSteps;
import utils.PropertyReader;
import validators.ChangeUserSettingsValidator;
import validators.CorrectLoginValidator;
import validators.ErrorValidator;

abstract class BaseTest {
    protected final LoginSteps loginSteps = new LoginSteps();
    protected final ChangeLanguageSteps changeLanguageSteps = new ChangeLanguageSteps();
    protected final ChangeThemeSteps changeThemeSteps = new ChangeThemeSteps();
    protected final CorrectLoginValidator correctLoginValidator = new CorrectLoginValidator();
    protected final ErrorValidator errorValidator = new ErrorValidator();
    protected final ChangeUserSettingsValidator changeUserSettingsValidator = new ChangeUserSettingsValidator();
    public static final String USER_ROLE = PropertyReader.getProperty("user.role");
}
