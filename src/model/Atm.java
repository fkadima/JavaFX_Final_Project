package model;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Atm extends Application {
    //Stage loginStage;
    Scene loginScene, applicationScene, checkBalanceScene, makeDepositScene, makeWithdrawalScene, balanceTranferScene, transactionsScene;
    
    String checkingAccountNumber = "";
    String savingsAccountNumber = "";
    String checkActBalance = "";
    String saveActBalance = "";
    String depositAmount = "";
    String transferAmount = "";
    double withdrawAmount = 0.0;
    Label checkingAccountBalanceLabel = null;
    Label savingsAccountBalanceLabel = null;
    
    boolean isCheckingAccount = false;
    CheckingAccount checkingAccount = new CheckingAccount();
    SavingsAccount savingsAccount = new SavingsAccount();
    
    List<Transaction> transactions = new ArrayList<Transaction>();
    
    User user = new User();
    @Override
    public void start(Stage primaryStage) {
        
        try {
            primaryStage.setTitle("ATM Systems Services");
            
            loginScene = loginToATM(primaryStage);
                        
            depositTransaction(primaryStage);
            balanceTransfer(primaryStage);
            //transactionsDisplay(primaryStage);
            
            // Withdrawal Scene begin
            withdraw(primaryStage);
            
            primaryStage.setResizable(false);
            // Setting and showing primary Scene
            primaryStage.setScene(loginScene);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private Scene loginToATM(Stage primaryStage) {
		GridPane titlePane = new GridPane();
		GridPane pane = new GridPane();
		
		VBox items = new VBox();
		HBox vbButtons = new HBox();
		items.setStyle("-fx-background-color: beige");
		//pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER);
		
		Label appTitle = new Label(" ATM Systems Services Inc.");
		appTitle.setStyle("-fx-font-weight: bold");
		appTitle.setFont(new Font("Cambria", 25));
		
		Label usernameLbl = new Label("Username");
		Label passwordLbl = new Label("Password");
		TextField usernameTf = new TextField("fkadima@test.com");
		PasswordField passwordTf = new PasswordField();
		
		Label message = new Label("");
		message.setTextFill(Color.web("#ff0000"));
		message.setVisible(false);
		
		Button loginBtn = new Button("Login");
		Button cancelBtn = new Button("Cancel");
		Button exitBtn = new Button("Exit");
		
		titlePane.add(appTitle, 1, 10);
		titlePane.setAlignment(Pos.CENTER);
		titlePane.setVgap(15);
		
		vbButtons.setSpacing(10);
		vbButtons.setPadding(new Insets(0, 20, 10, 20));
		vbButtons.getChildren().addAll(loginBtn, cancelBtn, exitBtn);
		
		pane.add(usernameLbl, 0, 0);
		pane.add(passwordLbl, 0, 1);
		pane.add(usernameTf, 1, 0);
		pane.add(passwordTf, 1, 1);
		pane.add(vbButtons, 1, 2);
		pane.add(message, 1, 3);
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
//                    
		        if (usernameTf.getText().isEmpty() || passwordTf.getText().isEmpty()) {
		            message.setText("Username/Password cannot be empty.");
		            message.setVisible(true);
		            System.out.println("Username/Password cannot be empty.");
		        } else {
		            System.out.println("Fields were not empty.");
		            System.out.println("Login was successful");
		            
		            Functionality f1 = new Functionality();
		            
		            user = f1.login(usernameTf.getText(), passwordTf.getText());
		            
		            checkingAccountNumber = user.getUserCheckingAccount().getAccountNumber();
		            savingsAccountNumber = user.getUserSavingsAccount().getAccountNumber();
		            
		            checkActBalance = Double.toString(user.getUserCheckingAccount().getAccountBalance());
		            saveActBalance = Double.toString(user.getUserSavingsAccount().getAccountBalance());
		            
		            System.out.println(user.toString());
		            
		            formElement(primaryStage);
		            
					/*
					 * if(user != null) { transactionsDisplay(primaryStage); }
					 */		            		            
		            
		            primaryStage.setScene(applicationScene);
		            
		        }
		    }
		});
		
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		        message.setVisible(false);
		        usernameTf.clear();
		        passwordTf.clear();
		        System.out.println("Fields were cleared.");
		    }
		});
		
		exitBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		        primaryStage.close();
		        System.out.println("Closed application.");
		    }
		});
		            
		items.getChildren().addAll(titlePane, pane);            
		
		Scene loginScene = new Scene(items, 400, 400);
		return loginScene;
	}

	public void withdraw(Stage primaryStage) {
		VBox withdraw = new VBox();
		withdraw.setStyle("-fx-background-color: beige");
		HBox withdrawHbox = new HBox();
		
		makeWithdrawalScene = new Scene(withdraw, 400, 400);
		withdraw.setSpacing(20);
		Label lable = new Label("Withdraw Money");
		lable.setFont(new Font("Cambria", 20));
		lable.setStyle("-fx-font-weight: bold");
		
		Label label3 = new Label("How much do you want to withdraw?");
		
		TilePane tilePane = new TilePane();
		TilePane tilePane2 = new TilePane();
		
		Label option = new Label("Select account to withdraw from:");
		Label selection = new Label("Please select an account");
		Label accountSelected = new Label();
		
		ToggleGroup tg = new ToggleGroup();
		
		RadioButton checking = new RadioButton("Checking Account");
		RadioButton savings = new RadioButton("Savings Account");
		
		checking.setToggleGroup(tg);
		savings.setToggleGroup(tg);
		
		tilePane2.getChildren().add(option);
		tilePane.getChildren().addAll(checking, savings);
		tilePane2.getChildren().add(selection);
		
		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		    public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
     
		        RadioButton rb = (RadioButton)tg.getSelectedToggle();
     
		        if (rb != null) {
		            String s = rb.getText();
		            
		            if (s == "Checking Account") {
		            	accountSelected.setText(user.getUserCheckingAccount().getAccountNumber());
		            } else {
		            	accountSelected.setText(user.getUserSavingsAccount().getAccountNumber());
		            }
     
		            // change the label
		            selection.setStyle("-fx-font-weight: bold");
		            selection.setText(s + " selected: " + accountSelected.getText());
		        }
		    }
		});
		
		TextField withdrawalAmountTf = new TextField();
		
		Button confirmWBtn = new Button("Confirm");
		Button cancelWBtn = new Button("Cancel");            
		
		withdrawHbox.setSpacing(10);
		withdrawHbox.setPadding(new Insets(0, 20, 10, 20));
		withdrawHbox.getChildren().addAll(confirmWBtn, cancelWBtn);
		
		withdraw.setPadding(new Insets(0, 20, 10, 20));
		withdraw.getChildren().addAll(lable, label3, tilePane2, tilePane, withdrawalAmountTf, withdrawHbox);
		
		cancelWBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent arg0) {
		        primaryStage.setScene(applicationScene);
		    }
		    
		});
		
		confirmWBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent arg0) {
		    	Transaction transaction = new Transaction();
		    	
		        depositAmount = withdrawalAmountTf.getText();
		        
		        if (depositAmount != null) {
		            System.out.println(depositAmount);
		            double amount = Double.parseDouble(depositAmount);
		            System.out.println(amount);
		            if (amount > 0) {
		            	Transaction transact = transaction.withdraw(transactions, user, accountSelected.getText(), amount);
		                //user.withdraw(amount);
		                //System.out.println(user.getBalance());
		            	
		            	if (transact.getAccountType() == "Checking") {
		            		checkActBalance = Double.toString(transact.getNewBalance());
		            		checkingAccountBalanceLabel.setText(checkActBalance);
		            	} else {
		            		saveActBalance = Double.toString(transact.getNewBalance());
		            		savingsAccountBalanceLabel.setText(saveActBalance);
		            	}                        	
		                
		                System.out.println(user.getUserCheckingAccount().getAccountBalance());
		                
		                for (Transaction trans : transactions) {
		                	System.out.println("transaction in withdraw: " + trans);
		                }
		                
		            }
		        }
		        
		        withdrawalAmountTf.clear();  
		        
		        primaryStage.setScene(applicationScene);
		    }
		    
		});
		//End
	}
    
    public void balanceTransfer(Stage primaryStage) {
    	
    	VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: beige");
        HBox hBox = new HBox();
        
        balanceTranferScene = new Scene(vBox, 400, 400);
        vBox.setSpacing(20);
        
        Label labl = new Label("Balance Transfer");
        labl.setFont(new Font("Cambria", 20));
        labl.setStyle("-fx-font-weight: bold");
        Label label2 = new Label("How much do you want to transfer?");
        TextField transferAmountTf = new TextField();
                
        Button confirmTransfer = new Button("Confirm");            
        Button cancelTransfer = new Button("Cancel");
        
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(0, 20, 10, 20));
        hBox.getChildren().addAll(confirmTransfer, cancelTransfer);
        
        
        cancelTransfer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(applicationScene);
            }
            
        });
        
        confirmTransfer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            	Transaction transaction = new Transaction();
                
                transferAmount = transferAmountTf.getText();
                if (transferAmount != null) {
                    System.out.println(transferAmount);
                    double amount = Double.parseDouble(transferAmount);
                    System.out.println(amount);
                    if (amount > 0) {
                    	if (isCheckingAccount) {
                    		User usr = transaction.tranferFromCheckingToSaving(transactions, user, amount);
                    		
                    		checkActBalance = Double.toString(usr.getUserCheckingAccount().getAccountBalance());
		            		checkingAccountBalanceLabel.setText(checkActBalance);
		            		
		            		saveActBalance = Double.toString(usr.getUserSavingsAccount().getAccountBalance());
		            		savingsAccountBalanceLabel.setText(saveActBalance);
                    		
                    		System.out.println("Checking Account Balance" + usr.getUserCheckingAccount().getAccountBalance());
                    		System.out.println("Savings Account Balance" + usr.getUserSavingsAccount().getAccountBalance());
                    	} else {
                    		User usr = transaction.tranferFromSavingToChecking(transactions, user, amount);
                    		
                    		checkActBalance = Double.toString(usr.getUserCheckingAccount().getAccountBalance());
		            		checkingAccountBalanceLabel.setText(checkActBalance);
		            		
		            		saveActBalance = Double.toString(usr.getUserSavingsAccount().getAccountBalance());
		            		savingsAccountBalanceLabel.setText(saveActBalance);
                    	}
                    }
                    
                    for (Transaction trans : transactions) {
	                	System.out.println("transaction in BF: " + trans);
	                }
                }
                
                transferAmountTf.clear();
                
                primaryStage.setScene(applicationScene);
            }
            
        });
        
        TilePane tilePane = new TilePane();
		TilePane tilePane2 = new TilePane();
		
		Label option = new Label("Select account to transfer money from:");
		Label selection = new Label("Please select an account");
		Label accountSelected = new Label();
		
		ToggleGroup tg = new ToggleGroup();
		
		RadioButton checking = new RadioButton("Checking Account");
		RadioButton savings = new RadioButton("Savings Account");
		
		checking.setToggleGroup(tg);
		savings.setToggleGroup(tg);
		
		tilePane2.getChildren().add(option);
		tilePane.getChildren().addAll(checking, savings);
		tilePane2.getChildren().add(selection);
		
		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		    public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
     
		        RadioButton rb = (RadioButton)tg.getSelectedToggle();
     
		        if (rb != null) {
		        	String message = "";
		            String s = rb.getText();
		            
		            if (s.equalsIgnoreCase("Checking Account")) {
		            	message = "Transfering from Checking to Savings";
		            	isCheckingAccount = true;
		            	checkingAccount = user.getUserCheckingAccount();
		            	accountSelected.setText(user.getUserCheckingAccount().getAccountNumber());
		            } else {
		            	message = "Transfering from Savings to Checking";
		            	savingsAccount = user.getUserSavingsAccount();
		            	accountSelected.setText(user.getUserSavingsAccount().getAccountNumber());
		            }
     
		            // change the label
		            selection.setStyle("-fx-font-weight: bold");
		            selection.setText(message);
		        }
		    }
		});
    	
		vBox.setPadding(new Insets(0, 20, 10, 20));
		vBox.getChildren().addAll(labl,  tilePane2, tilePane, label2,transferAmountTf, hBox);
    }
    
    public void depositTransaction(Stage primaryStage) {
        // Deposit Scene begin
        VBox dep = new VBox();
        dep.setStyle("-fx-background-color: beige");
        HBox depHbox = new HBox();
        
        makeDepositScene = new Scene(dep, 400, 400);
        dep.setSpacing(20);
        Label labl = new Label("Deposit Money");
        labl.setFont(new Font("Cambria", 20));
        labl.setStyle("-fx-font-weight: bold");
        Label label2 = new Label("How much do you want to deposit?");
        TextField depositAmountTf = new TextField();
                
        Button confirm = new Button("Confirm");            
        Button cancel = new Button("Cancel");            
        
        depHbox.setSpacing(10);
        depHbox.setPadding(new Insets(0, 20, 10, 20));
        depHbox.getChildren().addAll(confirm, cancel);
        
        dep.setPadding(new Insets(0, 20, 10, 20));
        dep.getChildren().addAll(labl, label2, depositAmountTf, depHbox);
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(applicationScene);
            }
            
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            	Transaction transaction = new Transaction();
                
                depositAmount = depositAmountTf.getText();
                if (depositAmount != null) {
                    System.out.println(depositAmount);
                    double amount = Double.parseDouble(depositAmount);
                    System.out.println(amount);
                    if (amount > 0) {
                    	CheckingAccount account = transaction.deposit(transactions, user, amount);
                        checkActBalance = Double.toString(account.getAccountBalance());
                        checkingAccountBalanceLabel.setText("$" + checkActBalance);
                        System.out.println(user.getUserCheckingAccount().getAccountBalance());
                        
                        for (Transaction trans : transactions) {
                        	System.out.println("transaction: " + trans);
                        }
                        System.out.println(user);
                    }
                    
                    for (Transaction trans : transactions) {
	                	System.out.println("transaction in DP: " + trans);
	                }
                }
                
                depositAmountTf.clear();
                
                primaryStage.setScene(applicationScene);
            }
            
        });
        //End
    }
    
    private void transactionsDisplay(Stage primaryStage) {
    	VBox vbox = new VBox();
    	vbox.setStyle("-fx-background-color: beige");
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
                    
        Label label0 = new Label(user.getFirstName() + " Transactions");
        label0.setFont(new Font("Cambria", 20));
        label0.setStyle("-fx-font-weight: bold");
    	
    	Label label = new Label("File Data:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);
        Button cancel = new Button("Cancel");
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(applicationScene);
            }
            
        });
        
        // New Table View
        TableView tbv = new TableView();
        // Create two columns 
        TableColumn<String, Transaction> cl1 = new TableColumn<>("Account Number");
        cl1.setCellValueFactory(new PropertyValueFactory<>("AccountNumber"));
        cl1.setPrefWidth(110);
        
        TableColumn<String, Transaction> cl2 = new TableColumn<>("Transaction Type");
        cl2.setCellValueFactory(new PropertyValueFactory<>("TransactionType"));
        cl2.setPrefWidth(110);
        
        TableColumn<String, Transaction> cl3 = new TableColumn<>("Previous $");
        cl3.setCellValueFactory(new PropertyValueFactory<>("PreviousBalance"));
        cl3.setPrefWidth(90);
        
        TableColumn<String, Transaction> cl4 = new TableColumn<>("Current $");
        cl4.setCellValueFactory(new PropertyValueFactory<>("NewBalance"));
        cl4.setPrefWidth(90);
        
        // Add two columns into TableView
        tbv.getColumns().add(cl1);
        tbv.getColumns().add(cl2);
        tbv.getColumns().add(cl3);
        tbv.getColumns().add(cl4);
        
        
        // Load objects into table		
		 
		 for (Transaction trans : user.getUserTransactions()){
			 tbv.getItems().add(trans); 
			 System.out.println(trans); 
		 } 
		 

        // Display the table              
        vbox.getChildren().addAll(label0, tbv, cancel);
        
        transactionsScene = new Scene(vbox, 400, 400);
        
        primaryStage.setScene(transactionsScene);
    	
    }
    
    private void accountBalance(Stage primaryStage) {
		// Check Balance Scene begin
        HBox checkingAccBox = new HBox();
        HBox savingsAccBox = new HBox();      
        
        VBox appGroupBalance = new VBox();
        appGroupBalance.setSpacing(30);
        
        appGroupBalance.setStyle("-fx-background-color: beige");
        
        checkBalanceScene = new Scene(appGroupBalance, 400, 400);
                    
        Label label0 = new Label("Balance Inquiry");
        label0.setFont(new Font("Cambria", 20));
        label0.setStyle("-fx-font-weight: bold");
        
        checkingAccountBalanceLabel = new Label("$" + checkActBalance);
        savingsAccountBalanceLabel = new Label("$" + saveActBalance);
        
        Label checkBalanceLabel = new Label("Your Checking Account Balance is: ");
        Label saveBalanceLabel = new Label("Your Savings Account Balance is: ");
        
        
        Button close = new Button("Close"); 
        
        checkingAccBox.getChildren().addAll(checkBalanceLabel, checkingAccountBalanceLabel);
        savingsAccBox.getChildren().addAll(saveBalanceLabel, savingsAccountBalanceLabel);
        
        for (Transaction trans : transactions) {
        	System.out.println("transaction in AC: " + trans);
        }
        
        
        appGroupBalance.setSpacing(10);
        appGroupBalance.setPadding(new Insets(0, 20, 10, 20));
        appGroupBalance.getChildren().addAll(label0, checkingAccBox, savingsAccBox, close);
        
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(applicationScene);
            }
            
        });
	}
    

    public void formElement(Stage primaryStage) {
        // Application Scene setup begin
        VBox appGroup = new VBox();
        appGroup.setStyle("-fx-background-color: beige");
        HBox checkingBox = new HBox();
        HBox savingsBox = new HBox();
        
        appGroup.setSpacing(30);
        applicationScene = new Scene(appGroup, 400, 400);
        
        appGroup.setAlignment(Pos.CENTER);
        
        Label l1 = new Label("Welcome to ATM Systems Services, Inc");
        l1.setFont(new Font("Cambria", 18));
        l1.setStyle("-fx-font-weight: bold");
        
        Label l2 = new Label("Your Checking Account number is: ");
        Label checkingAccountLabel = new Label(checkingAccountNumber);
        
        Label l0 = new Label("Your Savings Account number is: ");
        Label savingsAccountLabel = new Label(savingsAccountNumber);
        
        Label l3 = new Label("Please choose an option:");
        
        Button checkBalanceBtn = new Button("Check Balance");
        
        checkBalanceBtn.setMaxWidth(Double.MAX_VALUE);
        checkBalanceBtn.setStyle("-fx-background-color: tan ");
        
        Button makeDepositBtn = new Button("Make a Deposit");
        
        makeDepositBtn.setStyle("-fx-background-color: tan ");
        makeDepositBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button withdrawalBtn = new Button("Withdrawal");
        
        withdrawalBtn.setStyle("-fx-background-color: tan ");
        withdrawalBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button transferBtn = new Button("Transfer");
        
        transferBtn.setStyle("-fx-background-color: tan ");
        transferBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button transactionBtn = new Button("Transactions");
        
        transactionBtn.setStyle("-fx-background-color: tan ");
        transactionBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button exitAppBtn = new Button("Exit/Logout");
        
        exitAppBtn.setStyle("-fx-background-color: tomato ");
        exitAppBtn.setMaxWidth(Double.MAX_VALUE);
        
        checkingBox.getChildren().addAll(l2, checkingAccountLabel);
        savingsBox.getChildren().addAll(l0, savingsAccountLabel);
        
        appGroup.setSpacing(10);
        appGroup.setPadding(new Insets(0, 20, 10, 20));
        appGroup.getChildren().addAll(l1, checkingBox, savingsBox, l3, 
        		checkBalanceBtn, makeDepositBtn, withdrawalBtn, transferBtn, transactionBtn, exitAppBtn);
        
        checkBalanceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(checkBalanceScene);
            }
            
        });
        
        makeDepositBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(makeDepositScene);
            }
            
        });
        
        withdrawalBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(makeWithdrawalScene);
            }            
        });
        
        transferBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(balanceTranferScene);
            }
            
        });
        
        transactionBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent arg0) {
        		primaryStage.setScene(transactionsScene);
        	}
        	
        });
        
        exitAppBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.close();                
            }
            
        });
        
        for (Transaction trans : transactions) {
        	System.out.println("transaction in AC: " + trans);
        }
        
        accountBalance(primaryStage);
        transactionsDisplay(primaryStage);
    }

	
	
    public static void main(String[] args) {
        launch(args);
    }
}
