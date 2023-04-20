package org.sct.jktor.cli;

import java.nio.file.Path;
import org.sct.jktor.FileGroup;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * Simple command line tool for JKtor.
 */
@Command(mixinStandardHelpOptions = true, name = "ktor", description = "Manipulating classifiers hierarchy.")
public class JKtorCLI {
    public static void main(String[] args) {
        new CommandLine(new JKtorCLI()).execute(args);
    }

    @Command
    void list(@Parameters(paramLabel = "<path>") Path path) {
        new FileGroup(path).all().forEach(
            System.out::println
        );
    }
}
