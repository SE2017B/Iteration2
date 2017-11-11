package controllers;


public class LogoutController implements ControllableScreen {
    private ScreenController parent;

    public void setParentController(ScreenController parent){
        this.parent = parent;
    }

    public void init(){}

}
