package cubey.cubey.general;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Recipie {
    String path = null;
    public Recipie(String path) {
        this.path = path;
    }

    Path p = Paths.get(path);

    private String getCode() throws IOException {return Files.readString(p, StandardCharsets.UTF_8);}

}
