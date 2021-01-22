package dev.ahrsoft.dagger2curso.ui.login;

import dev.ahrsoft.dagger2curso.model.User;

public class LoginPresenter implements Login.Presenter{

    private Login.View view;
    private User user;

    public LoginPresenter(User user){
        this.user = user;
    }

    @Override
    public void setView(Login.View view) {
        this.view = view;
    }

    @Override
    public void validaUser(String userName, String pass) {
        if (view != null){
            if (userName.equals("alfredo") && pass.equals("1234")){
                user.setUsername("Alfredo Hdez");
                user.setEdad("25");
                view.usuarioValido();
            }else {
                view.error();
            }
        }
    }
}
