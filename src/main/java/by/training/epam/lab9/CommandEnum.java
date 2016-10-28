package by.training.epam.lab9;

/**
 * Created by alexh on 26.10.2016.
 */
public enum  CommandEnum {

    LOGIN("LGN", "LOGIN (LGN) - user authorization"),
    REGISTER("RGT", "REGISTER (RGT) - user registration"),

    ADD_TEST("AT", "ADD_TEST (AT) - add test (the user must be tutor)"),
    SHOW_TEST("ST", "SHOW_TEST (ST) - show test (the user must be authorized)"),
    SHOW_TESTS("STS", "SHOW_TESTS (STS) - show tests (the user must be authorized)"),

    ADD_SUBJECT("AS", "ADD_SUBJECT (AS) - add test subject (the user must be tutor)"),
    SHOW_SUBJECTS("SSS", "SHOW_SUBJECTS (SSS) - show test subjects (the user must be tutor)"),

    HELP("H", "HELP (H) - help"),
    CLOSE("C", "CLOSE (C) - close program");


    private String abbreviation;
    private String description;


    CommandEnum(String abbreviation, String description){
        this.abbreviation = abbreviation;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }


    public static CommandEnum getEnum(String value){
        CommandEnum result = null;
        for (CommandEnum command : CommandEnum.values()){
            if (value.equals(command.name()) || value.equals(command.abbreviation)){
                result = command;
                break;
            }
        }

        if (result == null){
            throw new EnumConstantNotPresentException(CommandEnum.class, value);
        }

        return result;
    }
}
