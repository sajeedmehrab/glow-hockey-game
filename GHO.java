/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gho;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Mehrab
 */
public class GHO extends Application {

    Circle ball;
    double dist_betn_p1_ball;
    double dx;
    double dy;
    double dx_com;
    double dy_com;
    double dg=0.01;
    double amount_of_glow=0;
    double mouse_x = 200;
    double mouse_y = 50;
    boolean P1won;
    Circle Goal2;
    Rectangle rect1;
    Rectangle rect2;
    Rectangle boundlu;
    Rectangle boundll;
    Rectangle boundru;
    Rectangle boundrl;
    Rectangle boundr;
    Rectangle boundtl;
    Rectangle boundtr;
    Rectangle boundbl;
    Rectangle boundbr;
    //static int score=0;
    private LongProperty score = new SimpleLongProperty(0);
    private LongProperty scorecom = new SimpleLongProperty(0);
    public boolean gameOn = false;

    private static final Duration TRANSLATE_DURATION = Duration.seconds(0.25);
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @Override
    public void start(Stage theStage) throws Exception {

        theStage.setTitle("Glow Hockey");
        
        //theStage.show();
        //theStage.setScene(GameScene);
        
        Group root_start = new Group();
        Scene StartScene =  new Scene(root_start,400,500);
        StartScene.setFill(Color.BLACK);
        
        Group overo = new Group();
        Scene GameOvero = new Scene(overo , 400, 500);
        GameOvero.setFill(Color.BLACK);
        Text opwon = new Text();
        opwon.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
        opwon.setFill(Color.WHITE);
        opwon.setLayoutX(150);
        opwon.setLayoutY(200);
        opwon.textProperty().bind(Bindings.createStringBinding(() -> ("Opponent has won" +"\n" + "Your Score: " + score.get()) + "\n" + "Opponent's Score: " + scorecom.get(), score, scorecom));
        overo.getChildren().addAll(opwon);
        
//        Text ywon = new Text();
//        ywon.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
//        ywon.textProperty().bind(Bindings.createStringBinding(() -> ("You have won" +"\n" + "Your Score: " + score.get()), score));
//        
//        
        Group overy = new Group();
        Scene GameOvery = new Scene(overy , 400, 500);
        GameOvery.setFill(Color.BLACK);
        Text ywon = new Text();
        ywon.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
        ywon.setFill(Color.WHITE);
        ywon.setLayoutX(150);
        ywon.setLayoutY(200);
        ywon.textProperty().bind(Bindings.createStringBinding(() -> ("You have won" +"\n" + "Your Score: " + score.get()) + "\n" + "Opponent's Score: " + scorecom.get(), score, scorecom));
        overy.getChildren().addAll(ywon);
        
        
        Button StartGame = new Button("Start Game");
        StartGame.setLayoutX(150);
        StartGame.setLayoutY(300);
        root_start.getChildren().add(StartGame);
        Image gh = new Image("GH.jpg");
        ImageView iv_gh = new ImageView();
        iv_gh.setImage(gh);
        iv_gh.setFitWidth(400);
        iv_gh.setPreserveRatio(true);
        iv_gh.setSmooth(true);
         
          final Timeline start_loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                amount_of_glow+=dg;
                if(amount_of_glow>0.8 || amount_of_glow<0){
                    dg = -dg;
                }
                iv_gh.setEffect(new Glow(amount_of_glow));
            }
              
          }));
          start_loop.setCycleCount(Timeline.INDEFINITE);
        start_loop.play();
        // iv_gh.setCache(true);
//        TextField tfield = new TextField();
//        tfield.setLayoutX(120);
//        tfield.setLayoutY(450);
//        tfield.setStyle("-fx-control-inner-background:lightgreen");
//        tfield.setPrefWidth(220);
//        Text text = new Text(70, 670, "Enter you username.");
//        text.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
//        text.setStyle("-fx-fill:orange");
//        tfield.toFront();
        root_start.getChildren().addAll(iv_gh);
        theStage.setScene(StartScene);
        theStage.show();
        
        
        Group root = new Group();
        Scene GameScene = new Scene(root, 600, 500);
                
        StartGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Gello world");
                theStage.setScene(GameScene);
                theStage.show();
            }
        });
//        
//        Glow e2 = new Glow();
//        e2.setLevel(1.0);
//       
//        root.setEffect(e2);

        
        
        Canvas GameCanvas = new Canvas(400, 600);
        root.getChildren().add(GameCanvas);

        GraphicsContext gc = GameCanvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        gc.fillRect(0, 0, 400, 500);
        gc.setStroke(Color.WHITE);

        rect1 = new Rectangle(169, 0, 63, 35);
        rect1.setStroke(Color.BLACK);//black
       // rect1.toFront();
        root.getChildren().add(rect1);
        

        rect2 = new Rectangle(169, (500 - 35), 63, 35);
        rect2.setStroke(Color.BLACK);//black
        //rect2.toFront();
        root.getChildren().add(rect2);
        

        Circle MidField;
        MidField = new Circle(200, 250, 75);
        MidField.setStroke(Color.GRAY);
        root.getChildren().add(MidField);

        Circle Goal1 = new Circle(200, 0, 75);
        Goal1.setStroke(Color.GRAY);
        root.getChildren().add(Goal1);

        Goal2 = new Circle(200, 500, 75);
        Goal2.setStroke(Color.GRAY);
        root.getChildren().add(Goal2);

        
        
        Circle Player1 = new Circle(200, 50, 25);
        Player1.setFill(Color.RED);
        root.getChildren().add(Player1);
        Player1.toFront();
        DropShadow ds = new DropShadow();
