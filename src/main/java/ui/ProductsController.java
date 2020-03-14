package ui;

import data.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ProductRow;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {

    @FXML private TableView<ProductRow> productsTable;
    @FXML private TableColumn<ProductRow, String> nameCol;
    @FXML private TableColumn<ProductRow, String> descCol;
    @FXML private TableColumn<ProductRow, String> priceCol;
    @FXML private TableColumn<ProductRow, String> stockLevelCol;
    @FXML private TableColumn<ProductRow, Button> viewProductCol;

    Navigator navigator = Navigator.getInstance();

    public void viewHome(ActionEvent event) {
        try {
            navigator.viewHome(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewBasket(ActionEvent event) {
        try {
            navigator.viewBasket(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadProducts() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockLevelCol.setCellValueFactory(new PropertyValueFactory<>("stockLevel"));
        viewProductCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        productsTable.getItems().setAll(parseRows());
    }

    private List<ProductRow> parseRows() {
        List<ProductRow> productRows = new ArrayList<>();
        DBConnection db = new DBConnection();
        ResultSet results = null;

        try {
            results = db.getProductsInTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (results != null) {
            try {
                while (results.next()) {
                    int productId = results.getInt("product_id");
                    String name = results.getString("product_name");
                    String description = results.getString("product_description");
                    String price = results.getString("product_price");
                    String stockLevel = results.getString("product_stock_level");

                    Button viewButton = new Button("View Product");
                    viewButton.setOnAction(actionEvent -> {
                        try {
                            navigator.viewProduct(actionEvent, productId);
                        } catch(IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });

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
