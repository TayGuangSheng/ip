package wag.parser;

import wag.commands.*;
import wag.exceptions.WagException;
import wag.tasks.TaskList;

public class Parser {

    public static Command parse(String input, TaskList taskList) throws WagException {
        String command = input.trim();
        if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            int taskId = parseTaskNumber(command, "mark", taskList.getTaskCount());
            return new MarkCommand(taskId);
        } else if (command.startsWith("unmark")) {
            int taskId = parseTaskNumber(command, "unmark", taskList.getTaskCount());
            return new UnmarkCommand(taskId);
        } else if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            if (description.isEmpty()) {
                throw new WagException("The description for a todo cannot be empty.");
            }
            return new TodoCommand(description);
        } else if (command.startsWith("deadline")) {
            String content = command.substring(9).trim();
            String[] parts = content.split(" /by ", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new WagException("Invalid deadline format. Use: deadline <description> /by <date>");
            }
            return new DeadlineCommand(parts[0].trim(), parts[1].trim());
        } else if (command.startsWith("event")) {
            String content = command.substring(6).trim();
            String[] parts = content.split(" /from | /to ", 3);
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new WagException("Invalid event format. Use: event <description> /from <start> /to <end>");
            }
            return new EventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
        } else if (command.startsWith("delete")) {
            int taskId = parseTaskNumber(command, "delete", taskList.getTaskCount());
            return new DeleteCommand(taskId);
        } else {
            throw new WagException("I'm sorry, but I don't know what that means.");
        }
    }

    private static int parseTaskNumber(String command, String commandType, int taskCount) throws WagException {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new WagException("Please provide a valid task number to " + commandType + ".");
        }
        try {
            int taskId = Integer.parseInt(parts[1]) - 1;
            if (taskId < 0 || taskId >= taskCount) {
                throw new WagException("wag.tasks.Task number is out of range.");
            }
            return taskId;
        } catch (NumberFormatException e) {
            throw new WagException("wag.tasks.Task number should be a valid integer.");
        }
    }
}
