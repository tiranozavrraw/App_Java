public class User {
    int userId;
    String name;
    String address;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    User (String name, String address){

        this.name = name;
        this.address = address;

    }

    User (String name){

        this.name = name;

    }

    public void registerUser (User user) {


    }
}

