package dev.ahrsoft.dagger2curso.ui.WebService;

import dev.ahrsoft.dagger2curso.model.User;

public interface WebService {

    interface View{
        void showUser(User user);
    }

    interface Presenter{
        void setView(WebService.View view);
        void solicitudWebService();
    }

}
