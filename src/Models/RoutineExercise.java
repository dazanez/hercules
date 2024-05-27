package Models;

import Commons.Commons;

import java.util.List;
import java.util.Map;

public class RoutineExercise extends Model {
    Integer id, exerciseId, routineId;
    Exercise exercise;
    Routine routine;
    String name;
    String performing, precautions;
    Short repetitions, series, rest;

    Boolean isDefaultRoutineExercise = false;

    private RoutineExercise() {
        super("wroutines_exercises", "id");
    }

    public RoutineExercise(Integer id, Integer exerciseId, Integer routineId, String name, String performing, String precautions, Short repetitions, Short series, Short rest) {
        this();
        this.id = id;
        this.exerciseId = exerciseId;
        this.routineId = routineId;
        this.name = name;
        this.performing = performing;
        this.precautions = precautions;
        this.repetitions = repetitions;
        this.series = series;
        this.rest = rest;

        Exercise exerciseObj = Exercise.getDefaultExercise();
        this.exercise = exerciseObj.getById(exerciseId.toString());

        Routine routineObj = Routine.getDefaultRoutine();
        this.routine = routineObj.getById(routineId.toString());
    }

    public static RoutineExercise getDefaultRoutineExercise() {
        return new RoutineExercise(){{
            isDefaultRoutineExercise = true;
        }};
    }

    @Override
    public RoutineExercise getById(String id) {
        return getModelFromMap(getByIdMap(id));
    }

    @Override
    public List<RoutineExercise> getAll() {
        return Commons.modelMapListToModelList(getAllMapList(), this);
    }

    @Override
    public RoutineExercise getModelFromMap(Map<String, String> modelMap) {
        if (modelMap == null)
            return null;

        Integer id = Commons.getIntOrNull(modelMap.getOrDefault("id", null));
        Integer exerciseId = Commons.getIntOrNull(modelMap.getOrDefault("exercise_id", null));
        Integer routineId = Commons.getIntOrNull(modelMap.getOrDefault("wroutine_id", null));
        String name = modelMap.getOrDefault("name", null);
        String performing = modelMap.getOrDefault("performing", null);
        String precautions = modelMap.getOrDefault("precautions", null);
        Short repetitions = Commons.getShortOrNull(modelMap.getOrDefault("repetitions", null));
        Short series = Commons.getShortOrNull(modelMap.getOrDefault("series", null));
        Short rest = Commons.getShortOrNull(modelMap.getOrDefault("rest", null));

        return new RoutineExercise(id, exerciseId, routineId, name, performing, precautions, repetitions, series, rest);
    }

    @Override
    public String toString() {
        return "RoutineExercise{" +
                "id=" + id +
                ", exercise=" + exercise +
                ", routine=" + routine +
                ", name='" + name + '\'' +
                ", performing='" + performing + '\'' +
                ", precautions='" + precautions + '\'' +
                ", repetitions=" + repetitions +
                ", series=" + series +
                ", rest=" + rest +
                ", isDefaultRoutineExercise=" + isDefaultRoutineExercise +
                '}';
    }
}
