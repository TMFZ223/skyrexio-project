package api;

import steps.ChangeLanguageSteps;
import steps.LoginSteps;
import utils.PropertyReader;
import validators.ChangeLanguageValidator;
import validators.CorrectLoginValidator;
import validators.ErrorValidator;

abstract class BaseTest {
    protected final LoginSteps loginSteps = new LoginSteps();
    protected final ChangeLanguageSteps changeLanguageSteps = new ChangeLanguageSteps();
    protected final CorrectLoginValidator correctLoginValidator = new CorrectLoginValidator();
    protected final ErrorValidator errorValidator = new ErrorValidator();
    protected final ChangeLanguageValidator changeLanguageValidator = new ChangeLanguageValidator();
    public static final String USER_ROLE = PropertyReader.getProperty("user.role");
}
