
package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.LinkedList;

import java.io.BufferedWriter;

public final class Logger {
    private static LinkedList<String> log = new LinkedList<>();
    private static String spacing = "";
    private static int indentStart;
    private static boolean bIndent;

    public static void log(final String text) {
        log.add(spacing + text + "\n");
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            for (int i = 0; i < log.getSize(); i++) {
                writer.write(log.get(i));
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static void printTo(final BufferedWriter writer, final String filter) {

    }

    public static void clear() {
        log = new LinkedList<>();
    }

    public static Indent indent() {
        Indent indent = new Indent();
        spacing += "  ";

        if (spacing.equals("  ")) {
            indentStart = log.getSize();
            bIndent = true;
        }

        return indent;
    }

    public static void unindent() {
        spacing = spacing.substring(0, spacing.length() - 2);
    }

    public static void discard() {
        if (bIndent) {
            while (indentStart < log.getSize() && log.get(indentStart).startsWith("  ")) {
                log.remove(indentStart);
            }
        }
        bIndent = false;
    }
}