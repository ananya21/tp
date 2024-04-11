package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String name;
    private final String deadline;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String category;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
            @JsonProperty("category") String category, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.deadline = deadline;
        this.category = category;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        name = source.getName().fullName;
        deadline = source.getDeadlineString();
        category = source.getCategory();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Tag> projectTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            projectTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        Project toReturn = new Project(modelName);
        if (deadline != null && deadline.length() != 0) {
            toReturn.setDeadline(deadline);
        }
        if (category != null && category.length() != 0) {
            toReturn.setCategory(category);
        }
        return toReturn;
    }

}