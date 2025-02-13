# Wag User Guide

Wag is a command-line chatbot designed to help you manage your tasks quickly and efficiently. Whether you need to add todos, deadlines, or events, Wag provides a simple and intuitive interface to keep track of your tasks. With built-in error handling, Wag guides you to correct any mistakes, ensuring a smooth experience.

## Adding Deadlines
To add a deadline task, use the deadline command followed by the task description and the deadline time prefixed with /by.

Example:
````
deadline return book /by Sunday
````

Expected output:

````
Got it. I've added this task:
[D][ ] return book (by: Sunday)
````

This command creates a new deadline task and confirms that it has been added to your task list.

## Marking and Unmarking Tasks
Wag allows you to mark tasks as completed or revert them back to incomplete. Use the mark command to mark a task as done and the unmark command to undo it.

Example for marking a task:
````
mark 1
````

Expected output:
````
Nice! I've marked this task as done:
[T][X] read book
````

Example for unmarking a task:
````
unmark 1
````
Expected output:
````
OK, I've marked this task as not done yet:
[T][ ] read book
````
This feature helps you keep track of your progress by visually indicating which tasks are complete.

## Error Handling
Wag includes robust error handling to ensure that incorrect inputs are managed gracefully. Instead of crashing or behaving unpredictably, Wag provides clear feedback to help you correct your command.

Example: Entering an incomplete todo command:
````
todo
````

Expected output:
````
____________________________________________________________
OOPS!!! The description for a todo cannot be empty.
____________________________________________________________
````
By guiding you with specific error messages, Wag makes it easy to adjust your input and continue managing your tasks without interruption.