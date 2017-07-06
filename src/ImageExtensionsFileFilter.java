
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageExtensionsFileFilter extends FileFilter {

    private final String[] approvedFileExtensions = new String[]{"jpg", "jpeg", "png", "gif"};

    @Override
    public boolean accept(File file) {
        for (String extension : approvedFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "jpg, jpeg, png, gif";
    }
}
