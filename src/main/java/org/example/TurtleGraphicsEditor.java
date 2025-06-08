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
            System.out.flush();
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
                e.printStackTrace();
                System.out.println("Type 'help' for available commands.");
            }
        }

        displayClosingMessage();
    }

    private boolean processCommand(String command, String[] parts) {
        System.out.println("Processing command: " + command);

        switch (command) {
            case "quit":
            case "exit":
                return false;

            case "help":
                System.out.println("Basic commands: show, trace <n>, move <n>, turn <n>, clear, quit");
                System.out.println("Complex commands: rectangle <w> <h>, letters, lettere, digit3, se350");
                System.out.println("Other commands: undo, redo, strategy <bresenham|naive>, status");
                break;

            case "show":
                new ShowCommand(canvas).execute();
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
                System.out.println("Creating rectangle command...");
                executeCommand(new RectangleCommand(turtle, canvas, width, height));
                System.out.println("Rectangle command completed.");
                break;

            case "letters":
                System.out.println("Creating letter S command...");
                executeCommand(new LetterSCommand(turtle, canvas, 3));
                System.out.println("Letter S command completed.");
                break;

            case "lettere":
                System.out.println("Creating letter E command...");
                executeCommand(new LetterECommand(turtle, canvas, 3));
                System.out.println("Letter E command completed.");
                break;

            case "digit3":
                System.out.println("Creating digit 3 command...");
                executeCommand(new Digit3Command(turtle, canvas, 3));
                System.out.println("Digit 3 command completed.");
                break;

            case "se350":
                System.out.println("Creating SE350 text command...");
                executeCommand(new TextSE350Command(turtle, canvas, 2));
                System.out.println("SE350 text command completed.");
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
                System.out.printf("Position: (%.2f, %.2f)%n", turtle.getX(), turtle.getY());
                System.out.printf("Direction: %.2f degrees%n", turtle.getDirection());
                System.out.println("Pen is " + (turtle.getPen().isDown() ? "down" : "up"));
                System.out.println("Canvas size: " + CANVAS_WIDTH + "x" + CANVAS_HEIGHT);
                break;

            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Type 'help' for available commands.");
        }

        return true;
    }

    private void executeCommand(Command command) {
        try {
            System.out.println("Saving snapshot...");
            history.save(turtle, canvas);
            System.out.println("Executing command...");
            command.execute();
            System.out.println("Command executed successfully.");
        } catch (Exception e) {
            System.out.println("Error executing command: " + e.getMessage());
            e.printStackTrace();
        }
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