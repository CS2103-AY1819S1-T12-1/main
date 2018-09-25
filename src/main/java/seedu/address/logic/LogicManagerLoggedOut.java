package seedu.address.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.exceptions.NotLoggedInException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.expense.Person;

/**
 * The LogicManager of the app that manages logic after the user is logged in.
 */
public class LogicManagerLoggedOut extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManagerLoggedOut.class);

    private final CommandHistory history;
    private final AddressBookParser addressBookParser;

    public LogicManagerLoggedOut() {
        history = new CommandHistory();
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Command command = addressBookParser.parseCommand(commandText);
            return command.execute(null, history);
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() throws NotLoggedInException {
        throw new NotLoggedInException("User is not logged in to any account.");
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
