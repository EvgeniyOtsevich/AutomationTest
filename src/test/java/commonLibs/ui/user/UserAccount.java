package commonLibs.ui.user;

import com.github.javafaker.Faker;

public class UserAccount {
    private Faker faker = new Faker();

    private String email = "";
    private String password = "";

    public static Create initialize(){
        return new UserAccount().new Create();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public class Create {

        public Create email(String email){
            UserAccount.this.email = email;
            return this;
        }

        public Create email(){
            UserAccount.this.email = faker.internet().emailAddress();
            return this;
        }

        public Create password(String password){
            UserAccount.this.password = password;
            return this;
        }

        public Create password(){
            UserAccount.this.password = faker.internet().emailAddress();
            return this;
        }

        public UserAccount build(){
            return UserAccount.this;
        }


    }
}
