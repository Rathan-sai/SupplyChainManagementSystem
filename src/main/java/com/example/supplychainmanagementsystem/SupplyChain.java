package com.example.supplychainmanagementsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Blob;
import java.util.Collection;
import java.util.Objects;

public class SupplyChain extends Application {

//using a final lengths for the pane to fit for the stage/window width, height, header.
    public static final int width = 700, height = 500, headerbar = 50;

    static boolean selectimage = false;

//using a bodypane for the loginpage, products display and details filling.
    Pane bodyPane = new Pane();
//a headerbodypane is used hear to change the header details as per our requirements as signout, search.
    Pane headerbodyPane = new Pane();
//a footerbodypane is used hear to change the header details as per our requirements as buynow, addcart.
    Pane footerbodyPane = new Pane();
    public static int bodywidth, bodyheight;
//used a login reference to access the login class functions.
    Login login = new Login();

//signup reference for the signup class functions.
    SignUp signup = new SignUp();

//addressDetails reference for the addressDetails functions.
    addressDetails addressDetails = new addressDetails();

//productdetails refrence for productdetails functions
    productdetails productdetails = new productdetails();

//A globalsignoutbutton for sign from the account login.
    Button globalsignoutButton;

//customeremailLabel is used to indicates which customer was logged in.
    Label customeremailLabel = new Label();

//customeremail a string used to store the email. address and used for our requirements.
    String customeremail = null;

//A greeting grid which will be showing in the front top header when we run out application.
    private GridPane greeting(){
//This grid contains only a empty pane with a Blue-violet color background.
        Label invitegreeting = new Label("Welcome to the PL Computers (Online Shopping).");
        invitegreeting.setFont(new Font(20));
        invitegreeting.setTextFill(Color.BLACK);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(bodyPane.getMinWidth(), headerbar);
//        gridPane.setStyle("-fx-background-color:#0000CD");

        gridPane.getChildren().add(invitegreeting);

        return gridPane;
    }

//The headbar is appeared after we login with UI controls search and sign out with a text-field for searching out product.
    private GridPane headerbar(){

        //text-field for search by product name
        TextField searchText = new TextField();

        //button to search the product by clicking it and set a action to access the producttable by our preferences.
        Button searchbutton = new Button("search");
        searchbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();
                //clear body and put this new pain which we are returning in this method from the body
                bodyPane.getChildren().clear();
                bodyPane.getChildren().addAll(productdetails.getProductsByName(productName), productsShow());
                productdetails.productTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        product.id = productdetails.productTable.getSelectionModel().getSelectedIndex()+1;
                        bodyPane.getChildren().add(productsShow());
                    }
                });
                footerbodyPane.getChildren().clear();
                footerbodyPane.getChildren().add(footerBar());
            }
        });

        //globalsignoutbutton will named as"sign out" and placed here with an action of sign out from the application and a frist grid will appear.
        globalsignoutButton = new Button("Sign out");
        globalsignoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(logincredintials());
                globalsignoutButton.setDisable(true);
                footerbodyPane.getChildren().clear();
