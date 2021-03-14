package samples.nio.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.Manifest;

public class ManifestManipulator {

    public static final void main(String... args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("at least the path to the JAR file expected!");
        }
        Path jarPath = Paths.get(args[0]);
        Set<String> attributeNames = args.length > 1 ? new LinkedHashSet<>(List.of(args).subList(1, args.length)) : Set.of();

        if (!attributeNames.isEmpty()) {
            ManifestManipulator manifestManipulator = new ManifestManipulator();
            manifestManipulator.removeManifestEntries(jarPath, attributeNames);
        } else {
            System.out.println("Warning: no entries specified to remove!");
        }
    }

    private void removeManifestEntries(Path jarPath, Set<String> attributeNames) throws IOException {
        System.out.println("Going to remove: " + attributeNames);
        try (FileSystem jarFS = FileSystems.newFileSystem(URI.create("jar:" + jarPath.toUri()), Map.of())) {
            Path manifestPath = jarFS.getPath("META-INF", "MANIFEST.MF");
            Manifest manifest = readManifest(manifestPath);
            Attributes mainAttributes = manifest.getMainAttributes();
            System.out.println("Found main attribute names: " + mainAttributes.keySet());

            boolean removed = mainAttributes.entrySet().removeIf(entry -> attributeNames.contains(((Name) entry.getKey()).toString()));
            if (removed) {
                writeManifest(manifestPath, manifest);
            } else {
                System.out.println("Warning: nothing removed");
            }
        }
    }

    private Manifest readManifest(Path manifestPath) throws IOException {
        try (InputStream is = Files.newInputStream(manifestPath)) {
            return new Manifest(is);
        }
    }

    private void writeManifest(Path manifestPath, Manifest manifest) throws IOException {
        try (OutputStream os = Files.newOutputStream(manifestPath)) {
            manifest.write(os);
        }
    }

}
