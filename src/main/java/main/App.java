/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Controller;
import dao.Dao;
import dao.DaoFileImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.View;

/**
 *
 * @author kylecieskiewicz
 */
public class App {

    public static void main(String[] args) throws Exception {
        UserIO myIo = new UserIOConsoleImpl();
        View myView = new View(myIo);
        Dao myDao = new DaoFileImpl();
        Controller controller
                = new Controller(myDao, myView);
        controller.run();
    }

}