//                footerbodyPane.setStyle("-fx-background-color:#FFFF00")
            }
        });

        //girdpane to store the UIs we created and displayed by add to the root.
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(bodyPane.getMinWidth(), headerbar);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
//        gridPane.setStyle("-fx-background-color:#6495ED");

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(searchbutton, 4, 0);
        gridPane.add(searchText, 3, 0);
        gridPane.add(globalsignoutButton, 5, 0);
        gridPane.add(customeremailLabel, 6, 0);

        return gridPane;
    }

    public GridPane productsShow(){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(width-400, height);
        Image image;
        gridPane.getChildren().clear();
        if(selectimage) {
//        Image image = new Image("C:\\Users\\RATHAN SAI\\IdeaProjects\\SupplyChainManagementSystem\\src\\main\\asus tuf.jpg");
            image = new Image(product.getImage());
//            System.out.println(product.getImage());
//        Image image = new Image("C:\\Users\\RATHAN SAI\\IdeaProjects\\SupplyChainManagementSystem\\src\\main\\java\\com\\example\\supplychainmanagementsystem\\asus tuf.jpg");

        }else{
            image = new Image("C:\\Users\\RATHAN SAI\\IdeaProjects\\SupplyChainManagementSystem\\src\\main\\resources\\explore.jpg");
//            image = new Image(product.getImage());
            selectimage = true;
        }
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(bodyPane.getMinHeight());
        imageView.setFitWidth(300);
        gridPane.getChildren().add(imageView);
//        System.out.println(selectimage);
//        System.out.println(product.id);
        gridPane.setTranslateX(400);
        return gridPane;
    }
    public GridPane productSelect(){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(width-100, height);


        gridPane.setTranslateX(400);
        Image headImage = new Image("https://www.shutterstock.com/image-vector/vector-white-shop-online-sign-600w-1734707438.jpg");
        BackgroundImage headback = new BackgroundImage(headImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1, 1, true, true, true, true));

        Background headbg = new Background(headback);
        gridPane.setBackground(headbg);
        return gridPane;
    }

    //logingcredintals which is used to login with our account are used to signup if the user was to have an account.
    public GridPane logincredintials(){
        //A label is used to display email, password and a text, and set this some preferred positions.
        Label emaillabel = new Label("Email");
        emaillabel.setTextFill(Color.WHITE);
        emaillabel.setFont(new Font(15));
        Label passwordlabel = new Label("Password");
        passwordlabel.setTextFill(Color.WHITE);
        passwordlabel.setFont(new Font(15));
        Label labelmessage = new Label("Enter you Email and password to login");
        labelmessage.setTextFill(Color.WHITE);
        labelmessage.setFont(new Font(15));

        //Textfield and Passwordfield for the login credentials.
        TextField emailText = new TextField();
        PasswordField passwordText = new PasswordField();

        //added some an actions for both login and signup button and placed them some preferred positions.
        Button loginButton = new Button("login");
        Button signupButton = new Button("SignUp");
        signupButton.setTranslateX(70);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String mail = emailText.getText();
                String password = passwordText.getText();
                if(login.customerLogin(mail, password)){
                    labelmessage.setText("Login successful");
                    customeremail = mail;
                    customeremailLabel.setText("Welcome: "+ customeremail);
                    headerbodyPane.getChildren().clear();
                    headerbodyPane.getChildren().add(headerbar());
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().addAll(productdetails.getAllProducts(), productSelect());
                    productdetails.productTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            product.id = productdetails.productTable.getSelectionModel().getSelectedIndex()+1;
                            bodyPane.getChildren().add(productsShow());
                        }
                    });
                    footerbodyPane.getChildren().clear();
                    footerbodyPane.getChildren().add(footerBar());
                }
                else{
                    labelmessage.setText("Login Failed");
                    labelmessage.setText(("Please enter valid credentials"));
                }
            }
        });

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUP());
            }
        });

//        Image image = new Image("C:\\Users\\RATHAN SAI\\IdeaProjects\\SupplyChainManagementSystem\\src\\main\\laptops.jpg");
//        ImageView boardimage = new ImageView();
//        boardimage.setImage(image);
//        boardimage.setFitWidth(bodyPane.getMinWidth());
//        boardimage.setFitHeight(bodyPane.getMinHeight());

        //girdpane to store the UIs we created and displayed by add to the root.
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(10);
        gridPane.setHgap(10);
//        gridPane.setStyle("-fx-background-color:#0000CD");
//        Image image5 = new Image("https://www.teknovatus.com/media/industry/02.jpg");
//        ImageView imageView = new ImageView(image5);
//        gridPane.setStyle("fx-background-image:url('https://www.teknovatus.com/media/industry/02.jpg')");
        gridPane.setAlignment(Pos.CENTER);

//        Image img = new Image("https://shop.metrology.zeiss.com/INTERSHOP/static/WFS/IMT-US-Site/-/IMT/en_US/TEASER/Pre_Paid_Budgets_1920x1200.jpg");
//
//        BackgroundSize backgroundSize = new BackgroundSize(1, 1, true, true, false, false);
//        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
////
//        Background bg = new Background(backgroundImage);

//        gridPane.setBackground(bg);

        gridPane.add(emaillabel, 0, 0);
        gridPane.add(emailText, 1, 0);
        gridPane.add(passwordlabel, 0, 1);
        gridPane.add(passwordText, 1, 1);
        gridPane.add(loginButton, 1,2);
        gridPane.add(signupButton, 1,2);
        gridPane.add(labelmessage, 1, 3);
