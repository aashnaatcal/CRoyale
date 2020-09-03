import java.io.File;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
/** The class that runs the game
 *
 */
public class Game extends Application {
	MovableFighter test2;
	MovableFighter test3;
	double originalX;
	double originalY;
	double orgTranslateX;
	double orgTranslateY;
	EnergyBar bar;
	MenuItem howTo;
	MenuItem about;
	MenuItem beginner;
	MenuItem medium;
	MenuItem adv;
	ArenaWorld aWorld;
	MovableFighter str3;
	MovableFighter str2;
	MovableFighter str1;
	
	public static MediaPlayer mediaPlayer;
	public static Stage primaryStage;
	public static Scene sce;
	static Scene introScene;
	onPressedHandler onPressed = new onPressedHandler();
	onDraggedHandler onDragged = new onDraggedHandler();
	onReleasedHandler onReleased = new onReleasedHandler();
	
	/** Will use the launch method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**Will create the mediaPlayer and have music start playing. Will also create and show the introduction page, which has buttons that lead to a How To page, an About page, and a button that will start the game. The background shows the Clash Royale image.
	 * 
	 */
	public void start(Stage s) throws Exception {
		Media sound = new Media(new File("Music7.mp3").toURI().toString());
	    mediaPlayer = new MediaPlayer(sound);
	    mediaPlayer.play();
	    mediaPlayer.setVolume(10000);
		primaryStage = new Stage();
		HBox root2 = new HBox();
		introScene = new Scene(root2, 800, 500);
		BackgroundImage[] b2 = { new BackgroundImage(new Image("clashIntroPage.jpg"), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(800, 600, false, false, false, false)) };
		root2.setBackground(new Background(b2));
		primaryStage.setScene(introScene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
		MenuBar bar = new MenuBar();
		
		Menu pages = new Menu("Pages");
		howTo = new MenuItem("How To Play");
		howTo.setOnAction(new MenuHandler());
		about = new MenuItem("About");
		about.setOnAction(new MenuHandler());
		
		pages.getItems().add(howTo);
		pages.getItems().add(about);
		
		bar.getMenus().add(pages);
		
		Menu game = new Menu("Game");
		
		beginner = new MenuItem("Beginner");
		beginner.setOnAction(new MenuHandler());
		medium = new MenuItem("Intermediate");
		medium.setOnAction(new MenuHandler());
		adv = new MenuItem("Advanced");
		adv.setOnAction(new MenuHandler());
		
		game.getItems().add(beginner);
		game.getItems().add(medium);
		game.getItems().add(adv);
		
		bar.getMenus().add(game);
		
		root2.getChildren().add(bar);
		
		/*
		
		
		
		howTo = new Button("How To Play");
		howTo.setFont(new Font(20));
		root2.setSpacing(100);
		howTo.setPrefSize(200, 30);
		howTo.setOnAction(new ButtonHandler());
		start = new Button("Start Game");
		start.setFont(new Font(20));
		start.setPrefSize(200, 30);
		start.setOnAction(new ButtonHandler());
		root2.getChildren().add(howTo);
		about = new Button("About");
		about.setFont(new Font(20));
		about.setPrefSize(200, 30);
		about.setOnAction(new ButtonHandler());
		root2.getChildren().add(about);
		root2.getChildren().add(start);
		*/
		
	}
	/**Will change the music to be the game music. Will change the Scene to be the one that shows the game, and starts the game. 
	 * 
	 */
	private void resetSce() {
		mediaPlayer.stop();
		Media sound = new Media(new File("Music8.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		aWorld = new ArenaWorld();
		BorderPane root = new BorderPane();
		BackgroundImage[] b = { new BackgroundImage(new Image("clashBackground.png"), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(200, 200, false, false, true, false)) };
		aWorld.setBackground(new Background(b));
		root.setCenter(aWorld);
		sce = new Scene(root, 800, 800);
		root.setCenter(aWorld);
		aWorld.start();
		aWorld.setOnMousePressed(new MouseHandler());
		MovableFighter test1 = new MovableFighter(0, true, true);
		test2 = new MovableFighter(0, true, true);
		test3 = new MovableFighter(0, true, true);
		MovableFighter test4 = new MovableFighter(0, false, true);
		MovableFighter test5 = new MovableFighter(0, false, true);
		MovableFighter test6 = new MovableFighter(0, false, true);
		test1.setX(260);
		test1.setY(470);
		test2.setX(370);
		test2.setY(520);
		test3.setX(470);
		test3.setY(470);
		test4.setX(260);
		test4.setY(175);
		test5.setX(370);
		test5.setY(125);
		test6.setX(470);
		test6.setY(175);
		aWorld.add(test1);
		aWorld.add(test2);
		aWorld.add(test3);
		aWorld.add(test4);
		aWorld.add(test5);
		aWorld.add(test6);
		str1 = new MovableFighter(1, true, false);
		str1.setCursor(Cursor.HAND);
		str1.setOnMousePressed(onPressed);
		str1.setOnMouseDragged(onDragged);
		str1.setOnMouseReleased(onReleased);
		str1.setX(470);
		str1.setY(650);
		str2 = new MovableFighter(2, true, false);
		str2.setCursor(Cursor.HAND);
		str2.setOnMousePressed(onPressed);
		str2.setOnMouseDragged(onDragged);
		str2.setOnMouseReleased(onReleased);
		// **********************
		str3 = new MovableFighter(3, true, false);
		str3.setCursor(Cursor.HAND);
		str3.setOnMousePressed(onPressed);
		str3.setOnMouseDragged(onDragged);
		str3.setOnMouseReleased(onReleased);
		str2.setX(370);
		str2.setY(650);
		str3.setX(270);
		str3.setY(650);
		aWorld.add(str3);
		aWorld.add(str2);
		aWorld.add(str1);
		bar = new EnergyBar();
		bar.setX(275);
		bar.setY(700);
		aWorld.add(bar.getRect());
		aWorld.add(bar);
		bar.removeEnergy(10);

	}

	private class MouseHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			if (event.getButton() == MouseButton.SECONDARY) {
				System.out.println("" + event.getX() + " " + event.getY());

			}

		}

	}
	
	private class MenuHandler implements EventHandler<ActionEvent> {


		@Override
		public void handle(ActionEvent event) {
			if (event.getSource().equals(howTo)) {
				Stage s = new Stage();
				WebView web = new WebView();
				Scene scen = new Scene(web, 500, 500);
				s.setScene(scen);
				String abs = new File("howToClashRoyale.html").getAbsolutePath();
				web.getEngine().load("file:///" + abs);
				s.show();
			}
			else if (event.getSource().equals(about)) {
				Stage s = new Stage();
				WebView web = new WebView();
				Scene scen = new Scene(web, 500, 500);
				s.setScene(scen);
				String abs = new File("aboutPage.html").getAbsolutePath();
				web.getEngine().load("file:///" + abs);
				s.show();
			}
			else if (event.getSource().equals(beginner)) {
				resetSce();
				primaryStage.setScene(sce);
				aWorld.setIntensity(1);
			}
			else if (event.getSource().equals(medium)) {
				resetSce();
				primaryStage.setScene(sce);
				aWorld.setIntensity(2);
			}
			else if (event.getSource().equals(adv)) {
				resetSce();
				primaryStage.setScene(sce);
				aWorld.setIntensity(3);
			}
			
		}
		
	}

	/*private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource().equals(howTo)) {
				Stage s = new Stage();
				WebView web = new WebView();
				Scene scen = new Scene(web, 500, 500);
				s.setScene(scen);
				String abs = new File("howToClashRoyale.html").getAbsolutePath();
				web.getEngine().load("file:///" + abs);
				s.show();
			} else if (event.getSource() == about) {
				Stage s = new Stage();
				WebView web = new WebView();
				Scene scen = new Scene(web, 500, 500);
				s.setScene(scen);
				String abs = new File("aboutPage.html").getAbsolutePath();
				web.getEngine().load("file:///" + abs);
				s.show();
			} else if (event.getSource() == start) {
				resetSce();
				primaryStage.setScene(sce);
			}

		}
	}
*/
	private class onPressedHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			originalX = event.getSceneX();
			originalY = event.getSceneY();
			orgTranslateX = ((MovableFighter) event.getSource()).getX();
			orgTranslateY = ((MovableFighter) event.getSource()).getY();
			((MovableFighter) event.getSource()).setOpacity(0.5);
		}

	}

	private class onDraggedHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			double offX = event.getSceneX() - originalX;
			double offY = event.getSceneY() - originalY;
			((MovableFighter) event.getSource()).setX(orgTranslateX + offX);
			((MovableFighter) event.getSource()).setY(orgTranslateY + offY);
		}

	}

	private class onReleasedHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			// if the event happens within the brown bounds
			if (event.getSceneX() >= 220 && event.getSceneX() <= 570 && event.getSceneY() >= 350
					&& event.getSceneY() <= 610) {
				// if the energyBar has enough energy to place the fighter on the field
				if (((MovableFighter) event.getSource()).getEnergyCost() <= bar.energyAmount()
						&& ((MovableFighter) event.getSource()).onField == false) {
					((MovableFighter) event.getSource()).setOnField(true);
					bar.removeEnergy(((MovableFighter) event.getSource()).getEnergyCost());
					((MovableFighter) event.getSource()).setOnMousePressed(null);
					((MovableFighter) event.getSource()).setOnMouseDragged(null);
					((MovableFighter) event.getSource()).setOnMouseReleased(null);
					if (((MovableFighter) event.getSource()).getEnergyCost() == 6) {
						str3 = new MovableFighter(3, true, false);
						str3.setX(270);
						str3.setY(650);
						str3.setCursor(Cursor.HAND);
						str3.setOnMousePressed(new onPressedHandler());
						str3.setOnMouseDragged(new onDraggedHandler());
						str3.setOnMouseReleased(new onReleasedHandler());
						aWorld.add(str3);
					} else if (((MovableFighter) event.getSource()).getEnergyCost() == 4) {
						str2 = new MovableFighter(2, true, false);
						str2.setX(370);
						str2.setY(650);
						str2.setCursor(Cursor.HAND);
						str2.setOnMousePressed(new onPressedHandler());
						str2.setOnMouseDragged(new onDraggedHandler());
						str2.setOnMouseReleased(new onReleasedHandler());
						aWorld.add(str2);
					} else {
						str1 = new MovableFighter(1, true, false);
						str1.setX(470);
						str1.setY(650);
						str1.setCursor(Cursor.HAND);
						str1.setOnMousePressed(new onPressedHandler());
						str1.setOnMouseDragged(new onDraggedHandler());
						str1.setOnMouseReleased(new onReleasedHandler());
						aWorld.add(str1);
					}
				} else {
					if (((MovableFighter) event.getSource()).getEnergyCost() == 6) {
						((MovableFighter) event.getSource()).setX(270);
						((MovableFighter) event.getSource()).setY(650);
					} else if ((((MovableFighter) event.getSource()).getEnergyCost() == 4)) {
						((MovableFighter) event.getSource()).setX(370);
						((MovableFighter) event.getSource()).setY(650);
					} else {
						((MovableFighter) event.getSource()).setX(470);
						((MovableFighter) event.getSource()).setY(650);
					}
				}
			} else {
				if (((MovableFighter) event.getSource()).getEnergyCost() == 6) {
					((MovableFighter) event.getSource()).setX(270);
					((MovableFighter) event.getSource()).setY(650);
				} else if ((((MovableFighter) event.getSource()).getEnergyCost() == 4)) {
					((MovableFighter) event.getSource()).setX(370);
					((MovableFighter) event.getSource()).setY(650);
				} else {
					((MovableFighter) event.getSource()).setX(470);
					((MovableFighter) event.getSource()).setY(650);
				}
			}
			((MovableFighter) event.getSource()).setOpacity(1);
		}
		

	}

	public static class OKButtonHandler implements EventHandler<DialogEvent> {

		@Override
		public void handle(DialogEvent event) {
			mediaPlayer.stop();
			Media sound = new Media(new File("Music7.mp3").toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
			primaryStage.setScene(introScene);
		}

	}
}
