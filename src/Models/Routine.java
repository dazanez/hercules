package Models;

import Commons.Commons;

import java.util.List;
import java.util.Map;

// The table name is wroutines
public class Routine extends Model{
    Integer id, trainerId;
    Trainer trainer;
    String name, development, focus, category, objetive;
    Short requirement;
    boolean isDefaultRoutine = false;

    private Routine() {
        super("wroutines", "id");
    }

    public Routine(Integer id, String trainerId, String name, String development, String focus, String category, String objetive, Short requirement) {
        this();
        this.id = id;
        this.trainerId = Commons.getIntOrNull(trainerId);
        this.name = name;
        this.development = development;
        this.focus = focus;
        this.category = category;
        this.objetive = objetive;
        this.requirement = requirement;

        Trainer trainerObj = Trainer.getDefaultTrainer();
        this.trainer = trainerObj.getById(trainerId);
    }

    public static Routine getDefaultRoutine() {
        return new Routine(){{
            isDefaultRoutine = true;
        }};
    }

    @Override
    public Routine getById(String id) {
        return getModelFromMap(getByIdMap(id));
    }

    @Override
    public List<Routine> getAll() {
        return Commons.modelMapListToModelList(getAllMapList(), this);
    }

    @Override
    public Routine getModelFromMap(Map<String, String> modelMap) {
        if (modelMap == null)
            return null;

        Integer id = Commons.getIntOrNull(modelMap.getOrDefault("id", null));
        String trainerId = modelMap.getOrDefault("trainer_id", null);
        String name = modelMap.getOrDefault("name", null);
        String development = modelMap.getOrDefault("development", null);
        String focus = modelMap.getOrDefault("focus", null);
        String category = modelMap.getOrDefault("category", null);
        String objetive = modelMap.getOrDefault("objetive", null);
        Short requirement = Commons.getShortOrNull(modelMap.getOrDefault("requirement", null));

        return new Routine(id, trainerId, name, development, focus, category, objetive, requirement);
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", trainer=" + trainer +
                ", name='" + name + '\'' +
                ", development='" + development + '\'' +
                ", focus='" + focus + '\'' +
                ", category='" + category + '\'' +
                ", objetive='" + objetive + '\'' +
                ", requirement=" + requirement +
                '}';
    }
}