//        gridPane.getChildren().addAll(boardimage);

        return gridPane;
    }

//A signUP page for the new user which are using the online shopping first time.
    public GridPane signUP(){
        //label is used for the firstname, lastname, number, email, password, confirmpassword, and inform text.
        Label first_name = new Label("First Name ");
        Label last_name = new Label("Last Name");
        Label phone_no = new Label("Phone Number ");
        Label email = new Label("Email");
        Label password = new Label("Password");
        Label confirm_password = new Label("Confirm Password");
        Label labelmessage = new Label("Please Enter Your details");

        //As per our requirement we placed them in the grid.
        first_name.setFont(new Font(15));
        first_name.setTextFill(Color.WHITE);
        last_name.setFont(new Font(15));
        last_name.setTextFill(Color.WHITE);
        phone_no.setFont(new Font(15));
        phone_no.setTextFill(Color.WHITE);
        email.setFont(new Font(15));
        email.setTextFill(Color.WHITE);
        password.setFont(new Font(15));
        password.setTextFill(Color.WHITE);
        confirm_password.setFont(new Font(15));
        confirm_password.setTextFill(Color.WHITE);
        labelmessage.setFont(new Font(15));
        labelmessage.setTextFill(Color.WHITE);

        //Add the textfield for the signup details access.
        TextField frist_nameText = new TextField();
        TextField last_nameText = new TextField();
        TextField phonenoText = new TextField();
        TextField emailText = new TextField();
        PasswordField Ppassword = new PasswordField();
        PasswordField Con_password = new PasswordField();

        //added some actions for the signup and back to login if the user is already in.
        Button signUpButton = new Button("SignUp");
        Button logInBack = new Button("Back to Login");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String in_email = emailText.getText();
                String in_password = Ppassword.getText();
                String in_con_password = Con_password.getText();
                String in_first_name = frist_nameText.getText();
                String in_last_name = last_nameText.getText();
                float in_phone_no = Float.parseFloat(phonenoText.getText());
                if(Objects.equals(in_con_password, in_password))
                {
                    if(signup.customerSignUp(in_email, in_password, in_first_name, in_last_name, in_phone_no))
                    {
                        labelmessage.setText("Login successful");
                        customeremail = in_email;
                        globalsignoutButton.setDisable(true);
                        customeremailLabel.setText("Welcome: "+ customeremail);
                        bodyPane.getChildren().clear();
                        bodyPane.getChildren().add(productdetails.getAllProducts());
                    }
                    else {
                        labelmessage.setText("signup failed");
                        labelmessage.setText("please, enter valid credentials");
                    }
                }else if(in_email.isEmpty()){
                    labelmessage.setText("please, enter an email.");
                }else if(in_first_name.isEmpty()){
                    labelmessage.setText("please, enter an first name.");
                }else if(in_last_name.isEmpty()){
                    labelmessage.setText("please, enter an last name.");
                }else if(in_con_password.isEmpty()){
                    labelmessage.setText("please, enter an confirm password.");
                }else if(in_password.isEmpty()){
                    labelmessage.setText("please, enter an password.");
                }else if(Float.toString(in_phone_no).length()<10){
                    labelmessage.setText("please, enter an phone number.");
                }
            }
        });
        //logback if already a user.
        logInBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(logincredintials());
            }
        });

        logInBack.setTranslateX(-80);

        //girdpane to store the UIs we created and displayed by add to the root.
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(10);
        gridPane.setHgap(10);
