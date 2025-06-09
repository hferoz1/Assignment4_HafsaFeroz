package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class AllTests {

    private Matrix matrix;
    private Turtle turtle;
    private Pen pen;
    private CommandHistory history;

    @BeforeEach
    public void setUp() {
        pen = new Pen(new BresenhamStrategy());
        turtle = new Turtle(pen);
        matrix = new Matrix(20, 20);
        history = new CommandHistory();
        turtle.setPosition(10, 10);
    }

    @Test
    public void testMatrix() {
        matrix.setCell(5, 5, '#');
        assertEquals('#', matrix.getCell(5, 5));
        matrix.clear();
        assertEquals(' ', matrix.getCell(5, 5));
    }

    @Test
    public void testTurtle() {
        turtle.move(matrix, 5);
        assertEquals(15.0, turtle.getX(), 0.01);
        turtle.turn(90);
        assertEquals(90.0, turtle.getDirection());
    }

    @Test
    public void testPen() {
        pen.down();
        assertTrue(pen.isDown());
        pen.drawLine(matrix, 0, 0, 3, 0);
        assertEquals('#', matrix.getCell(0, 0));
        assertEquals('#', matrix.getCell(3, 0));
    }

    @Test
    public void testStrategies() {
        BresenhamStrategy bresenham = new BresenhamStrategy();
        NaiveStrategy naive = new NaiveStrategy();
        bresenham.drawLine(matrix, 2, 2, 5, 2);
        naive.drawLine(matrix, 2, 5, 5, 5);
        assertEquals('#', matrix.getCell(2, 2));
        assertEquals('#', matrix.getCell(5, 5));
    }

    @Test
    public void testCommands() {
        TraceCommand trace = new TraceCommand(turtle, matrix, 3);
        TurnCommand turn = new TurnCommand(turtle, 90);
        MoveCommand move = new MoveCommand(turtle, matrix, 2);
        trace.execute();
        turn.execute();
        move.execute();
        assertEquals(90.0, turtle.getDirection());
        assertTrue(turtle.getX() != 10.0 || turtle.getY() != 10.0);
    }

    @Test
    public void testCompositeCommands() {
        RectangleCommand rect = new RectangleCommand(turtle, matrix, 4, 3);
        LetterSCommand letterS = new LetterSCommand(turtle, matrix, 2);
        rect.execute();
        letterS.execute();
        assertTrue(hasDrawnContent());
    }

    @Test
    public void testUndoRedo() {
        history.save(turtle, matrix);
        turtle.setPosition(5, 5);
        turtle.setDirection(180);
        assertTrue(history.undo(turtle, matrix));
        assertEquals(10.0, turtle.getX());
        assertEquals(10.0, turtle.getY());
        assertEquals(0.0, turtle.getDirection());
        assertTrue(history.redo(turtle, matrix));
        assertEquals(5.0, turtle.getX());
        assertEquals(180.0, turtle.getDirection());
    }

    // fails
    @Test
    public void testEditor() {
        TurtleGraphicsEditor editor = new TurtleGraphicsEditor();
        assertNotNull(editor);
        String input = "trace 3\nturn 90\nshow\nquit\n";
        ByteArrayInputStream is = new ByteArrayInputStream(input.getBytes());
        System.setIn(is);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(os));
        editor.start();
        System.setOut(out);
        String output = os.toString();
        assertTrue(output.contains("Welcome"));
        assertTrue(output.contains("#"));
        assertTrue(output.contains("Thank you"));
    }

    private boolean hasDrawnContent() {
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                if (matrix.getCell(j, i) == '#') {
                    return true;
                }
            }
        }
        return false;
    }
}
