package Models;

import Commons.Commons;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class User extends Model {
    // Table and model attributes
    Integer id, age;
    String email, pass, names;
    Boolean isTrainer;
    Timestamp createdAt, lastLogin;

    // A default user is used to use the query methods
    boolean isDefaultUser = false;

    /**
     * Sets the name of the table and its primary key column
     *
     */
    private User() {
        super("users", "id");
    }

    public User(Integer id, Integer age, String names, String email, String pass, boolean isTrainer, Timestamp createdAt, Timestamp lastLogin) {
        this();
        this.id = id;
        this.age = age;
        this.names = names;
        this.email = email;
        this.pass = pass;
        this.isTrainer = isTrainer;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }

    /**
     * It is meant to make queries or communicate with the database, not to create new users
     * @return a User object that can be used to query or communicate with the database
     */
    public static User getDefaultUser() {
        return new User(){{
            isDefaultUser = true;
        }};
    }

    @Override
    public User getById(String id) {
        return getModelFromMap(getByIdMap(id));
    }

    @Override
    public List<User> getAll() {
        return Commons.modelMapListToModelList(getAllMapList(), this);
    }

    @Override
    public User getModelFromMap(Map<String, String> modelMap) {
        if (modelMap == null)
            return null;

        Integer id = Commons.getIntOrNull(modelMap.getOrDefault("id", null));
        Integer age = Commons.getIntOrNull(modelMap.getOrDefault("age", null));
        String names = modelMap.getOrDefault("names", null);
        String email = modelMap.getOrDefault("email", null);
        String pass = modelMap.getOrDefault("pass", null);
        Boolean isTrainer = Commons.getBoolOrNull(modelMap.getOrDefault("is_trainer", null));
        Timestamp createdAt = Commons.getTimestampOrNull(modelMap.getOrDefault("created_at", null));
        Timestamp lastLogin = Commons.getTimestampOrNull(modelMap.getOrDefault("last_login", null));

        return new User(id, age, names, email, pass, isTrainer, createdAt, lastLogin);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", isTrainer=" + isTrainer +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
