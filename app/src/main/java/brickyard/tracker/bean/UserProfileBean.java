package brickyard.tracker.bean;

public class UserProfileBean {

    private int id;
    private String name;
    private String email;
    private String country;
    private String language;
    private String currency;
    private String password;
    private String enablePassProtection;
    private String signedOut;

    public UserProfileBean(String name, String email, String country, String language, String currency, String password, String enablePassword, String signedOut) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.language = language;
        this.currency = currency;
        this.password = password;
        this.enablePassProtection = enablePassword;
        this.signedOut = signedOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getSignedOut() {
        return signedOut;
    }

    public String getEnablePassword() {
        return enablePassProtection;
    }

    public void setEnablePassword(String enablePassword) {
        this.enablePassProtection = enablePassword;
    }

    public void setSignedOut(String signedOut) {
        this.signedOut = signedOut;
    }


}
