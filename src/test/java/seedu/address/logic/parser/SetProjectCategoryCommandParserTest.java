package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetProjectCategoryCommand;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;

class SetProjectCategoryCommandParserTest {
    private SetProjectCategoryCommandParser parser = new SetProjectCategoryCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project project = new Project(new Name("project"));

        assertParseSuccess(parser, "A /to project",
                new SetProjectCategoryCommand("A", project));

        assertParseSuccess(parser, "B /to project",
                new SetProjectCategoryCommand("B", project));

    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetProjectCategoryCommand.MESSAGE_USAGE);

        // missing project name
        assertParseFailure(parser, "B /to ",
                expectedMessage);

        // missing category name
        assertParseFailure(parser, " /to project",
                "Please enter the category.");
    }
}