//        ds.setOffsetX(3.0);
//        ds.setOffsetY(3.0);
        
        Circle Player2 = new Circle(200, (500 - 50), 25);
        Player2.setFill(Color.GREENYELLOW);
        root.getChildren().add(Player2);

        Line midline = new Line(0, 250, 400, 250);
        midline.setStroke(Color.GRAY);
        root.getChildren().add(midline);

        ball = new Circle(200, 250, 20);
        ball.setFill(Color.IVORY);
        root.getChildren().add(ball);
    
        boundlu = new Rectangle(0, 0, 6, 250);
        boundlu.setFill(Color.LIME);

        boundll = new Rectangle(0, 250, 6, 250);
        boundll.setFill(Color.RED);

        boundru = new Rectangle(394, 0, 6, 250);
        boundru.setFill(Color.LIME);

        boundrl = new Rectangle(394, 250, 6, 250);
        boundrl.setFill(Color.RED);

        boundtl = new Rectangle(6, 0, 116, 6);
        boundtl.setFill(Color.LIME);

        boundbl = new Rectangle(6, 494, 116, 6);
        boundbl.setFill(Color.RED);

        boundtr = new Rectangle(284, 0, 116, 6);
        boundtr.setFill(Color.LIME);

        boundbr = new Rectangle(284, 494, 116, 6);
        boundbr.setFill(Color.RED);
        root.getChildren().addAll(boundlu, boundtl, boundbl, boundtr, boundbr, boundll, boundru, boundrl);

        Text ShowScore = new Text();
        ShowScore.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
        //ShowScore.textProperty().bind(Bindings.createStringBinding(score.get(), score));
        ShowScore.textProperty().bind(Bindings.createStringBinding(() -> ("Your Score: " + score.get()), score));
       
        Text ShowScoreCom = new Text();
        ShowScoreCom.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 20));
        //ShowScore.textProperty().bind(Bindings.createStringBinding(score.get(), score));
        ShowScoreCom.textProperty().bind(Bindings.createStringBinding(() -> ("Opponent Score: " + scorecom.get()), scorecom));


        ShowScore.setX(425);
        ShowScore.setY(250);
        
        ShowScoreCom.setX(405);
        ShowScoreCom.setY(300);
        
        root.getChildren().addAll(ShowScore, ShowScoreCom);

        // ShowScore.setX();
