package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private void btnNewCustomerClicked(ActionEvent event) {
        openNewCustomerScene(event);
    }


    private void openNewCustomerScene(ActionEvent event){
        try{
        Parent parent = FXMLLoader.load(getClass().getResource("/org/view/newCustomer.fxml"));
        Scene root = new Scene(parent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(root);
        window.show();
        }
        catch (IOException e){
            System.out.println("FXML file not found!");
        }
    }




    public void initialize() {
        // TODO
    }
}
