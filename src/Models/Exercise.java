package Models;

import Commons.Commons;

import java.util.List;
import java.util.Map;

public class Exercise extends Model {
    Integer id;
    String name, description, type;
    String musclesWorked;
    boolean isDefaultExercise = false;

    /**
     * Sets the name of the table and its primary key column
     */
    private Exercise() {
        super("exercises", "id");
    }

    public Exercise(Integer id, String name, String description, String type, String musclesWorked) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.musclesWorked = musclesWorked;
    }

    public static Exercise getDefaultExercise() {
        return new Exercise() {{
            isDefaultExercise = true;
        }};
    }

    @Override
    public Exercise getById(String id) {
        return getModelFromMap(getByIdMap(id));
    }

    @Override
    public List<Exercise> getAll() {
        return Commons.modelMapListToModelList(getAllMapList(), this);
    }

    @Override
    public Exercise getModelFromMap(Map<String, String> modelMap) {
        if (modelMap == null)
            return null;

        Integer id = Commons.getIntOrNull(modelMap.getOrDefault("id", null));
        String name = modelMap.getOrDefault("name", null);
        String description = modelMap.getOrDefault("description", null);
        String type = modelMap.getOrDefault("type", null);
        String musclesWorked = modelMap.getOrDefault("muscles_worked", null);

        return new Exercise(id, name, description, type, musclesWorked);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", musclesWorked='" + musclesWorked + '\'' +
                '}';
    }
}
