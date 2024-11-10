
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class MainApp extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        showLoginScreen(primaryStage);
//    }
//
//    private void showLoginScreen(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/login.fxml"));
//        Scene loginScene = new Scene(loader.load(), 600, 400); // Set desired size for login screen
//        stage.setScene(loginScene);
//        stage.setTitle("BookingBreeze Login");
//        stage.setResizable(false); // Prevent resizing
//        stage.show();
//    }
//
//    public void showSignupScreen(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/signup.fxml"));
//        Scene signupScene = new Scene(loader.load(), 600, 400); // Set desired size for signup screen
//        stage.setScene(signupScene);
//        stage.setTitle("BookingBreeze Signup");
//        stage.setResizable(false); // Prevent resizing
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
