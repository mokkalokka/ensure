package models.gui;

import javafx.scene.control.Alert;

public class ErrorDialog {

    Alert alert;
    private String title;
    private String message;
    boolean isCritical;

    public ErrorDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public ErrorDialog(String title, boolean isCritical) {
        this.title = title;
        message = "En alvorlig feil skjedde! Hvis feilen gjentar seg, prøv å start programmet på nytt.";
        this.isCritical = isCritical;
    }


    public ErrorDialog(String title, String message, boolean isCritical) {
        this.title = title;
        this.message = message;
        this.isCritical = isCritical;
    }

    public void show() {
        if (isCritical) {
            alert = new Alert(Alert.AlertType.WARNING);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
