import Models.*;

public class Main {
    public static void main(String[] args) {
//        User userObj = User.getDefaultUser();
//        User myUser = userObj.getById("5");
//        System.out.println(myUser);
//        System.out.println(userObj.getAll());
//
//        Exercise exerciseObj = Exercise.getDefaultExercise();
//        Exercise exercise = exerciseObj.getById("1");
//        System.out.println(exercise);
//        System.out.println(exerciseObj.getAll());
//
//        Trainer trainerObj = Trainer.getDefaultTrainer();
//        Trainer trainer = trainerObj.getById("4");
//        System.out.println(trainer);
//        System.out.println(trainerObj.getAll());

        Routine routineObj = Routine.getDefaultRoutine();
        Routine routine = routineObj.getById("1");
        System.out.println(routine);
        System.out.println(routineObj.getAll());

        RoutineExercise routineExerciseObj = RoutineExercise.getDefaultRoutineExercise();
        RoutineExercise routineExercise = routineExerciseObj.getById("10");
        System.out.println(routineExercise);
        System.out.println(routineExerciseObj.getAll());

//        ProgressMonitoring progressMonitoringObj = ProgressMonitoring.getDefaultProgressMonitoring();
//        ProgressMonitoring progressMonitoring = progressMonitoringObj.getById("5");
//        System.out.println(progressMonitoring);
//        System.out.println(progressMonitoringObj.getAll());

        TrainingSession trainingSessionObj = TrainingSession.getDefaultTrainingSession();
        TrainingSession trainingSession = trainingSessionObj.getById("8");
        System.out.println(trainingSession);
        System.out.println(trainingSession.getAll());


    }
}
