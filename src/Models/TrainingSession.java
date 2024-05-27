package Models;

import Commons.Commons;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class TrainingSession extends Model {
    Integer id, userId, routineId;
    User user;
    Routine routine;
    Timestamp dateTime;
    Short rating;

    boolean isDefaultTrainingSession = true;

    public TrainingSession() {
        super("training_sessions", "id");
    }

    public TrainingSession(Integer id, Integer userId, Integer routineId, Timestamp dateTime, Short rating) {
        this();
        this.id = id;
        this.userId = userId;
        this.routineId = routineId;
        this.dateTime = dateTime;
        this.rating = rating;

        User userObj = User.getDefaultUser();
        this.user = userObj.getById(userId.toString());

        Routine routineObj = Routine.getDefaultRoutine();
        System.out.println("routine id: " + routineId);
        System.out.println("training id: " + id);
        this.routine = routineObj.getById(routineId.toString());
    }

    public static TrainingSession getDefaultTrainingSession() {
        return new TrainingSession() {{
            isDefaultTrainingSession = true;
        }};
    }

    @Override
    public TrainingSession getById(String id) {
        return getModelFromMap(getByIdMap(id));
    }

    @Override
    public List<TrainingSession> getAll() {
        return Commons.modelMapListToModelList(getAllMapList(), this);
    }

    @Override
    public TrainingSession getModelFromMap(Map<String, String> modelMap) {
        if (modelMap == null)
            return null;

        Integer id = Commons.getIntOrNull(modelMap.getOrDefault("id", null));
        Integer userId = Commons.getIntOrNull(modelMap.getOrDefault("user_id", null));
        Integer routineId = Commons.getIntOrNull(modelMap.getOrDefault("wroutine_id", null));
        Timestamp dateTime = Commons.getTimestampOrNull(modelMap.getOrDefault("date_time", null));
        Short rating = Commons.getShortOrNull(modelMap.getOrDefault("rating", null));

        return new TrainingSession(id, userId, routineId, dateTime, rating);
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "id=" + id +
                ", user=" + user +
                ", routine=" + routine +
                ", dateTime=" + dateTime +
                ", rating=" + rating +
                '}';
    }
}
