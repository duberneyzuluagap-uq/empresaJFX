package co.edu.uniquindio.poo.empresajfx.viewController;

import co.edu.uniquindio.poo.empresajfx.App;
import co.edu.uniquindio.poo.empresajfx.controller.ProductoController;
import co.edu.uniquindio.poo.empresajfx.model.Producto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductoViewController {
    ProductoController productoController;
    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    Producto selectedProducto;
    App app;

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnAgregar;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtId;
    @FXML
    private TableView<Producto> tablaProducto;
    @FXML
    private TableColumn<Producto,String> colId;
    @FXML
    private TableColumn<Producto,String> colNombre;
    @FXML
    private TableColumn<Producto,Integer> colCantidad;
    @FXML
    void onAgregarProducto(){
        agregarProducto();
    }
    @FXML
    void onEliminar(){
        eliminarProducto();
    }
    @FXML
    void onActualizar(){
        actualizarProducto();
    }
    @FXML
    void initialize() {
        initDataBinding();
        tablaProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProducto = newSelection;
            if (selectedProducto != null) {
                txtId.setText(selectedProducto.getId());
                txtNombre.setText(selectedProducto.getNombre());
                txtCantidad.setText(String.valueOf(selectedProducto.getCantidadDisponible()));
            }
        });
    }

    void initDataBinding(){
        colId.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getId()));
        colCantidad.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantidadDisponible()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getNombre()));
    }

    private Producto buildProducto(){
        Producto producto = new Producto(txtId.getText(),txtNombre.getText(),Integer.parseInt(txtCantidad.getText()));
        return producto;
    }

    private void agregarProducto(){
        Producto productoNuevo = buildProducto();
        if (productoController.agregarProducto(productoNuevo)){
            listaProductos.add(productoNuevo);
            limpiarCamposCliente();
        }
    }

    private void eliminarProducto(){
        if (selectedProducto != null && productoController.eliminarProducto(selectedProducto.getId())){
            listaProductos.remove(selectedProducto);
            limpiarCamposCliente();
        }
    }

    private void actualizarProducto(){
        if (selectedProducto != null && productoController.actualizarProducto(selectedProducto.getId(), buildProducto())){
            int index = listaProductos.indexOf(selectedProducto);
            if (index >= 0) {
                listaProductos.set(index, buildProducto());
            }
            limpiarCamposCliente();
        }
    }

    private void limpiarCamposCliente() {
        txtId.clear();
        txtNombre.clear();
        txtCantidad.clear();
    }

    public void setApp(App app) {
        this.app = app;
        productoController = new ProductoController(app.empresa);
        obtenerProductos();
    }

    private void obtenerProductos() {
        listaProductos.clear();
        listaProductos.addAll(productoController.obtenerListaProductos());
        tablaProducto.setItems(listaProductos);
    }
}
