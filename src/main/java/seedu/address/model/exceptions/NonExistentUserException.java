package seedu.address.model.exceptions;

import seedu.address.model.user.Username;

/**
 * Represents an error where a user with the given username does not exist in the model is encountered.
 */
public class NonExistentUserException extends Exception {
    public NonExistentUserException(Username username) {
        super("The user \"" + username + "\" does not exist.");
    }
}
