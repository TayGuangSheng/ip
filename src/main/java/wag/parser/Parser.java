package wag.parser;

import wag.commands.*;
import wag.exceptions.WagException;
import wag.tasks.TaskList;

public class Parser {

    public static Command parse(String input, TaskList taskList) throws WagException {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = (parts.length > 1) ? parts[1].trim() : "";

        return switch (command) {
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(parseTaskNumber(args, "mark", taskList));
            case "unmark" -> new UnmarkCommand(parseTaskNumber(args, "unmark", taskList));
            case "todo" -> parseTodo(args);
            case "deadline" -> parseDeadline(args);
            case "event" -> parseEvent(args);
            case "delete" -> new DeleteCommand(parseTaskNumber(args, "delete", taskList));
            default -> throw new WagException("I'm sorry, but I don't know what that means.");
        };
    }

    /** Parses task number for commands like mark, unmark, and delete */
    private static int parseTaskNumber(String args, String commandType, TaskList taskList) throws WagException {
        if (args.isEmpty()) {
            throw new WagException("Please provide a valid task number to " + commandType + ".");
        }
        try {
            int taskId = Integer.parseInt(args) - 1;
            if (!taskList.isValidTaskIndex(taskId)) {
                throw new WagException("Task number is out of range.");
            }
            return taskId;
        } catch (NumberFormatException e) {
            throw new WagException("Task number should be a valid integer.");
        }
    }

    /** Parses a Todo command */
    private static Command parseTodo(String args) throws WagException {
        if (args.isEmpty()) {
            throw new WagException("The description for a todo cannot be empty.");
        }
        return new TodoCommand(args);
    }

    /** Parses a Deadline command */
    private static Command parseDeadline(String args) throws WagException {
        String[] deadlineParts = args.split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            throw new WagException("Invalid deadline format. Use: deadline <description> /by <date>");
        }
        return new DeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
    }

    /** Parses an Event command */
    private static Command parseEvent(String args) throws WagException {
        String[] eventParts = args.split(" /from | /to ", 3);
        if (eventParts.length < 3 || eventParts[0].isEmpty() || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
            throw new WagException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        return new EventCommand(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
    }
}
