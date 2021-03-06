package seedu.expensetracker.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static seedu.expensetracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expensetracker.testutil.ModelUtil.getTypicalModel;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import seedu.expensetracker.logic.CommandHistory;
import seedu.expensetracker.model.ExpenseTracker;
import seedu.expensetracker.model.Model;
import seedu.expensetracker.model.ModelManager;
import seedu.expensetracker.model.UserPrefs;
import seedu.expensetracker.model.exceptions.NoUserSelectedException;
import seedu.expensetracker.testutil.ExpenseTrackerBuilder;


public class SetRecurringBudgetCommandTest {
    private Model model = getTypicalModel();
    private CommandHistory commandHistory = new CommandHistory();
    private long newRecurrenceFrequency = 123456;

    @BeforeEach
    public void setUp() {
        this.model = getTypicalModel();
        this.commandHistory = new CommandHistory();
    }

    @Test
    public void execute_setRecurrence_successful() throws NoUserSelectedException {
        ExpenseTracker emptyBook = new ExpenseTrackerBuilder().build();
        model = new ModelManager(emptyBook, new UserPrefs(), null);
        ModelManager expectedModel = new ModelManager(model.getExpenseTracker(), new UserPrefs(), null);
        expectedModel.setRecurrenceFrequency(this.newRecurrenceFrequency);
        expectedModel.commitExpenseTracker();
        SetRecurringBudgetCommand setRecurrenceFrequencyCommand = new SetRecurringBudgetCommand(newRecurrenceFrequency);
        String expectedMessage = String.format(SetRecurringBudgetCommand.MESSAGE_SUCCESS, this.newRecurrenceFrequency);
        assertCommandSuccess(setRecurrenceFrequencyCommand, model, commandHistory, expectedMessage, expectedModel);
        assertNotNull(expectedModel.getMaximumBudget().getNextRecurrence());
        assertTrue(this.newRecurrenceFrequency
            == expectedModel.getExpenseTracker().getMaximumTotalBudget().getNumberOfSecondsToRecurAgain());

    }
    @Test
    public void execute_updateRecurrence_successful() throws NoUserSelectedException {
        ModelManager expectedModel = new ModelManager(model.getExpenseTracker(), new UserPrefs(), null);
        long initialRecurrenceFrequency =
            expectedModel.getExpenseTracker().getMaximumTotalBudget().getNumberOfSecondsToRecurAgain();
        expectedModel.setRecurrenceFrequency(this.newRecurrenceFrequency);
        expectedModel.commitExpenseTracker();
        String expectedMessage = String.format(SetRecurringBudgetCommand.MESSAGE_SUCCESS, this.newRecurrenceFrequency);
        SetRecurringBudgetCommand setRecurrenceFrequencyCommand = new SetRecurringBudgetCommand(newRecurrenceFrequency);
        assertCommandSuccess(setRecurrenceFrequencyCommand, model, commandHistory, expectedMessage, expectedModel);
        assertFalse(initialRecurrenceFrequency
            == expectedModel.getExpenseTracker().getMaximumTotalBudget().getNumberOfSecondsToRecurAgain());
    }

    @Test
    public void equals() {
        assertTrue(new SetRecurringBudgetCommand(1)
            .equals(new SetRecurringBudgetCommand(1)));
    }

}
