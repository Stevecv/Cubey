package cubey.cubey.general.install;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install {
    public void install() throws IOException {
        Path scriptsFolder = Paths.get("Cubey/Scripts");
        if (!Files.exists(scriptsFolder)) { Files.createDirectory(scriptsFolder); }
        Path compiled = Paths.get("Cubey/Built Scripts");
        if (!Files.exists(compiled)) { Files.createDirectory(compiled); }
    }
}