//        gridPane.setStyle("-fx-background-color:#0000CD");

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(first_name, 0, 0);
        gridPane.add(last_name, 0, 1);
        gridPane.add(phone_no, 0, 2);
        gridPane.add(email, 0, 3);
        gridPane.add(password, 0, 4);
        gridPane.add(confirm_password, 0, 5);
        gridPane.add(frist_nameText, 1, 0);
        gridPane.add(last_nameText, 1, 1);
        gridPane.add(phonenoText, 1, 2);
        gridPane.add(emailText, 1, 3);
        gridPane.add(Ppassword, 1, 4);
        gridPane.add(Con_password, 1, 5);
        gridPane.add(labelmessage, 1,6);
        gridPane.add(signUpButton, 1, 7);
        gridPane.add(logInBack, 2, 7);

        return gridPane;
    }

    //Footerbar which is placed at the bottom of the root which contains some UIs like buy add-cart.
    private GridPane footerBar(){
        //labels and buttons for the buy, add-cart and my cart to store out products.
        Button addToCartButton = new Button("Add to Cart");
        Button buynowbutton = new Button("Buy Now");
        Button myCartButton = new Button("My Cart");
        Label messageLabel = new Label();

        //added action to the buynow and addcart button.
        buynowbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product selectedProduct = productdetails.getSelectProduct();
                if(selectedProduct != null){
                    if(order.placeOrder(customeremail, selectedProduct)){
                        bodyPane.getChildren().clear();
                        bodyPane.getChildren().add(customerAddress());
                        footerbodyPane.getChildren().clear();
//                        footerbodyPane.setStyle("-fx-background-color:#6495ED");
                    }
                }
                else {
                    messageLabel.setText("Select a Product.");
                }
            }
        });
        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product selectedProduct = productdetails.getSelectProduct();
                messageLabel.setText("Added to MyCart.");
                productdetails.cartproducts(selectedProduct);
            }
        });
        myCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productdetails.mycartproducts());
            }
        });

        //girdpane to store the UIs we created and displayed by add to the root.
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(bodyPane.getMinWidth(), headerbar+10);
        gridPane.setHgap(10);
        gridPane.setVgap(40);
//        gridPane.setStyle("-fx-background-color:#6495ED");

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(addToCartButton, 0, 0);
        gridPane.add(buynowbutton, 1, 0);
        gridPane.add(messageLabel, 0, 0);
        messageLabel.setTranslateX(300);
        gridPane.add(myCartButton, 0, 0);
        myCartButton.setTranslateX(-250);

        return gridPane;
    }

    //After we select the product we wanted place the order in certain location this customer address will be used to where we have to place our oder.
    public GridPane customerAddress()
    {
        //used some Label as address, door-no, street-name, city, district, state.
        Label address = new Label("Address");
        Label house_no = new Label("House/Door no :");
        Label street = new Label("Street Name :");
        Label city = new Label("city/village :");
        Label district = new Label("District :");
        Label state = new Label("State :");

        //parallel text-fields for each label.
        TextField house_noText = new TextField();
        TextField streetText = new TextField();
        TextField cityText = new TextField();
        TextField districtText = new TextField();
        TextField stateText = new TextField();

        //used two buttons for order confirm and cancel and added some actions to them.
        Button orderconfirm = new Button("order confirm");
        Button cancel = new Button("Cancel");
        cancel.setTranslateX(100);
        orderconfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(finalPrint());
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productdetails.getAllProducts());
                footerbodyPane.getChildren().clear();
                footerbodyPane.getChildren().add(footerBar());
            }
        });

        //girdpane to store the UIs we created and displayed by add to the root.
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Image img = new Image("https://cdn.pixabay.com/photo/2021/02/25/12/03/courier-6048941__340.png");

        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(1, 1, true, true, false, true));

        Background bg = new Background(backgroundImage);

//        gridPane.getStylesheets().addAll(Objects.requireNonNull(getClass().getResource("/css/app.css")).toExternalForm());
//        gridPane.setStyle("-fx-background-url('https://thumbs.dreamstime.com/b/shopping-cart-ecommerce-marketing-channel-distribution-concept-supermarket-background-shopping-cart-ecommerce-marketing-channel-142901304.jpg')");

        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setPadding(new Insets(100, 10, 10, 10));
        gridPane.add(address, 0, 0);
        gridPane.add(house_no, 0, 1);
        gridPane.add(street, 0, 2);
        gridPane.add(city, 0, 3);
        gridPane.add(district, 0, 4);
        gridPane.add(state, 0, 5);
        gridPane.add(house_noText, 1, 1);
        gridPane.add(streetText, 1, 2);
        gridPane.add(cityText, 1, 3);
        gridPane.add(districtText, 1, 4);
        gridPane.add(stateText, 1, 5);
        gridPane.add(orderconfirm, 1, 6);
        gridPane.add(cancel, 1, 6);
        gridPane.setBackground(bg);

        return gridPane;
    }

    //final greeting girdpane to display after order placed.
    public GridPane finalPrint(){
        Label confirm_message = new Label("                Your order is conformed. \n Thank-you for shopping with PL computers \n                    shop with us again...");
        confirm_message.setFont(new Font("Cambria",30));
        confirm_message.setTextFill(Color.YELLOW);
        confirm_message.setWrapText(true);

        Image image = new Image("https://cdn.pixabay.com/photo/2017/07/10/16/07/thank-you-2490552__340.png");
        BackgroundImage bgi = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false));
        Background bg = new Background(bgi);
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(width, height);
//        gridPane.setStyle("-fx-background-color:#6495ED");
        gridPane.setBackground(bg);
        gridPane.setAlignment(Pos.CENTER);
