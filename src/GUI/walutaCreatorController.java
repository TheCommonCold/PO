package GUI;
import aktywa.waluta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.Main;



public class walutaCreatorController {
    @FXML
    private TableView<waluta> walutaTable;
    @FXML
    private TableColumn<waluta, String> nazwaColumn;
    @FXML
    private TableColumn<waluta, String> krajColumn;

    @FXML
    private Label nazwaLabel;
    @FXML
    private Label krajLabel;
    @FXML
    private Label cenaKupnaLabel;
    @FXML
    private Label cenaSprzedazyLabel;


    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public walutaCreatorController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main
     */
    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        personTable.setItems(main.getPersonData());
    }
}
