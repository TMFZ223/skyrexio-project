package user;

import utils.PropertyReader;

public class UserFactory {

    public static User withValidCredentials() {
        return new User(PropertyReader.getProperty("valid.email"), PropertyReader.getProperty("valid.password"));
    }

    public static User withUnknownEmail() {
        return new User(PropertyReader.getProperty("unknown.email"), PropertyReader.getProperty("valid.password"));
    }

    public static User withIncorrectFormatEmail() {
        return new User(PropertyReader.getProperty("invalid.format.email"), PropertyReader.getProperty("valid.password"));
    }

    public static User withEmptyEmail() {
        return new User("", PropertyReader.getProperty("valid.password"));
    }

    public static User withEmptyPassword() {
        return new User(PropertyReader.getProperty("valid.email"), "");
    }

    public static User withEmptyValues() {
        return new User("", "");
    }

    public static User withNullEmailValue() {
        return new User(null, PropertyReader.getProperty("valid.password"));
    }

    public static User withNullPasswordValue() {
        return new User(PropertyReader.getProperty("valid.email"), null);
    }

    public static User withNullValues() {
        return new User(null, null);
    }
}