//        gridPane.add(confirm_message, 0, 0);

        bodyPane.getChildren().clear();

        return gridPane;
    }

    //createContent pane which consists of all the pane that we are designed.
    public Pane CreateContent() throws FileNotFoundException {
        Pane root = new Pane();

        root.setPrefSize(width, height+2*headerbar);

        bodyPane.setMinSize(width, height+10);
        bodyPane.setTranslateY(headerbar);
        Image bodyImage = new Image("https://cdn.pixabay.com/photo/2018/07/26/09/56/ecommerce-3563183__340.jpg");
        BackgroundImage bodyimg = new BackgroundImage(bodyImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                                            new BackgroundSize(1, 1, true, true, false, false));
        Background bodybg = new Background(bodyimg);
        bodyPane.setBackground(bodybg);

        Image headImage = new Image("https://www.shutterstock.com/image-vector/vector-white-shop-online-sign-600w-1734707438.jpg");
        BackgroundImage headback = new BackgroundImage(headImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                    new BackgroundSize(1, 1, true, true, true, true));

        Background headbg = new Background(headback);

        headerbodyPane.setPrefSize(bodyPane.getMinWidth(), headerbar);
        headerbodyPane.getChildren().add((greeting()));
//        headerbodyPane.setStyle("-fx-background-color:\t#F5F5DC");
        headerbodyPane.setBackground(headbg);

        footerbodyPane.setPrefSize(bodyPane.getMinWidth(), headerbar+10);
        footerbodyPane.setTranslateY(height+headerbar);
//        footerbodyPane.setStyle("-fx-background-color:#FFFF00");
//        footerbodyPane.getChildren().add(footerBar());
        footerbodyPane.setBackground(headbg);

//        bodyPane.getChildren().addAll(productdetails.getAllProducts(), productsShow());
//        bodyPane.getChildren().addAll(signUP());
//        bodyPane.prefWidthProperty().bind(stack);
        bodyPane.getChildren().add(logincredintials());
//        bodyPane.getChildren().add(customerAddress());
//        bodyPane.getChildren().add(productsShow());
//        bodyPane.getChildren().add(finalPrint());
//        product.getImage();
        root.getChildren().addAll(bodyPane, footerbodyPane);
        root.getChildren().add(headerbodyPane);
//        root.getChildren().add(headerbar());
//        root.getChildren().addAll(footerBar())
//        productdetails.productTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                product.id = productdetails.productTable.getSelectionModel().getSelectedIndex()+1;
//                bodyPane.getChildren().addAll(productdetails.getAllProducts(), productsShow());
//                bodyPane.getChildren().clear();
//            }
//        });
        return root;
    }

    public GridPane demo(){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(width, height+2*headerbar);
        Image img = new Image("https://www.ncra-usa.org/portals/68/Images/store-hero.jpg");

        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(100, 100, true, true, true, true));
//
        Background bg = new Background(backgroundImage);

//        gridPane.setStyle("-fx-background:url('http://duke.kenai.com/wave/Wave.jpg')");

        gridPane.setBackground(bg);

        return gridPane;
    }

    //start main function which consists a stage and screen.
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SupplyChain.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(CreateContent());
        stage.setTitle("PL COMPUTERS (Online Shopping...!)");
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://cdn.pixabay.com/photo/2015/04/18/07/49/shopping-cart-728430__340.png"));
        stage.show();
    }
    //launch's out stage with what we are placed.
    public static void main(String[] args) {
        launch();
    }
}