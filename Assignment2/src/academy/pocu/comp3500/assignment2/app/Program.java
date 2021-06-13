package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;
import academy.pocu.comp3500.assignment2.datastructure.Sort;

import static academy.pocu.comp3500.assignment2.Logger.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Program {
    private static final int CHAR_LEFT_LIMIT = 48;
    private static final int CHAR_RIGHT_LIMIT = 122;

    public static void main(String[] args) {
        // write your code here
        try {
            exampleCodes();
            mainTest();
            kkrTest();
            dorasimaTest();
            rokTest();
            sehyTest();
            testD();
            testF();
            testG();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testD() throws IOException {
        Random random = new Random();
        {
            System.out.print("D00_Level1LogOnce: ");
            Logger.clear();

            for (int testCount = 0; testCount < 1000; ++testCount) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/d00.txt"));
                String logString = generateRandomString(random.nextInt(32) + 1);

                Logger.indent();
                {
                    log(logString);
                }
                Logger.unindent();

                Logger.printTo(writer);

                writer.close();

                Scanner reader = new Scanner(new File("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/d00.txt"));
                int counter = 0;
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    ++counter;
                    assert (line.equals("  " + logString));
                }

                assert (counter == 1);

                reader.close();
            }

            System.out.println("SUCCESS");
        }

        {
            System.out.print("D01_Level1MultiLogs: ");

            for (int testCount = 0; testCount < 1000; ++testCount) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/d01.txt"));
                Logger.clear();
                ArrayList<String> logStrings = new ArrayList<>();
                int logsCount = random.nextInt(256);

                Logger.indent();
                {
                    for (int i = 0; i < logsCount; ++i) {
                        String generatedString = generateRandomString(random.nextInt(32) + 1);
                        logStrings.add(generatedString);
                        log(generatedString);
                    }
                }
                Logger.unindent();

                Logger.printTo(writer);

                writer.close();

                Scanner reader = new Scanner(new File("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/d01.txt"));
                int counter = 0;
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    assert (line.equals("  " + logStrings.get(counter)));
                    ++counter;
                }

                assert (counter == logsCount);

                reader.close();
            }

            System.out.println("SUCCESS");
        }
    }

    private static void testF() throws IOException {
        Random random = new Random();
        long start = System.nanoTime();
        {
            System.out.print("F00_MixedLevelTests1: ");
//            System.out.print(System.lineSeparator());

            for (int testCount = 0; testCount < 1000; ++testCount) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/f00.txt"));
                Logger.clear();
//                System.out.println("START=====================================");
                int logCount = random.nextInt(32);
                ArrayList<String> logStrings = new ArrayList<>();
                ArrayList<Integer> indentLevels = new ArrayList<>();
                ArrayList<Boolean> brackets = new ArrayList<>();
                int currentIndentLevel = 0;

                Indent currentIndent = null;
                int discardLevel = -1;
                while (logStrings.size() < logCount) {
                    boolean isIndent = random.nextInt(10) == 0;
                    boolean isUnindent = currentIndentLevel > 0 && currentIndent != null && random.nextInt(10) == 0;

                    if (isIndent) {
//                        System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "INDENT");
                        ++currentIndentLevel;
                        currentIndent = Logger.indent();
                        brackets.add(true);
                    } else {
                        brackets.add(false);
                    }

                    String generatedString = generateRandomString(random.nextInt(32));
                    log(generatedString);
                    logStrings.add(generatedString);

                    if (discardLevel != -1) {
                        indentLevels.add(-1);
                    } else {
                        indentLevels.add(currentIndentLevel);
                    }

//                    if (discardLevel != -1) {
//                        System.out.print("# ");
//                    }
//                    System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + generatedString + " " + indentLevels.get(indentLevels.size() - 1));

                    boolean isDiscarded = currentIndent != null && currentIndentLevel > 0 && indentLevels.get(indentLevels.size() - 1) != -1 && random.nextInt(25) == 0;
                    if (isDiscarded) {
//                        System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "DISCARDED");
                        discardLevel = currentIndentLevel;
                        int index = indentLevels.size() - 1;
                        int pivotIndentLevel = indentLevels.get(indentLevels.size() - 1);
                        while (index >= 0 && indentLevels.get(index) >= pivotIndentLevel) {
//                            System.out.printf("[%d]: %s, %s %s", index, logStrings.get(index), (brackets.get(index) ? "true" : "false"), System.lineSeparator());
                            indentLevels.set(index, -1);
                            if (brackets.get(index)) {
                                break;
                            }
                            --index;
                        }
                        currentIndent.discard();
                    }

                    if (isUnindent) {
                        if (discardLevel == currentIndentLevel) {
                            discardLevel = -1;
                        }
                        --currentIndentLevel;
                        currentIndent = null;
//                        System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "UNINDENT");
                        Logger.unindent();
                    }
                }

                while (currentIndentLevel > 0) {
                    --currentIndentLevel;
//                    System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "UNINDENT");
                    Logger.unindent();
                }

                Logger.printTo(writer);

                writer.close();

                Scanner reader = new Scanner(new File("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/f00.txt"));
//                System.out.println("TEST=====================================");
                int counter = 0;

                while (reader.hasNextLine()) {
//                    System.out.printf("%d-%d: ", counter, indentLevels.get(counter));

                    if (counter < indentLevels.size() &&  indentLevels.get(counter) != -1) {
                        String assertString = "  ".repeat(Math.max(0, indentLevels.get(counter))) +
                                logStrings.get(counter);
                        String line = reader.nextLine();
//                        System.out.println("test: " + assertString + (brackets.get(counter) ? " true" : " false"));
//                        System.out.printf("%d-%d: ", counter, indentLevels.get(counter));
//                        System.out.print("line: " + line);
                        assert (line.equals(assertString));
                    }
                    ++counter;

                }

                reader.close();
            }

            System.out.println("SUCCESS");
        }
        long finish = System.nanoTime();

        System.out.println("Elapsed time: " + ((finish - start) / (1000.0 * 1000.0)) + " ms");
    }

    private static void testG() throws IOException {
        Random random = new Random();
        long start = System.nanoTime();
        {
            System.out.print("G00_PrintToFiltered1: ");
//            System.out.print(System.lineSeparator());

            for (int testCount = 0; testCount < 1000; ++testCount) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/g00.txt"));
                Logger.clear();
//                System.out.println("START=====================================");
                int logCount = random.nextInt(32);
                String filter = "filter";
                ArrayList<String> logStrings = new ArrayList<>();
                ArrayList<Integer> indentLevels = new ArrayList<>();
                ArrayList<Boolean> brackets = new ArrayList<>();
                ArrayList<Boolean> containsFilter = new ArrayList<>();
                int currentIndentLevel = 0;

                Indent currentIndent = null;
                int discardLevel = -1;
                while (logStrings.size() < logCount) {
                    boolean isIndent = random.nextInt(10) == 0;
                    boolean isUnindent = currentIndentLevel > 0 && currentIndent != null && random.nextInt(10) == 0;

                    if (isIndent) {
//                        System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "INDENT");
                        ++currentIndentLevel;
                        currentIndent = Logger.indent();
                        brackets.add(true);
                    } else {
                        brackets.add(false);
                    }

                    String generatedString = generateRandomString(random.nextInt(32));

                    if (generatedString.contains(filter)) {
                        containsFilter.add(true);
                    } else {
                        if (random.nextBoolean()) {
                            containsFilter.add(true);
                            generatedString += " " + filter;
                        } else {
                            containsFilter.add(false);
                        }
                    }
                    log(generatedString);
                    logStrings.add(generatedString);

                    if (discardLevel != -1) {
                        indentLevels.add(-1);
                    } else {
                        indentLevels.add(currentIndentLevel);
                    }

//                    if (discardLevel != -1) {
//                        System.out.print("# ");
//                    }
//                    System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + generatedString + " " + indentLevels.get(indentLevels.size() - 1));

                    boolean isDiscarded = currentIndent != null && currentIndentLevel > 0 && indentLevels.get(indentLevels.size() - 1) != -1 && random.nextInt(25) == 0;
                    if (isDiscarded) {
//                        System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "DISCARDED");
                        discardLevel = currentIndentLevel;
                        int index = indentLevels.size() - 1;
                        int pivotIndentLevel = indentLevels.get(indentLevels.size() - 1);
                        while (index >= 0 && indentLevels.get(index) >= pivotIndentLevel) {
//                            System.out.printf("[%d]: %s, %s %s", index, logStrings.get(index), (brackets.get(index) ? "true" : "false"), System.lineSeparator());
                            indentLevels.set(index, -1);
                            if (brackets.get(index)) {
                                break;
                            }
                            --index;
                        }
                        currentIndent.discard();
                    }

                    if (isUnindent) {
                        if (discardLevel == currentIndentLevel) {
                            discardLevel = -1;
                        }
                        --currentIndentLevel;
                        currentIndent = null;
//                        System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "UNINDENT");
                        Logger.unindent();
                    }
                }

                while (currentIndentLevel > 0) {
                    --currentIndentLevel;
//                    System.out.println("__".repeat(Math.max(0, currentIndentLevel)) + "UNINDENT");
                    Logger.unindent();
                }

                Logger.printTo(writer, filter);

                writer.close();

                Scanner reader = new Scanner(new File("Assignment2/src/academy/pocu/comp3500/assignment2/app/test/g00.txt"));
//                System.out.println("TEST=====================================");
                int counter = 0;
                while (reader.hasNextLine()) {
//                    System.out.printf("%d-%d: ", counter, indentLevels.get(counter));

                    if (indentLevels.get(counter) != -1 && containsFilter.get(counter)) {
                        String assertString = "  ".repeat(Math.max(0, indentLevels.get(counter))) +
                                logStrings.get(counter);
                        String line = reader.nextLine();
//                        System.out.println("test: " + assertString + (brackets.get(counter) ? " true" : " false"));
//                        System.out.printf("%d-%d: ", counter, indentLevels.get(counter));
//                        System.out.print("line: " + line);
                        assert (line.equals(assertString));
                    }
//                    System.out.println();
                    ++counter;
                }

                reader.close();
            }

            System.out.println("SUCCESS");
        }
        long finish = System.nanoTime();

        System.out.println("Elapsed time: " + ((finish - start) / (1000.0 * 1000.0)) + " ms");
    }

    private static void mainTest() throws IOException {
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog1.log"));

            log("hello");
            log("world");
            log("this is logging at the top level");

            Logger.indent();
            {
                log("using indent, you can indent to organize your logs");
                log("call unindent() to decrease the indentation level");
            }
            Logger.unindent();

            Indent indent = Logger.indent();
            {
                log("whatever I say here");
                log("is discarded!");
                log("too bad!");

                indent.discard();
            }
            Logger.unindent();

            Logger.indent();
            {
                log("this won't be discarded");
                log("it's true!");

                doMagic();
            }
            Logger.unindent();

            log("back to the top level!");
            log("and let's print the logs");

            Logger.printTo(writer);

            Logger.clear();

            log("log was just cleared");
            log("so you start logging from the top level again");

            Logger.printTo(writer);

            writer.close();
        }

        Logger.clear();

        {
            final BufferedWriter writer1 = new BufferedWriter(new FileWriter("quicksort1.log"));
            final BufferedWriter writer2 = new BufferedWriter(new FileWriter("quicksort2.log"));

            int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

            Sort.quickSort(nums);

            Logger.printTo(writer1);

            Logger.printTo(writer2, "90");

            writer1.close();
            writer2.close();
        }
    }

    private static void exampleCodes() throws IOException {
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog1.log"));

            int[] nums = new int[]{1, 2, 3, 4};

            log("call sum()");
            int sum = sum(nums);

            log("call average()");
            double average = calculateAverage(nums);

            Logger.printTo(writer);

            writer.close();
        }

        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog2.log"));

            log("this is not indented");

            Logger.indent();
            {
                log("but this is");
                log("so is this");
            }
            Logger.unindent();

            log("but not this");
            Logger.printTo(writer);

            writer.close();
        }

        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog3.log"));

            log("first level 1");

            Logger.indent();
            {
                log("second level 1");
                log("second level 2");

                doMagicInExampleCode();

                log("second level 3");
            }
            Logger.unindent();

            log("first level 2");
            Logger.printTo(writer);

            writer.close();
        }

        {
            final BufferedWriter writer1 = new BufferedWriter(new FileWriter("quicksort1.log"));

            int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

            Sort.quickSort(nums);

            Logger.printTo(writer1);

            writer1.close();
        }

        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog4.log"));

            int x = 10;

            log("first level 1");

            Indent indent = Logger.indent();
            {
                log("second level 1");
                log("second level 2");

                if (x % 2 == 0) {
                    indent.discard();
                }
            }
            Logger.unindent();

            log("first level 2");
            Logger.printTo(writer);

            writer.close();
        }

        {
            final BufferedWriter writer1 = new BufferedWriter(new FileWriter("quicksort1.log"));

            int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

            Sort.quickSort(nums);

            Logger.printTo(writer1);
            // quicksort1.log
            /*
            30 10 80 90 50 70 40
              L: 30 10 40
                L: 10
                R: 30
                X: 10 30
              R: 90 50 70 80
                L: 50 70 80
                  L: 50 70
                  R:
                  X: 50 70
                R: 90
                X: 50 70 80 90
              X: 10 30 40 50 70 80 90
            */

            writer1.close();
        }

        {
            final BufferedWriter writer2 = new BufferedWriter(new FileWriter("quicksort2.log"));

            int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

            Sort.quickSort(nums);

            Logger.printTo(writer2, "90");
            //
            /*
            30 10 80 90 50 70 40
              R: 90 50 70 80
                R: 90
                X: 50 70 80 90
              X: 10 30 40 50 70 80 90
            */

            writer2.close();
        }
    }

    private static void kkrTest() {
        try {
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter("kkrlog1.log"));
                log("out 1");
                Indent indent = Logger.indent();
                {
                    log("in 1-in");
                    Logger.indent();
                    {
                        log("in 2-in");
                        Logger.indent();
                        {
                            log("in 3");
                        }
                        Logger.unindent();
                        log("in 2-out");
                    }
                    Logger.unindent();
                    log("in 1-out1");
                    indent.discard();
                    log("in 1-out2");
                }
                Logger.unindent();
                log("out 2");
                Logger.printTo(writer);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dorasimaTest() {
        try {
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter("dorasimalog1.log"));

                Indent indent1;
                Indent indent2;
                Indent indent3;
                Indent indent4;

                log("this");
                Logger.indent();
                {
                    log("will");
                    indent1 = Logger.indent();
                    {
                        log("(invisible1)");
                        log("(invisible2)");
                        log("(invisible3)");
                        Logger.indent();
                        {
                            log("be");
                            Logger.indent();
                            {
                                log("enough");
                                Logger.indent();
                                {
                                    log("to");
                                    indent2 = Logger.indent();
                                    {
                                        log("(invisible1)");
                                        log("(invisible2)");
                                        log("(invisible3)");
                                    }
                                    Logger.unindent();
                                    log("deepLogs");
                                    Logger.indent();
                                    {
                                        log("case");
                                        indent3 = Logger.indent();
                                        {
                                            log("(invisible1)");
                                            log("(invisible2)");
                                            log("(invisible3)");
                                        }
                                        Logger.unindent();
                                        log("am I");
                                    }
                                    Logger.unindent();
                                    log("right?");
                                }
                                Logger.unindent();
                                log("12345678");
                                indent4 = Logger.indent();
                                {
                                    Logger.indent();
                                    {
                                        Logger.indent();
                                        {
                                            log("XX");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                indent1.discard();
                indent2.discard();
                indent3.discard();
                indent4.discard();
                Logger.printTo(writer);

                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rokTest() throws IOException {
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("roklog1.log"));
            log("first level 1");
            Indent indent1 = Logger.indent();
            {
                log("second level 1");
                Indent indent2 =Logger.indent();
                {
                    log("third level 1");
                    Indent indent3 =Logger.indent();
                    {
                        log("fourth level 1");
                    }
                    Logger.unindent();
                }
                Logger.unindent();
            }
            Logger.unindent();
            indent1.discard();
            log("first level 2");
            Logger.printTo(writer);
            writer.close();
        }
    }

    private static void sehyTest() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("sehylog1.log"));

        Logger.indent();
        {
            Logger.indent();
            {
                Logger.log("sub log");
            }
            Logger.unindent();
        }
        Logger.unindent();

        Logger.printTo(writer);

        writer.close();
    }

    private static void doMagicInExampleCode() {
        Logger.indent();
        {
            log("third level 1");
            log("third level 2");
        }
        Logger.unindent();
    }

    private static void doMagic() {
        Indent indent = Logger.indent();
        {
            log("you can also nest an indent");
            log("like this!");
        }
        Logger.unindent();
    }

    private static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            log(String.format("sum + %d", num));
            sum += num;
            log(String.format("sum: %d", sum));
        }

        log(String.format("return sum: %d", sum));
        return sum;
    }

    private static double calculateAverage(int[] nums) {
        log("call sum()");
        int sum = sum(nums);

        log(String.format("sum / nums.length: %d / %d", sum, nums.length));
        double average = sum / (double) nums.length;

        log(String.format("return average: %f", average));
        return average;
    }

    private static String generateRandomString(int length) {
        return new Random().ints(CHAR_LEFT_LIMIT, CHAR_RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}