package ua.com.javarush.gnew.fileManager;

import ua.com.javarush.gnew.runner.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    public List<String> read(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    public void write(Path path, List<String> lines) throws IOException {
        String content = String.join("\n", lines);
        Files.writeString(path, content);
    }

    public Path getNewPath(Path path, Command command) {
        String markerOfAction = switch (command) {
            case DECRYPT:
                yield " [DECRYPTED].txt";
            case ENCRYPT:
                yield " [ENCRYPTED].txt";
            default:
                yield ".txt";
        };

        String originalPath = path.toString();
        String newPath = originalPath.substring(0, originalPath.length() - 4) + markerOfAction;
        return Path.of(newPath);
    }

    public Path getNewPath(Path path, int key) {
        String originalPath = path.toString();
        String newPath = originalPath.substring(0, originalPath.length() - 4) + " [BRUTE_FORCE] " + key + ".txt";
        return Path.of(newPath);
    }
}