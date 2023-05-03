package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class BusinessCardMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BusinessCardMain.class.getResource("hello-view.fxml"));
        //Create a default pane
        Pane pane = new Pane();

        //Create button that formats the information
        Button start = new Button("Make Business Card");
        start.setLayoutX(285); start.setLayoutY(360);

        Button uploadLogo = new Button("Upload Image For Logo");

        pane.getChildren().add(uploadLogo);

        ImageView logoView = new ImageView();
        uploadLogo.setLayoutX(277.5); uploadLogo.setLayoutY(200);
        uploadLogo.setOnAction(e ->
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            File file = fileChooser.showOpenDialog(stage);

            Image logo = new Image(file.toURI().toString());
            if (logo.getWidth() !=  logo.getHeight())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Image Size");
                alert.setHeaderText(null);
                alert.setContentText("The selected image must have an 1:1 size ratio");
                alert.showAndWait();
            }
            else
            {
                logoView.setImage(logo);

                if(logo.getWidth() > 128 || logo.getHeight() > 128)
                {
                    logoView.setFitWidth(128); logoView.setFitHeight(128);
                }

                logoView.setLayoutX(286); logoView.setLayoutY(136);

                pane.getChildren().remove(uploadLogo);
                pane.getChildren().add(logoView);
            }
        });

        //Title of Program
        Text title = new Text("Business Card Formatter");
        title.setFill(Color.BLACK);
        title.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        title.setLayoutY(60);

        //determine the center of the text and display accordingly
        double textWidth = title.getBoundsInLocal().getWidth();
        double textX = 350 - (textWidth / 2);
        title.setLayoutX(textX);

        //Create the Company Name TextField and title
        TextField companyName = new TextField();
        companyName.setLayoutX(100); companyName.setLayoutY(110);
        Text companyNameTitle = new Text("Company Name :");
        companyNameTitle.setLayoutX(130); companyNameTitle.setLayoutY(100);

        //Create the Name TextField and title
        TextField name = new TextField();
        name.setLayoutX(450); name.setLayoutY(110);
        Text nameTitle = new Text("Name :");
        nameTitle.setLayoutX(500); nameTitle.setLayoutY(100);

        //Create the Phone Number TextField and title
        TextField phoneNumber = new TextField();
        phoneNumber.setLayoutX(450); phoneNumber.setLayoutY(170);
        Text phoneNumberTitle = new Text("Phone Number :");
        phoneNumberTitle.setLayoutX(480); phoneNumberTitle.setLayoutY(160);

        //Create the Website TextField and title
        TextField website = new TextField();
        website.setLayoutX(100); website.setLayoutY(170);
        Text websiteTitle = new Text("Website :");
        websiteTitle.setLayoutX(150); websiteTitle.setLayoutY(160);

        //Create the Job Title TextField and title
        TextField jobTitle = new TextField();
        jobTitle.setLayoutX(450); jobTitle.setLayoutY(230);
        Text jobTitleTitle = new Text("Job Title :");
        jobTitleTitle.setLayoutX(495); jobTitleTitle.setLayoutY(220);

        //Create the Address TextField and title
        TextField address = new TextField();
        address.setLayoutX(100); address.setLayoutY(230);
        Text addressTitle = new Text("Address :");
        addressTitle.setLayoutX(150); addressTitle.setLayoutY(220);

        //Create the Email TextField and title
        TextField email = new TextField();
        email.setLayoutX(450); email.setLayoutY(290);
        Text emailTitle = new Text("Email :");
        emailTitle.setLayoutX(505); emailTitle.setLayoutY(280);

        //Create the Extra Information TextField and title
        TextField extraInfo = new TextField();
        extraInfo.setLayoutX(100); extraInfo.setLayoutY(290);
        Text extraInfoTitle = new Text("Extra Information :");
        extraInfoTitle.setLayoutX(130); extraInfoTitle.setLayoutY(280);

        //Button action method that reads all the information and formats it correctly
        start.setOnAction(event ->
        {
            pane.getChildren().remove(logoView);
            Image background = new Image("https://i.ibb.co/qRtjwcT/business-card-3-fixed.png");
            ImageView backgroundView = new ImageView(background);

            logoView.setLayoutX(503); logoView.setLayoutY(30);

            //Create new text for the Name in the right format
            Text nameF = new Text(name.getText());
            nameF.setFill(Color.WHITE);
            nameF.setFont(Font.font("Agency FB", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 40));
            nameF.setLayoutX(42); nameF.setLayoutY(83);

            //Create new text for the Job Title in the right format
            Text jobTitleF = new Text(jobTitle.getText());
            jobTitleF.setFill(Color.WHITE);
            jobTitleF.setFont(Font.font("arial narrow bold", FontWeight.NORMAL, FontPosture.REGULAR, 25));
            jobTitleF.setLayoutX(42); jobTitleF.setLayoutY(115);

            //Create new text for the Phone Number in the right format
            Text phoneNumberF = new Text(phoneNumber.getText());
            phoneNumberF.setFill(Color.WHITE);
            phoneNumberF.setFont(Font.font("arial narrow bold", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            phoneNumberF.setLayoutX(100); phoneNumberF.setLayoutY(185);

            //Create new text for the Email in the right format
            Text emailF = new Text(email.getText());
            emailF.setFill(Color.WHITE);
            emailF.setFont(Font.font("arial narrow bold", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            emailF.setLayoutX(100); emailF.setLayoutY(240);

            //Create new text for the Website in the right format
            Text websiteF = new Text(website.getText());
            websiteF.setFill(Color.WHITE);
            websiteF.setFont(Font.font("arial narrow bold", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            websiteF.setLayoutX(100); websiteF.setLayoutY(260);

            //Create new text for the Address in the right format
            Text addressF = new Text(address.getText());
            addressF.setFill(Color.WHITE);
            addressF.setFont(Font.font("arial narrow bold", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            addressF.setWrappingWidth(210);
            addressF.setLayoutX(100); addressF.setLayoutY(305);

            //Create new text for the Extra Information in the right format
            Text extraInfoF = new Text(extraInfo.getText());
            extraInfoF.setFill(Color.BLACK);
            extraInfoF.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 18));
            extraInfoF.setWrappingWidth(250);
            extraInfoF.setTextAlignment(TextAlignment.CENTER);

            //determine the center of the text and display accordingly
            double textWidth1 = extraInfoF.getBoundsInLocal().getWidth();
            double textHeight1 = extraInfoF.getBoundsInLocal().getHeight();
            double textX1 = 567 - (textWidth1 / 2);
            double textY1 = 315 - (textHeight1 / 2);
            extraInfoF.setLayoutX(textX1);
            extraInfoF.setLayoutY(textY1);

            //Create new text for the Company Name in the right format
            Text companyNameF = new Text(companyName.getText());
            companyNameF.setFill(Color.BLACK);
            companyNameF.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            companyNameF.setLayoutY(180);

            //determine the center of the text and display accordingly
            double textWidth2 = companyNameF.getBoundsInLocal().getWidth();
            double textX2 = 567 - (textWidth2 / 2);
            companyNameF.setLayoutX(textX2);

            pane.getChildren().addAll(backgroundView, companyNameF, logoView, nameF, phoneNumberF, emailF, websiteF, addressF, jobTitleF, extraInfoF);
        });

        pane.getChildren().addAll
                (
                        start, title, companyName, companyNameTitle, name, nameTitle, phoneNumber, phoneNumberTitle, website, websiteTitle,
                        jobTitle, jobTitleTitle, address, addressTitle, email, emailTitle, extraInfo, extraInfoTitle
                );

        Scene scene = new Scene(pane, 700, 400);
        stage.setTitle("Business Card");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}