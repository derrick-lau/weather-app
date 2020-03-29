package org.openjfx.services;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.openjfx.repository.ISideMenuScene;

public class SideMenuScene implements ISideMenuScene {

    @Override
    public void sideMenuInitialization(AnchorPane mainPane, AnchorPane sidePane, ImageView menuButton) {
        sidePane.setVisible(false);

        mainPane.setVisible(false);

        fadeTransition(.2,mainPane,1,0);

        translateTransition(.2,sidePane);

        handleMainPaneClicked(mainPane, sidePane);

        handleMenuButton(mainPane, sidePane, menuButton);
    }


    private void handleMenuButton(AnchorPane mainPane, AnchorPane sidePane, ImageView menuButton)
    {
        menuButton.setOnMouseClicked(e -> {
            mainPane.setVisible(true);
            sidePane.setVisible(true);

            fadeTransition(.4,mainPane,0,0.2);
            translateTransition(.4, sidePane);
        });
    }

    private void handleMainPaneClicked (AnchorPane mainPane, AnchorPane sidePane)
    {
        mainPane.setOnMouseClicked(event -> {

            fadeTransition(.4, mainPane,.2,0);
            translateTransition(.4, sidePane);
            TranslateTransition translateTransition=translateTransition(.4,sidePane);

            translateTransition.setOnFinished(e -> {

                sidePane.setVisible(false);
                mainPane.setVisible(false);
            });
        });
    }



    private TranslateTransition translateTransition(double duration, Node node)
    {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.play();
        return translateTransition;
    }


    private FadeTransition fadeTransition(double duration, Node node, double from, double to)
    {
        FadeTransition fadeTransition = new FadeTransition (Duration.seconds(duration), node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        fadeTransition.play();
        return fadeTransition;
    }

}
