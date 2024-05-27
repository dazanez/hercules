package Models;

import Commons.Commons;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class ProgressMonitoring extends Model{
    Integer id;
    String userId;
    User user;
    Date progressDate;
    Float weigh, height, circumference;

    boolean isDefaultMonitoring = false;

    public ProgressMonitoring() {
        super("progress_monitorings", "id");
    }

    public ProgressMonitoring(Integer id, String userId, Date progressDate, Float weigh, Float height, Float circumference) {
        this();
        this.id = id;
        this.userId = userId;
        this.progressDate = progressDate;
        this.weigh = weigh;
        this.height = height;
        this.circumference = circumference;

        User userObj = User.getDefaultUser();
        this.user = userObj.getById(userId);
    }

    public static ProgressMonitoring getDefaultProgressMonitoring() {
        return new ProgressMonitoring() {{
            isDefaultMonitoring = true;
        }};
    }

    @Override
    public ProgressMonitoring getById(String id) {
        return getModelFromMap(getByIdMap(id));
    }

    @Override
    public List<ProgressMonitoring> getAll() {
        return Commons.modelMapListToModelList(getAllMapList(), this);
    }

    @Override
    public ProgressMonitoring getModelFromMap(Map<String, String> modelMap) {
        if (modelMap == null)
            return null;

        Integer id = Commons.getIntOrNull(modelMap.getOrDefault("id", null));
        String userId = modelMap.getOrDefault("user_id", null);
        Date progressDate = Commons.getDateOrNull(modelMap.getOrDefault("progress_date", null));
        Float weigh = Commons.getFloatOrNull(modelMap.getOrDefault("weigh", null));
        Float height = Commons.getFloatOrNull(modelMap.getOrDefault("height", null));
        Float circumference = Commons.getFloatOrNull(modelMap.getOrDefault("circumference", null));

        return new ProgressMonitoring(id, userId, progressDate, weigh, height, circumference);
    }

    @Override
    public String toString() {
        return "ProgressMonitoring{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", user=" + user +
                ", progressDate=" + progressDate +
                ", weigh=" + weigh +
                ", height=" + height +
                ", circumference=" + circumference +
                '}';
    }
}
