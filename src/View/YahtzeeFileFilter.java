package View;

import javax.swing.filechooser.FileFilter;
import java.io.File;

// setups up the file type filter for the the Save and Load activities
public class YahtzeeFileFilter extends FileFilter {


    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String name = f.getName();
        String extension;

        int pointIndex = name.lastIndexOf(".");

        if (pointIndex == -1) {
            extension = null;
        } else if (pointIndex == name.length() - 1) {
            extension = null;
        } else {
            extension = name.substring(pointIndex + 1, name.length());
        }
        if (extension == null) {
            return false;
        }

        if (extension.equals("ytz")) {
            return true;
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Yahtzee Game Files Filter - .ytz";
    }
}
