package seedu.address.model.exceptions;

/**
 * Represents an error where no user is logged in is encountered by the model.
 */
public class NoUserSelectedException extends Exception {
    public NoUserSelectedException(String desc) {
        super(desc);
    }

    public NoUserSelectedException() {
        super("Not logged into any user.");
    }
}
