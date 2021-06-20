package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileFilter;

public class MainWindowLayoutController {

    @FXML
    private GridPane gridPaneFromMainWindowId;
    @FXML
    private TextField folderPathTextField;
    @FXML
    private TextArea textAreaForMessages;

    private final FileFilter wavFileFilter = file -> file.getName().toLowerCase().endsWith(".wav");
    private File[] tableOfFiles;

    public void chooseFolderButtonHandler() {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) gridPaneFromMainWindowId.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            folderPathTextField.setText(selectedDirectory.getAbsolutePath());
        }

    }

    public void searchForWavFilesButtonHandler() {

        String folderPath = folderPathTextField.getText();

        if (folderPath != null) {

            File file = new File(folderPath);
            this.tableOfFiles = file.listFiles(this.wavFileFilter);

            if (this.tableOfFiles != null && this.tableOfFiles.length > 0) {
                textAreaForMessages.appendText("Wav files found:\n");
                for (File f : tableOfFiles) {
                    textAreaForMessages.appendText(f.getName() + "\n");
                }
                textAreaForMessages.appendText("These files will be used after choosing desired button with algorithms.\n");
            }
            else {
                textAreaForMessages.appendText("No wav files in directory!\n");
            }

        }
    }

    public void normalizeVolumeButtonHandler() {

        if (this.tableOfFiles != null && this.tableOfFiles.length > 0) {
            try {
                for (File f : tableOfFiles) {
                    WavFile.normalizeVolume(f.getCanonicalPath());
                    textAreaForMessages.appendText("Normalized " + f + "\n");
                }
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            textAreaForMessages.appendText("List of files is empty, search for .wav files first\n");
        }
    }

    public void invertPolarityButtonHandler() {
        if (this.tableOfFiles != null && this.tableOfFiles.length > 0) {
            try {
                for (File f : tableOfFiles) {
                    WavFile.invertPolarity(f.getCanonicalPath());
                    textAreaForMessages.appendText("Inverted polarity on " + f + "\n");
                }
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            textAreaForMessages.appendText("List of files is empty, search for .wav files first\n");
        }
    }

    public void swapStereoButtonHandler() {
        if (this.tableOfFiles != null && this.tableOfFiles.length > 0) {
            try {
                for (File f : tableOfFiles) {
                    WavFile.swapStereo(f.getCanonicalPath());
                    textAreaForMessages.appendText("Swapped stereo on " + f + "\n");
                }
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            textAreaForMessages.appendText("List of files is empty, search for .wav files first\n");
        }
    }

    public void monophonizeButtonHandler() {
        if (this.tableOfFiles != null && this.tableOfFiles.length > 0) {
            try {
                for (File f : tableOfFiles) {
                    WavFile.monophonizeAudio(f.getCanonicalPath());
                    textAreaForMessages.appendText("Monophonized on " + f + "\n");
                }
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            textAreaForMessages.appendText("List of files is empty, search for .wav files first\n");
        }
    }

    public void helpButtonHandler() {
        try {
            Parent secondRoot = FXMLLoader.load(getClass().getResource("helpWindowLayout.fxml"));
            Stage secondStage = new Stage();

            secondStage.setScene(new Scene(secondRoot, 720, 860));
            secondStage.setTitle("Help");
            secondStage.setResizable(false);

            secondStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }


    }
}
