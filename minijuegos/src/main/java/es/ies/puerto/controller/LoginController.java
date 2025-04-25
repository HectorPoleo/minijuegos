package es.ies.puerto.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.bbdd.Usuario;
import es.ies.puerto.model.bbdd.UsuarioSesion;
import es.ies.puerto.model.bbdd.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author hectorpoleo
 * @version 1.0.0
 */
public class LoginController extends AbstractController {
    
    UsuarioSesion usuarioSesion = new UsuarioSesion();
    Usuarios usuarios;

    @FXML
    private TextField textFieldUsuario;
    
    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Text textFieldMensaje;

    @FXML
    private Button openRegistrarButton;

    @FXML
    private Text textUsuario;

    @FXML
    private Text textContrasenia;

    @FXML
    private ComboBox comboIdioma;

    @FXML
    private Button mostrarButton;

    /**
     * 
     * @throws SQLException
     */
    @FXML
    public void initialize() throws SQLException {
        List<String> idiomas = new ArrayList<>();
        idiomas.add("es");
        idiomas.add("en");
        idiomas.add("fr");
        if(comboIdioma!=null){
             comboIdioma.getItems().addAll(idiomas);
        } 
        try {
            usuarios=new Usuarios();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void postInitialize(){
        
    }

    @FXML
    protected void cambiarIdioma() {
        setPropertiesIdioma(loadIdioma("idioma", comboIdioma.getValue().toString()));
        textUsuario.setText(getPropertiesIdioma().getProperty("textUsuario"));
        textContrasenia.setText(getPropertiesIdioma().getProperty("textContrasenia"));
    }
    /**
     * Metodo que permite iniciar sesion
     */
    @FXML
    protected void onLoginButtonClick() throws SQLException, ClassNotFoundException {

        if (textFieldUsuario== null || textFieldUsuario.getText().isEmpty() ||
            textFieldPassword == null || textFieldPassword.getText().isEmpty() ) {
                textFieldMensaje.setText("Credenciales null o vacias");
                return;
        }
        if (usuarios.recibirUsuario(textFieldUsuario.getText(), textFieldPassword.getText()).getEmail()!=null) {
            textFieldMensaje.setText("Usuario validado correctamente");
            Usuario usuario =new Usuario(textFieldPassword.getText(), textFieldUsuario.getText());
            usuarioSesion.setUsuario( usuarios.recibirUsuario(textFieldUsuario.getText(), textFieldPassword.getText()));
            try {
                usuarios.escribir(usuarioSesion.getUsuario());
                perfil();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        textFieldMensaje.setText("Credenciales invalidas");
    }
    /**
     * Metodo que abre el xml de registrar un usuario nuevo
     */
    @FXML
    protected void openRegistrarClick() {

        try {

            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("registro.fxml"));
            Parent root = loader.load();
    
            RegistroController registroController = loader.getController();
            registroController.setPropertiesIdioma(this.getPropertiesIdioma());
            
            registroController.postInitialize(); 
    
            Stage stage = (Stage) textFieldUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /**
     * Metodo que abre el xml de recuperar password
     */
    @FXML
    protected void recoverPassoword(){
        try {

            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("recover.fxml"));
            Parent root = loader.load();
    
            RecoverPasswordController registroController = loader.getController();
            registroController.setPropertiesIdioma(this.getPropertiesIdioma());
            
            registroController.postInitialize();
    
            Stage stage = (Stage) mostrarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que abre el xml de perfil
     */
    @FXML
    protected void perfil(){
        try {

            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
            Parent root = loader.load();
    
            PerfilController registroController = loader.getController();
            registroController.setPropertiesIdioma(this.getPropertiesIdioma());
            
            registroController.postInitialize();
    
            Stage stage = (Stage) mostrarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo que abre el xml de mostrar usuarios
     */
    @FXML
    protected void onClickMostrar(){
        try {

            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("usuarios.fxml"));
            Parent root = loader.load();
    
            UsuariosController registroController = loader.getController();
            registroController.setPropertiesIdioma(this.getPropertiesIdioma());
            
            registroController.postInitialize();
    
            Stage stage = (Stage) mostrarButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    
