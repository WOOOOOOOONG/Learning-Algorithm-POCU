
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
                writer.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static void printTo(final BufferedWriter writer, final String filter) {
        try {
            /*
            boolean bPrint = false;
            for (int i = 0; i < log.getSize(); i++) {
                if (!log.get(i).startsWith("  ")) {
                    if (log.get(i).contains(filter)) {
                        writer.write(log.get(i));
                        bPrint = true;
                    }
                }
                if (bPrint) {
                    boolean bBackIndent = false;
                    while (i + 1 < log.getSize() && log.get(i + 1).startsWith("  ")) {
                        bBackIndent = true;
                        i++;
                        System.out.print(log.get(i));
                        writer.write(log.get(i));
                    }
                    if (bBackIndent) {
                        i--;
                    }
                    bPrint = false;
                }
            }
            */
            for (int i = 0; i < log.getSize(); i++) {
                if (log.get(i).contains(filter)) {
                    writer.write(log.get(i));
                    writer.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
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