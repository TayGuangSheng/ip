# Wag Task Manager - User Guide

## Table of Contents

- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Commands](#commands)
    - [Adding Tasks](#adding-tasks)
    - [Listing Tasks](#listing-tasks)
    - [Marking Tasks as Done](#marking-tasks-as-done)
    - [Unmarking Tasks](#unmarking-tasks)
    - [Deleting Tasks](#deleting-tasks)
    - [Finding Tasks](#finding-tasks)
    - [Exiting the Application](#exiting-the-application)
- [Examples](#examples)
- [Troubleshooting](#troubleshooting)
- [Command Summary](#command-summary)

## Introduction

Wag Task Manager is a simple command-line task management application. It helps users keep track of their tasks by allowing them to add, delete, and mark tasks as done.

## Getting Started

### Installation

1. Ensure you have **Java 11 or later** installed on your system.
2. Download the `wag.jar` file.
3. Open a terminal or command prompt and navigate to the directory containing `wag.jar`.
4. Run the application using:
   ```sh
   java -jar wag.jar
   ```

## Commands

Wag Task Manager supports various commands to help you manage your tasks effectively.

### Adding Tasks

You can add three types of tasks:

- **Todo**: A basic task with just a description.
  ```sh
  todo Read a book
  ```
- **Deadline**: A task with a due date.
  ```sh
  deadline Submit report /by 02/03/2025 0600
  ```
- **Event**: A task that occurs from a specific time to a specific time.
  ```sh
  event Team meeting /from 02/03/2025 0600 /to 02/03/2025 1800
  ```

### Listing Tasks

To view all tasks, use:

```sh
list
```

### Marking Tasks as Done

To mark a task as done:

```sh
mark 2
```

*This marks task #2 as completed.*

### Unmarking Tasks

To unmark a task as not done:

```sh
unmark 2
```

### Deleting Tasks

To remove a task:

```sh
delete 3
```

*This deletes task #3 from the list.*

### Finding Tasks

To search for tasks containing a keyword:

```sh
find report
```

### Exiting the Application

To exit Wag Task Manager, type:

```sh
bye
```

## Examples

```
_______________________________________
Hello! I'm Wag
What can I do for you?
_______________________________________
> todo Read a book
_______________________________________
Got it. I've added this task:
  [T][ ] Read a book
Now you have 1 tasks in the list.
_______________________________________
_______________________________________
> deadline Submit report /by 02/03/2025 0600
_______________________________________
Got it. I've added this task:
  [D][ ] Submit report (by: Mar 2 2025, 6:00AM)
Now you have 2 tasks in the list.
_______________________________________
_______________________________________
> event Team meeting /from 02/03/2025 0600 /to 02/03/2025 1800
_______________________________________
Got it. I've added this task:
  [E][ ] Team meeting (from: Mar 2 2025, 6:00AM to: Mar 2 2025, 6:00PM)
Now you have 3 tasks in the list.
_______________________________________
_______________________________________
> list
_______________________________________
Here are the tasks in your list:
1. [T][ ] Read a book
2. [D][ ] Submit report (by: Mar 2 2025, 6:00AM)
3. [E][ ] Team meeting (from: Mar 2 2025, 6:00AM to: Mar 2 2025, 6:00PM)
_______________________________________
_______________________________________
> mark 2
_______________________________________
Nice! I've marked this task as done:
  [D][X] Submit report (by: Mar 2 2025, 6:00AM)
_______________________________________
_______________________________________
> unmark 2
_______________________________________
OK, I've marked this task as not done yet:
  [D][ ] Submit report (by: Mar 2 2025, 6:00AM)
_______________________________________
_______________________________________
> delete 3
_______________________________________
Noted. I've removed this task:
  [E][ ] Team meeting (from: Mar 2 2025, 6:00AM to: Mar 2 2025, 6:00PM)
Now you have 2 tasks left in the list.
_______________________________________
_______________________________________
> find report
_______________________________________
Here are the matching tasks in your list:
1. [D][ ] Submit report (by: Mar 2 2025, 6:00AM)
_______________________________________
> bye
_______________________________________
Bye. Hope to see you again soon!
_______________________________________
```

## Troubleshooting

- **Invalid Command**: Ensure commands are typed correctly.
- **Task Not Found**: Check if the task number exists in the list.
- **Date Format Issues**: Use `DD-MM-YYYY HH:mm` for dates.
- **Application Crashes**: Try restarting the app and checking `tasks.txt` for issues.

## Command Summary

| Action                | Format                                     | Example                                      |
|-----------------------|-------------------------------------------|----------------------------------------------|
| Add a Todo Task      | `todo <description>`                      | `todo Read a book`                          |
| Add a Deadline Task  | `deadline <description> /by <date time>`  | `deadline Submit report /by 02/03/2025 0600` |
| Add an Event Task    | `event <description> /from <start> /to <end>` | `event Team meeting /from 02/03/2025 0600 /to 02/03/2025 1800` |
| List Tasks           | `list`                                     | `list`                                      |
| Mark Task as Done    | `mark <task number>`                      | `mark 2`                                    |
| Unmark Task as Not Done | `unmark <task number>`                  | `unmark 2`                                  |
| Delete Task          | `delete <task number>`                    | `delete 3`                                  |
| Find Task            | `find <keyword>`                          | `find report`                               |
| Exit Application     | `bye`                                      | `bye`                                       |


---

Enjoy using Wag Task Manager! 🚀