//         Button play = new Button("PLAY");
//         
//          root.getChildren().add(play);
//         if (play.isPressed()){
//             gameOn = true;
//         }
//          play.setOnAction(PressPlay);
//         System.out.println(gameOn);
        //if(gameOn==true){
        GameScene.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                mouse_x = t.getSceneX();
                mouse_y = t.getSceneY();

            }
        });
        //}

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent t) {

                System.out.println(mouse_x+"  "+mouse_y);
                if (mouse_y <= 250&&mouse_x>=25&&mouse_x<=375) {
                    Player1.setCenterX(mouse_x);
                    Player1.setCenterY(mouse_y);
                }

                if ((Player1.intersects(ball.getBoundsInLocal()))) {
                    dx = (ball.getCenterX() - Player1.getCenterX()) * 0.1;
                    dy = (ball.getCenterY() - Player1.getCenterY()) * 0.1;
                }

                
                //dummy code
//                if (Player2.intersects(boundll.getBoundsInLocal()) || Player2.intersects(boundrl.getBoundsInLocal())) {
//                    dx2 = -dx2;
//                    Player2.setCenterX(Player2.getCenterX() + dx2);
//                } else {
//                    Player2.setCenterX(Player2.getCenterX() + dx2);
//                }

                if(ball.getCenterY()>250){
                    if(Player2.getCenterX()>(Player2.getRadius()+2*ball.getRadius()) && Player2.getCenterX()<(400 - (Player2.getRadius()+2*ball.getRadius())) ){
                   dx_com = (ball.getCenterX() - Player2.getCenterX()) * 0.03;//might need to be inverted
                   dy_com = (ball.getCenterY() - Player2.getCenterY()) * 0.03;
                   Player2.setCenterX(Player2.getCenterX() + dx_com);
                   Player2.setCenterY(Player2.getCenterY()+dy_com);
                    }
                    else{
                        dx_com=-(50*dx_com);
                        dy_com=-(50*dy_com);
                        Player2.setCenterX(Player2.getCenterX() + dx_com);
                        Player2.setCenterY(Player2.getCenterY()+dy_com);
                    }
                }
               else if(ball.getCenterY()<250){
                   if(Player2.getCenterY()<450){
                   dx_com = (ball.getCenterX() - Goal2.getCenterX() ) * 0.005;
                   dy_com = (ball.getCenterY() - Goal2.getCenterY()) * 0.005;
                   Player2.setCenterX(Player2.getCenterX() - dx_com);
                   Player2.setCenterY(Player2.getCenterY()-dy_com);
                   }
                   
                }
                
                if ((Player2.intersects(ball.getBoundsInLocal()))) {//sets the direction of the ball when player 2 hits
                    dx = (ball.getCenterX() - Player2.getCenterX()) * 0.15;
                    dy = (ball.getCenterY() - Player2.getCenterY()) * 0.15;
//                    if(ball.intersects(boundll.getBoundsInLocal())&&ball.intersects(boundbl.getBoundsInLocal())){
//                        dx_com=-dx_com;
//                        dy_com = -dy_com;
//                        Player2.setCenterX(Player2.getCenterX() + dx_com);
//                   Player2.setCenterY(Player2.getCenterY()+dy_com);
//                    }
                }

                if (ball.intersects(boundtr.getBoundsInLocal()) || ball.intersects(boundtl.getBoundsInLocal()) || ball.intersects(boundbl.getBoundsInLocal()) || ball.intersects(boundbr.getBoundsInLocal())) {
                    dy = -1.1 * dy;
                    ball.setCenterX(ball.getCenterX() + dx);
                    ball.setCenterY(ball.getCenterY() + dy);
                    //System.out.println("Helloooooo");
                } else if (ball.intersects(boundlu.getBoundsInLocal()) || ball.intersects(boundru.getBoundsInLocal()) || ball.intersects(boundll.getBoundsInLocal()) || ball.intersects(boundrl.getBoundsInLocal())) {
                    dx = -1.1 * dx;
                    ball.setCenterX(ball.getCenterX() + dx);
                    ball.setCenterY(ball.getCenterY() + dy);
                    //System.out.println("HEllo");
                } else {
                    ball.setCenterX(ball.getCenterX() + dx);
                    ball.setCenterY(ball.getCenterY() + dy);
                }
                if (ball.getCenterY()<=0) {
                    ball.setCenterX(200);
                    ball.setCenterY(175);
                    dx = 0;
                    dy = 0;
                    // score--;
                    scorecom.set(scorecom.get() + 1);
                    Player1.setCenterX(200);
                    Player1.setCenterY(50);
                    Player2.setCenterX(200);
                    Player2.setCenterY(450);
                    mouse_x = 200;
                    mouse_y = 50;
                    System.out.println(score);
                }
//
                if (ball.getCenterY()>=500) {
                    ball.setCenterX(200);
                    ball.setCenterY(175+150);
                    dx = 0;
                    dy = 0;
                    // score++;
                    score.set(score.get() + 1);
                    Player1.setCenterX(200);
                    Player1.setCenterY(50);
                    Player2.setCenterX(200);
                    Player2.setCenterY(450);
                    mouse_x = 200;
                    mouse_y = 50;
                    System.out.println(score);
                }
                
//                if(scorecom.get()==2){
//                    computerwon(theStage, GameOvero, loop);
//                   
//           }
              if(scorecom.get()==7){
                  theStage.setScene(GameOvero);
                  theStage.show();
              }
              
              if(score.get()==7){
                  theStage.setScene(GameOvery);
                  theStage.show();
              }
               
            }
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
       
        
       // System.out.println(scorecom.get);
        
//         if(scorecom.get()==2){
//                    theStage.setScene(GameOvero);
//                    theStage.show();
//           }

        Button play = new Button("PLAY");
        play.setLayoutX(475);
        play.setLayoutY(96);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Gello world");
                gameOn = true;
            }

        });
       
        Button pause = new Button("PAUSE");
         pause.setLayoutX(470);
        pause.setLayoutY(155);
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello world");
                gameOn = false;
            }

        });

        final Timeline loop2 = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (gameOn) {
                    loop.play();
                } else if (!gameOn) {
                    loop.pause();
                }
            }

        }));
        loop2.setCycleCount(Timeline.INDEFINITE);
        loop2.play();

        root.getChildren().addAll(pause, play);
        
        final Timeline check_score = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if((scorecom.get()==7) || (score.get()==7)){
                   loop.stop();
               }
            }
              
          }));
        check_score.setCycleCount(Timeline.INDEFINITE);
        check_score.play();

    }
//    public double calculatedistance(double x1, double x2, double y1, double y2){
//          return (Math.sqrt(Math.pow((x2-x1), 2)+ Math.pow((y2-y1),2)));
//    }

//    EventHandler<ActionEvent> PressPlay = new EventHandler<ActionEvent>(){
//        @Override
//        public void handle(ActionEvent e) {
//           gameOn = true;
//        }
//        
//    };
    
    void computerwon(Stage theStage, Scene GameOvero, Timeline loop){
                    theStage.setScene(GameOvero);
                    theStage.show();
                    loop.stop();
        }
    
    public static void main(String[] args) {
        launch(args);
    }

}
