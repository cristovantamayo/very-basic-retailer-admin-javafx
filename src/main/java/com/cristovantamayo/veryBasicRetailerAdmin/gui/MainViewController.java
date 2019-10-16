package com.cristovantamayo.veryBasicRetailerAdmin.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.cristovantamayo.veryBasicRetailerAdmin.application.Main;
import com.cristovantamayo.veryBasicRetailerAdmin.db.DB;
import com.cristovantamayo.veryBasicRetailerAdmin.gui.enums.Menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	
	// Comum

	@FXML
	private Pane groundPane;
	
	@FXML
	private VBox vBox;

	@FXML
	private TabPane tabPane;
	
	@FXML
	private MenuBar menu;
	
	
	// SERVICOS /////////////////////////////////////
	
	@FXML
	private MenuItem menuItemNovaOS;
	
	public void onMenuItemNovaOS() {
		loadView("tab", Menu.ORDEM_DE_SERICOS.getSigla(), "com/cristovantamayo/veryBasicRetailerAdmin/gui/OSNova.fxml", x -> {});
	}
	
	@FXML
	private MenuItem menuItemListarOS;

	public void onMenuItemListarOS() {
		loadView("tab", "", "com/cristovantamayo/veryBasicRetailerAdmin/gui/OSLista.fxml", x -> {});
	}

	@FXML
	private MenuItem menuItemServicos;
	
	public void onMenuItemServicos() {
		
	}
	
	// VENDAS /////////////////////////////////////////
	
	@FXML
	private MenuItem menuItemNOrc;
	
	public void onMenuItemNOrc() {
		
	}
	
	@FXML
	private MenuItem menuItemOrcs;
	
	public void onMenuItemOrcs() {
		
	}
	
	@FXML
	private MenuItem menuItemVendas;
	
	public void onMenuItemVendas() {
		
	}
	
	@FXML
	private MenuItem menuItemProdutos;
	
	public void onMenuItemProdutos() {
		
	}
	
	// CLIENTES ////////////////////////////////////////
	
	@FXML
	private MenuItem menuItemListCli;
	
	public void onMenuItemListCli() {
		
	}
	
	@FXML
	private MenuItem menuItemNCli;
	
	public void onMenuItemNCli() {
		
	}
	
	// CAIXA //////////////////////////////////////////
	
	@FXML
	private MenuItem menuItemCxDiario;
	
	public void onMenuItemCxDiario() {
		
	}
	
	@FXML
	private MenuItem menuItemCxMensal;
	
	public void onMenuItemCxMensal() {
		
	}
	
	@FXML
	private MenuItem menuItemAjustes;
	
	public void onMenuItemAjustes() {
		
	}
	
	// AJUDA /////////////////////////////////
	
	
	@FXML
	private MenuItem menuItemAboud;
	
	public void onMenuItemAbout() {
		loadView("scene", "", "/gui/About.fxml", x -> {});
	}
	
	////////////////////////////////////////////

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// Binding properties
		vBox.prefWidthProperty().bind(groundPane.widthProperty());
		tabPane.prefHeightProperty().bind(groundPane.heightProperty());
		tabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		
		
	}
	
	private synchronized <T> void loadView(String tipo, String title, String absolutePath, Consumer<T> initializingAction) {
		
		try {

			switch(tipo) {

				case "scene":
					FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutePath));
					VBox newVBox = loader.load();
					Scene mainScene = Main.getMainScene();
					VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
					
					Node mainMenu = mainVBox.getChildren().get(0);
					mainVBox.getChildren().clear();
					
					mainVBox.getChildren().add(mainMenu);
					mainVBox.getChildren().addAll(newVBox.getChildren());
					
					T controller = loader.getController();
					initializingAction.accept(controller);
			
					break;
		
				case "tab":
					
					DB.getConnection();
					
					// UI
		        	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(absolutePath));
		            
		            // Tab
		        	Tab tab = new Tab();
		        	tab.setText(title);
		        	tab.setContent(root);
		            
		        	tabPane.getTabs().add(tab);
		        	
		        	break;
			}
			
		} catch(IOException e) {
			//Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
			
	}
	
}
