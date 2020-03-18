package ui;

import data.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ProductRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.DatabaseConstants.*;

public class ProductsController extends Controller {

    @FXML private TableView<ProductRow> productsTable;
    @FXML private TableColumn<ProductRow, String> nameCol;
    @FXML private TableColumn<ProductRow, String> descCol;
    @FXML private TableColumn<ProductRow, String> priceCol;
    @FXML private TableColumn<ProductRow, String> stockLevelCol;
    @FXML private TableColumn<ProductRow, Button> viewProductCol;

    private DBConnection db = DBConnection.getInstance();

    @Override
    public void viewProducts(ActionEvent event) {
        super.showPopup();
    }

    public void loadProducts() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockLevelCol.setCellValueFactory(new PropertyValueFactory<>("stockLevel"));
        viewProductCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        productsTable.getItems().setAll(parseRows());
    }

    private List<ProductRow> parseRows() {
        List<ProductRow> productRows = new ArrayList<>();
        ResultSet results = null;

        try {
            results = db.getProductsInTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (results != null) {
            try {
                while (results.next()) {
                    int productId = results.getInt(ID);
                    String name = results.getString(NAME);
                    String description = results.getString(DESCRIPTION);
                    String price = results.getString(PRICE);
                    String stockLevel = results.getString(STOCK_LEVEL);

                    Button viewButton = new Button("View Product");
                    viewButton.setOnAction(actionEvent -> navigator.viewProduct(actionEvent, productId));

                    ProductRow currentProductRow = new ProductRow(productId, name, description, price, stockLevel, viewButton);
                    productRows.add(currentProductRow);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return productRows;
    }

}
