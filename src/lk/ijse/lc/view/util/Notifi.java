/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.lc.view.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author LahiruPG
 */
public class Notifi {

    public Notifi(String title, String text) {
        javafx.scene.image.Image img = new javafx.scene.image.Image("lk/ijse/lc/image/tick_logo.png");
        Notifications notifiBulder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                        System.out.println("ASD");
                    }
                });
        //notifiBulder.darkStyle();
        notifiBulder.show();
    }

    public Notifi(String title, String text, String mod) {
       // javafx.scene.image.Image img = new javafx.scene.image.Image("lk/ijse/lc/image/tick_logo.png");
        Notifications notifiBulder = Notifications.create()
                .title(title)
                .text(text)
                //.graphic(graphic)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                        System.out.println("ASD");
                    }
                });
        //notifiBulder.darkStyle();
        notifiBulder.showError();
        
    }

}
