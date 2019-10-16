package com.cristovantamayo.veryBasicRetailerAdmin.gui;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import com.cristovantamayo.veryBasicRetailerAdmin.gui.util.Executor;
import com.cristovantamayo.veryBasicRetailerAdmin.gui.util.UI;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Cliente;
import com.cristovantamayo.veryBasicRetailerAdmin.model.services.ClienteService;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OSNovaController implements Initializable {
	
	@FXML
	private AnchorPane tabGnd;

	@FXML
	private GridPane gridPane;

	@FXML
	private TextField textFieldCliente;

	@FXML
	private ImageView loaderBusca;

	//////////////////////////////////////////////

	private ClienteService service;

	private List<Cliente> options = null;
	private String predicate = "";
	private Integer compared = 1000;
	private Timer timer = null;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		service = new ClienteService();
		prepareSearchField(gridPane);
		
	}

	private void prepareSearchField(GridPane container) {
		
		textFieldCliente.textProperty().addListener((observable, oldValue, newValue) -> {
			// Retorna o elemento da lista
			Node n = getNodeFromGridPane(container, 0, 3);
			// Remove se existir
			if (n != null) {
				container.getChildren().removeAll(Arrays.asList(n));
			}
		});
		
		
		
		// dimensiona o campo de busca segundo o container.
		textFieldCliente.prefWidthProperty().bind(container.prefWidthProperty());
		
		textFieldCliente.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				KeyCode code = event.getCode();
				if (KeyCode.DOWN == code) {
					container.lookup("#ddl-1").requestFocus();
				} else if(KeyCode.ENTER == code) {
					System.out.println("Search: " + textFieldCliente.getText());
					search(textFieldCliente.getText(), container);
				} else {
					
					if (timer != null) {
						timer.cancel();
					}
					
					String content = textFieldCliente.getText();
					
					/*System.out.println(" ----------- content: " + content);
					System.out.println(" ----------- predicate: " + predicate);
					System.out.println("compared: " + compared + ", predicate: " + predicate.compareTo(content));*/
					
					// Se houver conteudo e diferenca de conteudo
					if (content.length()>0 && compared != predicate.compareTo(content)) {
						
						// Igualar conteúdo
						predicate = content;
						compared = predicate.compareTo(content);

						timer = new Timer();

						Executor executor = new Executor(() -> {
							System.out.println("Search: " + content);
							search(content, container);
						});

						timer.schedule(executor, 500);
					}
					
					if(0 == content.length()) {
						predicate = "";
					}

				}

			}

		});

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				textFieldCliente.requestFocus();
			}
		});
		
	}

	private void search(String newValue, GridPane container) {
		if (!newValue.isEmpty()) {
			loaderBusca.setVisible(true);
			List<Cliente> lista = service.findByName(newValue, 15);

			if (!lista.isEmpty()) {
				
				options = lista;

				VBox vb = populateDropDownMenu(newValue, options, textFieldCliente);
				vb.prefWidthProperty().bind(container.prefWidthProperty());
				container.add(vb, 0, 3);
			}
			loaderBusca.setVisible(false);
		}
	}

	/**
	 * Return the Node from indicated position.
	 * @param gridPane
	 * @param col
	 * @param row
	 * @return Node
	 */
	private Node getNodeFromGridPane(GridPane gridPane, Integer col, Integer row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Populate the DropDown Menu
	 * @param text
	 * @param options
	 * @return VBox
	 */
	public static VBox populateDropDownMenu(String text, List<Cliente> options, TextField textField) {
		VBox dropDownMenu = new VBox();
		dropDownMenu.setId("vbxBusca");
		dropDownMenu.setAlignment(Pos.CENTER_LEFT); // all these are optional and up to you
		
		int counter = 1;
		
		for (Cliente cliente : options) {
			if (!text.replace(" ", "").isEmpty() && cliente.getNome().toUpperCase().contains(text.toUpperCase())) {
				
				Label label = mkDropDownLine(cliente, dropDownMenu, counter, textField);
				System.out.println(label.getClass() + " --> " + cliente.getNome());
				dropDownMenu.getChildren().add(label);
				counter++;
				
			}
		}

		return dropDownMenu; // at the end return the VBox (i.e. drop-down menu)
	}

	/**
	 * Make a Label as a active DropDown line
	 * @param label
	 * @param dropDownMenu
	 * @return
	 */
	private static Label mkDropDownLine(Cliente cliente, VBox dropDownMenu, Integer counter, TextField textField) {
		
		Label label = new Label(cliente.getNome());
		
		label.setId("ddl-"+String.valueOf(counter));
		label.getStyleClass().add("lbBusca");
		
		label.prefWidthProperty().bind(dropDownMenu.prefWidthProperty());
		
		label.focusedProperty().addListener( (observable, oldValue, newValue) -> {
			
				if (newValue){
					label.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
					label.setCursor(Cursor.HAND);
		        } else {
		        	label.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		        }
				
		});
		
		label.setOnKeyPressed(event -> {
				
			KeyCode code = event.getCode();
			if (KeyCode.DOWN == code) {
				
				String texto = event.getSource().toString().substring(13);
				String[] split = texto.split(",");
				Integer id = Integer.parseInt(split[0]);
				id++;
				if(dropDownMenu.lookup("#ddl-" + id) != null) {
					dropDownMenu.lookup("#ddl-" + id).requestFocus();
				}
				
			} else if (KeyCode.UP == code) {
				
				String texto = event.getSource().toString().substring(13);
				String[] split = texto.split(",");
				Integer id = Integer.parseInt(split[0]);
				id--;
				if(dropDownMenu.lookup("#ddl-" + id) != null) {
					dropDownMenu.lookup("#ddl-" + id).requestFocus();
				}
				
			} else if (KeyCode.ENTER == code) {
				String texto = event.getSource().toString().substring(13);
				String[] split = texto.split(",");
				Integer id = Integer.parseInt(split[0]);
				String[] split2 = texto.split("'");
				String nome = split2[1];
				
				textField.setText(nome);
				textField.getProperties().put("id", id);
				
				dropDownMenu.getChildren().remove(0);
			}
			
		});
		
		
		label.setOnMouseClicked(event -> {
			
			String texto = event.getSource().toString().substring(13);
			String[] split = texto.split(",");
			Integer id = Integer.parseInt(split[0]);
			String[] split2 = texto.split("'");
			String nome = split2[1];
			
			textField.setText(nome);
			textField.getProperties().put("id", id);
			
			dropDownMenu.getChildren().remove(0);	
		});
		
		UI.dropDownItem(label);

		return label;
	}
	
}
