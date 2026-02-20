package validators;

import utils.PropertyReader;

abstract class BaseValidator {
    private static final String JSON_SCHEMAS_DIRECTORY = PropertyReader.getProperty("json.schemas.directory");

    protected String getJsonSchema(String jsonSchemaName) {
        return JSON_SCHEMAS_DIRECTORY.concat(jsonSchemaName);
    }
}
