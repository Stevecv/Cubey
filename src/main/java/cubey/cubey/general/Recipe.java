package cubey.cubey.general;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Recipe {
    String path = null;
    public Recipe(String path) throws IOException {
        this.path = path;
    }

    Path p = Paths.get(path);
    File file = new File(path);
    String code = FileUtils.fileRead(file);

    public String getCode() {
        return code;
    }
}
