package dev.ahrsoft.dagger2curso.ui.profile;

import dev.ahrsoft.dagger2curso.model.User;

public interface Profile {

    interface View{
        void showUser(User user);
        void logout();
    }

    interface Presenter{
        void setView(Profile.View view);
        void updateUser(User updateUser);
        void logout();
    }

}
