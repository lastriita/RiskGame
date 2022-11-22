package IO;

import java.io.File;
import java.io.FileFilter;

public class MyFilter implements FileFilter {
    public boolean accept (File file)
    {
        if (file.getName().endsWith(".log"))
            return true;
        else
            return false;
    }
    public String getDescription()
    {
        return ("Filtro JPGs");
    }
}
