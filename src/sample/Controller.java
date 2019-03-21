package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;


public class Controller implements EventHandler<javafx.event.ActionEvent>, Initializable {

                //<!===== BUTTONS =====!>

    //ADDITIONAL STUFF
    @FXML private ToggleButton Night_mode;
    @FXML private Button Extend;
    @FXML private Button Extend2;
    @FXML private AnchorPane anchor;
    @FXML private ChoiceBox<String> Theme_choice = new ChoiceBox<>();

    //CALCULATOR NUMBERS
    @FXML private Button one;
    @FXML private Button two;
    @FXML private Button three;
    @FXML private Button four;
    @FXML private Button five;
    @FXML private Button six;
    @FXML private Button seven;
    @FXML private Button eight;
    @FXML private Button nine;
    @FXML private Button zero;
    @FXML private Button coma;
    @FXML private Button pi;

    //CALCULATOR OPERATORS
    @FXML private Button plus;
    @FXML private Button minus;
    @FXML private Button mult;
    @FXML private Button div;
    @FXML private Button mod;
    @FXML private Button equals;
    @FXML private Button root;
    @FXML private Button sqr;
    @FXML private Button neg;
    @FXML private Button del;
    @FXML private Button c;
    @FXML private Button ce;
    @FXML private Button factorial;
    @FXML private Button log;
    @FXML private Button onedivx;
    @FXML private Button xpowern;
    @FXML private Button sin;
    @FXML private Button cos;
    @FXML private Button tan;
    @FXML private Button ctg;
    @FXML private Button hex;
    @FXML private Button dec;
    @FXML private Button oct;
    @FXML private Button bin;

    //MEMORY BUTTONS
    @FXML private Button MR;
    @FXML private Button MC;
    @FXML private Button M_minus;
    @FXML private Button M_plus;


    //LABELS
    @FXML private Label memory_Label;
    @FXML private Label result_Label;
    @FXML private Label operator_Label;

    //VARIABLES
    private Calculus Calculator = new Calculus();
    private String Number = "";

    //STYLE SHEETS
    private String Current_StyleSheet = "sample/resources/style.css";
    private String Previous_StyleSheet = "sample/resources/style.css";
    private String Theme_Default = "sample/resources/style.css";
    private String Theme_Dark = "sample/resources/style_2.css";
    private String Theme_Pink = "sample/resources/style_3.css";
    private String Theme_Red = "sample/resources/style_4.css";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        Theme_choice.getItems().add("NIGHT MODE");
        Theme_choice.getItems().add("DEEP PINK");
        Theme_choice.getItems().add("FIERY RED");
        Theme_choice.getItems().add("DEFAULT");

