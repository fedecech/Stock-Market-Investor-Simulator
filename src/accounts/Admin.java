package accounts;

public class Admin extends Account {
    private String token;
    public Admin(String username, String password){
        super(username, password, AccountType.ADMIN);
        this.token = generateToken();
    }

    public String generateToken(){
        return getUsername();
    }
}
