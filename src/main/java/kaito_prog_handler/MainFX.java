package kaito_prog_handler;

import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private static File f;
	private static BorderPane bpCenter = new BorderPane();
	private static WebEngine webEngine;
	private static ListView<JavaTool> list;
	private static String rootPath;
	private static ToggleButton buttonChangeLog;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		rootPath = "folderhere";
		primaryStage.setTitle("Overlord");
		BorderPane root = new BorderPane();

		WebView browser = new WebView();
		webEngine = browser.getEngine();
		f = new File(rootPath + "welcome.html");
		webEngine.load(f.toURI().toURL().toString());

		list = new ListView<JavaTool>();

		// "Project1", "Project2", "Project3", "Project4"
		JavaTool tool1 = new JavaTool("Loggetta", "loggetta", "");
		JavaTool tool2 = new JavaTool("CometJNLP", "cometJNLP", "");
		ObservableList<JavaTool> items = FXCollections.observableArrayList(tool1, tool2);
		list.setItems(items);
		list.setCellFactory(new Callback<ListView<JavaTool>, ListCell<JavaTool>>() {
			@Override
			public ListCell<JavaTool> call(ListView<JavaTool> p) {
				ListCell<JavaTool> cell = new ListCell<JavaTool>() {
					@Override
					protected void updateItem(JavaTool t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getName());
						}
					}
				};
				return cell;
			}
		});
		list.setOnMouseClicked(event -> {
			addButtons();
			String html = list.getSelectionModel().getSelectedItem().getFolder() + "/index.html";
			System.out.println(html);
			f = new File(rootPath + html);
			try {
				webEngine.load(f.toURI().toURL().toString());
				html = list.getSelectionModel().getSelectedItem().getFolder() + "/changelog.html";
				boolean changelogExists = new File(rootPath + html).exists();
				buttonChangeLog.setDisable(!changelogExists);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.setLeft(list);

		bpCenter.setCenter(browser);

		root.setCenter(bpCenter);

		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}

	private static void addButtons() {
		Button buttonInstall = new Button("Install");
		buttonInstall.setOnAction(event -> {
			System.out.println("Hello World!");
		});

		Button buttonRemove = new Button("Remove");
		buttonRemove.setOnAction(event -> {
			System.out.println("remove");
		});

		Button updateTool = new Button("Updaten");
		updateTool.setOnAction(event -> {
			System.out.println("updateTool");
		});

		buttonChangeLog = new ToggleButton("Changelog");
		buttonChangeLog.setOnAction(event -> {
			String html = "";

			if (buttonChangeLog.isSelected()) {
				html = list.getSelectionModel().getSelectedItem().getFolder() + "/changelog.html";
				System.out.println(html);
			} else {
				System.out.println(html);
				html = list.getSelectionModel().getSelectedItem().getFolder() + "/index.html";
			}

			f = new File(rootPath + html);
			System.out.println(">" + f.getPath());
			try {
				webEngine.load(f.toURI().toURL().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		HBox vBox = new HBox(10);
		vBox.setPadding(new Insets(10));
		vBox.getChildren().addAll(buttonInstall, buttonRemove, updateTool, buttonChangeLog);
		vBox.setSpacing(20);
		bpCenter.setBottom(vBox);

	}
}