        //Listen Fore Selection changes
        Theme_choice.getSelectionModel().selectedItemProperty().addListener( (v,OldValue,NewValue) -> change_themes(NewValue));
    }

    public void ChangeScene_extended(ActionEvent event) throws IOException {
        Parent extended_calculator = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene = new Scene(extended_calculator);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().add(Current_StyleSheet);
    }

    public void ChangeScene_standard(ActionEvent event) throws IOException {
        Parent extended_calculator1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(extended_calculator1);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().add(Current_StyleSheet);
    }

    private void solve()
    {
        if(Calculator.getArg_1() != 0 && Calculator.getArg_2() == 0)
        {
            if(Number == "")
            {
                if (Calculator.getOperator() == "/" || Calculator.getOperator() == "*" || Calculator.getOperator() == "xpowern")
                    Calculator.setArg_2(1);
                else if(Calculator.getOperator() == "%")
                    Calculator.setArg_2(Calculator.getArg_1()+1);
                else
                    Calculator.setArg_2(0);
            }
            else
                Calculator.setArg_2(Double.parseDouble(Number));

        }
        else if(Calculator.getArg_1() == 0)
        {
            if(Number == "")
                Calculator.setArg_1(0);
            else
                Calculator.setArg_1(Double.parseDouble(Number));
            if (Calculator.getOperator() == "/" || Calculator.getOperator() == "*" || Calculator.getOperator() == "xpowern")
                Calculator.setArg_2(1);
            if(Calculator.getOperator() == "%")
                Calculator.setArg_2(Calculator.getArg_1()+1);
        }

        Number = "";
        Calculator.setArg_1(Calculator.calculate(Calculator.getArg_1(),Calculator.getArg_2(),Calculator.getOperator()));
        Calculator.setArg_2(0);
        if(Calculator.getArg_1() % 1 == 0)
            result_Label.setText(Integer.toString((int)Calculator.getArg_1()));
        else
            result_Label.setText(Double.toString(Calculator.getArg_1()));
    }


    //REMOVE LAST CHARACTER FROM THE STRING
    private static String removeLastChar(String str)
    {
        String new_stirng = "";
        if(str.length() != 0)
            new_stirng = str.substring(0, str.length() - 1);
        return new_stirng;
    }
    //INSERT DIGIT INTO NUMEBR
    private void insert_digit(String digit)
    {
        if(Number == "0")
            Number = digit;
        else
            Number += digit;
    }

    @FXML
    public void change_modes(ActionEvent event)
    {
        Scene scene = anchor.getScene();
        if(Night_mode.isSelected())
        {
            Previous_StyleSheet = Current_StyleSheet;
            scene.getStylesheets().remove(Current_StyleSheet);
            scene.getStylesheets().add(Theme_Dark);
            Current_StyleSheet = Theme_Dark;
        }
        else
        {
            scene.getStylesheets().remove(Current_StyleSheet);
            scene.getStylesheets().add(Previous_StyleSheet);
            Current_StyleSheet = Previous_StyleSheet;
        }

    }

    public void change_themes(String Theme_name)
    {
        Scene scene = anchor.getScene();
        switch(Theme_name)
        {
            case "NIGHT MODE":
            {
                scene.getStylesheets().remove(Current_StyleSheet);
                scene.getStylesheets().add(Theme_Dark);
                Current_StyleSheet = Theme_Dark;
            }
            break;
            case "DEEP PINK":
            {
                scene.getStylesheets().remove(Current_StyleSheet);
                scene.getStylesheets().add(Theme_Pink);
                Current_StyleSheet = Theme_Pink;
            }
            break;
            case "FIERY RED":
            {
                scene.getStylesheets().remove(Current_StyleSheet);
                scene.getStylesheets().add(Theme_Red);
                Current_StyleSheet = Theme_Red;
            }
            break;
            default:
            {
                scene.getStylesheets().remove(Current_StyleSheet);
                scene.getStylesheets().add(Theme_Default);
                Current_StyleSheet = Theme_Default;
            }

        }

    }


    @Override
    public void handle(javafx.event.ActionEvent event) {
        //DIGITS
        if(event.getSource() == one)
        {
            insert_digit("1");
            result_Label.setText(Number);
        }
        if(event.getSource() == two)
        {
            insert_digit("2");
            result_Label.setText(Number);
        }
        if(event.getSource() == three)
        {
            insert_digit("3");
            result_Label.setText(Number);
        }
        if(event.getSource() == four)
        {
            insert_digit("4");
            result_Label.setText(Number);
        }
        if(event.getSource() == five)
        {
            insert_digit("5");
            result_Label.setText(Number);
        }
        if(event.getSource() == six)
        {
            insert_digit("6");
            result_Label.setText(Number);
        }
        if(event.getSource() == seven)
        {
            insert_digit("7");
            result_Label.setText(Number);
        }
        if(event.getSource() == eight)
        {
            insert_digit("8");
            result_Label.setText(Number);
        }
        if(event.getSource() == nine)
        {
            insert_digit("9");
            result_Label.setText(Number);
        }
        if(event.getSource() == zero)
        {
            insert_digit("0");
            result_Label.setText(Number);
        }
        if(event.getSource() == pi)
        {
            Number = "3.14159265359";
            result_Label.setText(Number);
        }
        if(event.getSource() == coma)
        {
            Number +=".";
            result_Label.setText(Number);
        }


        //SIGNS
        if(event.getSource() == equals)
        {
            if(Number == "")
                Calculator.setArg_2(0);
            else
                Calculator.setArg_2(Double.parseDouble(Number));
            Number = "";
            Calculator.setArg_1(Calculator.calculate(Calculator.getArg_1(),Calculator.getArg_2(),Calculator.getOperator()));

            Calculator.setArg_2(0);
            if(Calculator.getArg_1() % 1 == 0)
                result_Label.setText(Integer.toString((int)Calculator.getArg_1()));
            else
            {
                result_Label.setText(Double.toString(Calculator.getArg_1()));
            }

        }
        if(event.getSource() == plus)
        {
            Calculator.setOperator("+");
            solve();
            operator_Label.setText("+");
        }

        if(event.getSource() == minus)
        {
            Calculator.setOperator("-");
            solve();
            operator_Label.setText("-");
        }
        if(event.getSource() == mult)
        {
            Calculator.setOperator("*");
            solve();
            operator_Label.setText("*");
        }
        if(event.getSource() == div)
        {
            Calculator.setOperator("/");
            solve();
            operator_Label.setText("/");
        }
        if(event.getSource() == mod)
        {
            Calculator.setOperator("%");
            solve();
            operator_Label.setText("%");
        }
        if(event.getSource() == root)
        {
            Calculator.setOperator("sqrt");
            solve();
            operator_Label.setText("√");
        }
        if(event.getSource() == sqr)
        {
            Calculator.setOperator("sqr");
            solve();
            operator_Label.setText("X²");
        }
        if(event.getSource() == neg)
        {
            Calculator.setOperator("neg");
            solve();
            operator_Label.setText("+/-");
        }
        if(event.getSource() == factorial)
        {
            Calculator.setOperator("factorial");
            solve();
            operator_Label.setText("!n");
        }
        if(event.getSource() == log)
        {
            Calculator.setOperator("log");
            solve();
            operator_Label.setText("log");
        }
        if(event.getSource() == onedivx)
        {
            Calculator.setOperator("onedivx");
            solve();
            operator_Label.setText("1/x");
        }
        if(event.getSource() == xpowern)
        {
            Calculator.setOperator("xpowern");
            solve();
            operator_Label.setText("xⁿ");
        }
        if(event.getSource() == sin)
        {
            Calculator.setOperator("sin");
            solve();
            operator_Label.setText("sin");
        }
        if(event.getSource() == cos)
        {
            Calculator.setOperator("cos");
            solve();
            operator_Label.setText("cos");
        }
        if(event.getSource() == tan)
        {
            Calculator.setOperator("tan");
            solve();
            operator_Label.setText("tan");
        }
        if(event.getSource() == ctg)
        {
            Calculator.setOperator("ctg");
            solve();
            operator_Label.setText("ctg");
        }

        //CONVERT INTEGERS
        if(event.getSource() == hex)
        {
            operator_Label.setText("HEX");
            Calculator.setArg_1((Integer.parseInt(Number)));

            String hex = Integer.toHexString((int)Double.parseDouble(Number));
            result_Label.setText("0x"+hex);

        }
        if(event.getSource() == dec)
        {
            operator_Label.setText("DEC");
            Calculator.setArg_1((Integer.parseInt(Number)));

            result_Label.setText(Double.toString(Calculator.getArg_1()));

        }
        if(event.getSource() == oct)
        {
            operator_Label.setText("OCT");
            Calculator.setArg_1((Integer.parseInt(Number)));

            String oct = Integer.toOctalString((int)Double.parseDouble(Number));
            result_Label.setText(oct);

        }
        if(event.getSource() == bin)
        {
            operator_Label.setText("BIN");
            Calculator.setArg_1((Integer.parseInt(Number)));

            String bin = Integer.toBinaryString((int)Double.parseDouble(Number));
            result_Label.setText(bin);

        }




        //CONTROLS
        if(event.getSource() == c)
        {
            Calculator.setOperator("");
            Calculator.setArg_1(0);
            Calculator.setArg_2(0);
            operator_Label.setText("");
            Number = "0";
            result_Label.setText(Number);

        }
        if(event.getSource() == ce)
        {
            Number = "0";
            result_Label.setText(Number);
        }

        if(event.getSource() == del)
        {
            if(Number == "0")
            {
                Calculator.setArg_1(0);
                result_Label.setText("0");
            }
            Number = removeLastChar(Number);
            result_Label.setText(Number);
        }


        //MEMORY
        if(event.getSource() == MR)
        {
            Number = Double.toString(Calculator.getMArg_1());

            if(Calculator.getMArg_1() % 1 == 0)
                result_Label.setText(Integer.toString((int)Calculator.getMArg_1()));
            else
                result_Label.setText(Double.toString(Calculator.getMArg_1()));
        }
        if(event.getSource() == MC)
        {
            Calculator.setMArg_1(0);
            memory_Label.setText("0");
        }
        if(event.getSource() == M_minus)
        {
            if(Number == "")
                Calculator.setMArg_1(Calculator.getMArg_1()-Calculator.getArg_1());
            else
                Calculator.setMArg_1(Calculator.getMArg_1()-Double.parseDouble(Number));

            if(Calculator.getMArg_1() % 1 == 0)
                memory_Label.setText(Integer.toString((int)Calculator.getMArg_1()));
            else
                memory_Label.setText(Double.toString(Calculator.getMArg_1()));

        }
        if(event.getSource() == M_plus)
        {
            if(Number == "")
                Calculator.setMArg_1(Calculator.getMArg_1()+Calculator.getArg_1());
            else
                Calculator.setMArg_1(Calculator.getMArg_1()+Double.parseDouble(Number));


            if(Calculator.getMArg_1() % 1 == 0)
                memory_Label.setText(Integer.toString((int)Calculator.getMArg_1()));
            else
                memory_Label.setText(Double.toString(Calculator.getMArg_1()));

        }


    }
}
