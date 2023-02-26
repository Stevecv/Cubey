package cubey.cubey.general;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Recipie {
    String path = null;
    public Recipie(String path) throws IOException {
        this.path = path;
    }

    Path p = Paths.get(path);
    File file = new File(path);
    String code = FileUtils.fileRead(file);
}
