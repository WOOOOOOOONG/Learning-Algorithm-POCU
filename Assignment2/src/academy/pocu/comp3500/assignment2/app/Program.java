package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;

import static academy.pocu.comp3500.assignment2.Logger.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/log1.log"));

        log("시자악");

        Indent indent = Logger.indent();
        {
            Logger.indent();
            log("반갑고");
            log("zzz");
            indent.discard();
        }
        Logger.unindent();
        log("되냐되냐?");

        Logger.printTo(writer);
    }
}
