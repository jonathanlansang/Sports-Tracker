/**
 * Created by jonathanlansang on 5/23/16.
 */
import com.sun.javafx.collections.ObservableSetWrapper;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public class GUI extends Application {

    private TableView table = new TableView();
    private String[] header = CSV.getHeader().split(",");
    private League nba;
    private Team[] teams;

    public static void main(String[] args) {
        launch(args);



    }


    @Override
    public void start(Stage stage) {
        nba = new League();
        teams = nba.getTeams();
        //loadTeams();
        Scene scene = new Scene(new Group());
        stage.setTitle("NBA Stat Tracker");
        stage.setWidth(3000);
        stage.setHeight(1000);

        final Label label = new Label("Teams");
        label.setFont(new Font("Arial", 20));


        table.setEditable(true);
        //columns
        for (String s:header) {
            TableColumn temp = new TableColumn(s);
            table.getColumns().addAll(temp);
        }

        Team t = teams[0]; // Change to variable dependent on combo box later

        ObservableList<TableColumn> tableCol = table.getColumns();
        for(int j = 0; j < tableCol.size();j++){
            String s = header[j];
            for(int i = 0; i < t.getPlayers().length; i++){
                SimpleStringProperty x = new SimpleStringProperty(t.getPlayers()[i].getAllStats()[j]);
                tableCol.get(j).setCellValueFactory(new PropertyValueFactory<Player, String>(s));
            }
        }





        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);


        stage.setScene(scene);
        stage.show();
    }
}