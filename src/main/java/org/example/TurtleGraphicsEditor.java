package org.example;

import java.util.Scanner;

public class TurtleGraphicsEditor {
    private static final int CANVAS_WIDTH = 50;
    private static final int CANVAS_HEIGHT = 20;

    private Matrix canvas;
    private Turtle turtle;
    private CommandHistory history;
    private Scanner scanner;

    public TurtleGraphicsEditor() {
        this.canvas = new Matrix(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.turtle = new Turtle(new Pen(new BresenhamStrategy()));
        this.history = new CommandHistory();
        this.scanner = new Scanner(System.in);
        turtle.setPosition(CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0);
    }


    public void start() {
        displayWelcomeMessage();
        boolean running = true;
        while (running) {
            System.out.print("turtle> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }
            String[] parts = input.split("\\s+");
            String command = parts[0].toLowerCase();

            try {
                running = processCommand(command, parts);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
                System.out.println("Type 'help' for available commands.");
            }
        }

        displayClosingMessage();
    }

    private boolean processCommand(String command, String[] parts) {
        switch (command) {
            case "quit":
            case "exit":
                return false;

            case "help":
                displayHelp();
                break;

            case "show":
                executeCommand(new ShowCommand(canvas));
                break;

            case "move":
                if (parts.length < 2) {
                    System.out.println("Usage: move <distance>");
                    break;
                }
                double moveDistance = parseDouble(parts[1]);
                executeCommand(new MoveCommand(turtle, canvas, moveDistance));
                break;

            case "trace":
                if (parts.length < 2) {
                    System.out.println("Usage: trace <distance>");
                    break;
                }
                double traceDistance = parseDouble(parts[1]);
                executeCommand(new TraceCommand(turtle, canvas, traceDistance));
                break;

            case "turn":
                if (parts.length < 2) {
                    System.out.println("Usage: turn <angle>");
                    break;
                }
                double angle = parseDouble(parts[1]);
                executeCommand(new TurnCommand(turtle, angle));
                break;

            case "undo":
                if (history.undo(turtle, canvas)) {
                    System.out.println("Command undone.");
                } else {
                    System.out.println("Nothing to undo.");
                }
                break;

            case "redo":
                if (history.redo(turtle, canvas)) {
                    System.out.println("Command redone.");
                } else {
                    System.out.println("Nothing to redo.");
                }
                break;

            case "clear":
                executeCommand(new Command() {
                    @Override
                    public void execute() {
                        canvas.clear();
                        turtle.setPosition(CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0);
                        turtle.setDirection(0);
                        System.out.println("Canvas cleared.");
                    }
                });
                break;

            case "rectangle":
                if (parts.length < 3) {
                    System.out.println("Usage: rectangle <width> <height>");
                    break;
                }
                double width = parseDouble(parts[1]);
                double height = parseDouble(parts[2]);
                executeCommand(new RectangleCommand(turtle, canvas, width, height));
                break;

            case "letters":
                executeCommand(new LetterSCommand(turtle, canvas, 5));
                break;

            case "lettere":
                executeCommand(new LetterECommand(turtle, canvas, 5));
                break;

            case "digit3":
                executeCommand(new Digit3Command(turtle, canvas, 5));
                break;

            case "se350":
                executeCommand(new TextSE350Command(turtle, canvas, 3));
                break;

            case "strategy":
                if (parts.length < 2) {
                    System.out.println("Usage: strategy <bresenham|naive>");
                    break;
                }
                String strategyName = parts[1].toLowerCase();
                if ("bresenham".equals(strategyName)) {
                    turtle.getPen().setStrategy(new BresenhamStrategy());
                    System.out.println("Drawing strategy set to Bresenham.");
                } else if ("naive".equals(strategyName)) {
                    turtle.getPen().setStrategy(new NaiveStrategy());
                    System.out.println("Drawing strategy set to Naive.");
                } else {
                    System.out.println("Unknown strategy. Available: bresenham, naive");
                }
                break;

            case "status":
                displayStatus();
                break;

            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Type 'help' for available commands.");
        }

        return true;
    }

    private void executeCommand(Command command) {
        history.saveSnapshot(turtle, canvas);
        command.execute();
    }

    private double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + str);
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("=========================================");
        System.out.println("    Welcome to Turtle Graphics Editor    ");
        System.out.println("=========================================");
        System.out.println("This program allows you to draw graphics using turtle commands.");
        System.out.println("The turtle starts at the center of a " + CANVAS_WIDTH + "x" + CANVAS_HEIGHT + " canvas.");
        System.out.println("Type 'help' to see available commands or 'quit' to exit.");
        System.out.println();
    }

    private void displayHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("==================");
        System.out.println("Basic Commands:");
        System.out.println("  show                    - Display the current drawing");
        System.out.println("  move <distance>         - Move turtle without drawing");
        System.out.println("  trace <distance>        - Move turtle while drawing");
        System.out.println("  turn <angle>            - Turn turtle by angle (degrees)");
        System.out.println("  clear                   - Clear the canvas");
        System.out.println("");
        System.out.println("History Commands:");
        System.out.println("  undo                    - Undo the last command");
        System.out.println("  redo                    - Redo the last undone command");
        System.out.println("");
        System.out.println("Drawing Patterns:");
        System.out.println("  rectangle <w> <h>       - Draw a rectangle");
        System.out.println("  letters                 - Draw letter 'S'");
        System.out.println("  lettere                 - Draw letter 'E'");
        System.out.println("  digit3                  - Draw digit '3'");
        System.out.println("  se350                   - Draw text 'SE 350'");
        System.out.println("");
        System.out.println("Settings:");
        System.out.println("  strategy <name>         - Set drawing strategy (bresenham|naive)");
        System.out.println("  status                  - Show turtle status");
        System.out.println("");
        System.out.println("System:");
        System.out.println("  help                    - Show this help message");
        System.out.println("  quit                    - Exit the program");
        System.out.println();
    }

    private void displayStatus() {
        System.out.println("\nTurtle Status:");
        System.out.println("=============");
        System.out.printf("Position: (%.2f, %.2f)%n", turtle.getX(), turtle.getY());
        System.out.printf("Direction: %.2f degrees%n", turtle.getDirection());
        System.out.println("Pen: " + (turtle.getPen().isDown() ? "DOWN (drawing)" : "UP (not drawing)"));
        String strategyName = turtle.getPen().getStrategy().getClass().getSimpleName();
        System.out.println("Strategy: " + strategyName);
        System.out.println("Canvas: " + CANVAS_WIDTH + "x" + CANVAS_HEIGHT);
        System.out.println("Undo available: " + history.canUndo());
        System.out.println("Redo available: " + history.canRedo());
        System.out.println();
    }

    private void displayClosingMessage() {
        System.out.println("\n=========================================");
        System.out.println("  Thank you for using Turtle Graphics!   ");
        System.out.println("=========================================");
        System.out.println("Program terminated successfully.");
    }

    public static void main(String[] args) {
        TurtleGraphicsEditor editor = new TurtleGraphicsEditor();
        editor.start();
    }
}