package commonLibs.ui.user;

import com.github.javafaker.Faker;

public class User {

    private Faker faker = new Faker();

    private String email;
    private String password;

    public User(States state){
        switch (state) {
            case FULL:
                this.email = faker.internet().emailAddress();
                this.password = faker.internet().password(12,16, true);
                break;
            case EMAIL_ONLY:
                this.email = faker.internet().emailAddress();
                break;
            case PASS_ONLY:
                this.password = faker.internet().password(12,16, true);
                break;
            default:
                this.email = "";
                this.password = "";
                break;
        }
    }

    public User() {
        this.email = "";
        this.password = "";
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public enum States {
        FULL, EMAIL_ONLY, PASS_ONLY
    }
}
