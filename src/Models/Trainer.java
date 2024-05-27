package Models;

import Commons.Commons;

import java.util.List;
import java.util.Map;

public class Trainer extends Model {
    Integer id;
    String profession;
    Short rating, experience;

    // A default Trainer is used to use the query methods
    boolean isDefaultTrainer = false;

    private Trainer() {
        super("trainers", "id");
    }

    public Trainer(Integer id, String profession, Short rating, Short experience) {
        this();
        this.id = id;
        this.profession = profession;
        this.rating = rating;
        this.experience = experience;
    }

    public static Trainer getDefaultTrainer() {
        return new Trainer(){{
            isDefaultTrainer = true;
        }};
    }

    @Override
    public Trainer getById(String id) {
        return getModelFromMap(getByIdMap(id));
    }

    @Override
    public List<Trainer> getAll() {
        return Commons.modelMapListToModelList(getAllMapList(), this);
    }

    @Override
    public Trainer getModelFromMap(Map<String, String> modelMap) {
        if (modelMap == null)
            return null;

        Integer id = Commons.getIntOrNull(modelMap.getOrDefault("id", null));
        String profession = modelMap.getOrDefault("profession", null);
        Short rating = Commons.getShortOrNull(modelMap.getOrDefault("rating", null));
        Short experience = Commons.getShortOrNull(modelMap.getOrDefault("experience", null));

        return new Trainer(id, profession, rating, experience);
    }

    @Override
    public String toString() {
        User userObj = User.getDefaultUser();
        return "Trainer{" +
                "id=" + id +
                "\n\t { " + userObj.getById(id.toString()) + " }" +
                ",\n profession='" + profession + '\'' +
                ", rating=" + rating +
                ", experience=" + experience +
                '}';
    }
}
