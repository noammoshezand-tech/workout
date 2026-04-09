package com.example.workout.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u00109\u001a\u00020:H\u0002J\b\u0010;\u001a\u00020:H\u0002J\u0016\u0010<\u001a\u00020:2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>J\u0006\u0010@\u001a\u00020:J\u0006\u0010A\u001a\u00020:J\u0006\u0010B\u001a\u00020:J\u0006\u0010C\u001a\u00020:J\u0006\u0010D\u001a\u00020:R\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR+\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R+\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u00168F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0 0\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\"\u001a\u00020!2\u0006\u0010\r\u001a\u00020!8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b&\u0010\u001d\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\'\u001a\u00020\u000eX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0011R+\u0010)\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b,\u0010\u0015\u001a\u0004\b*\u0010\u0011\"\u0004\b+\u0010\u0013R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\n0 8F\u00a2\u0006\u0006\u001a\u0004\b0\u00101R/\u00103\u001a\u0004\u0018\u0001022\b\u0010\r\u001a\u0004\u0018\u0001028F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b8\u0010\u001d\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lcom/example/workout/viewmodel/WorkoutViewModel;", "Landroidx/lifecycle/ViewModel;", "weatherRepository", "Lcom/example/workout/data/repository/WeatherRepository;", "locationRepository", "Lcom/example/workout/data/repository/LocationRepository;", "databaseRepository", "Lcom/example/workout/data/repository/DataBaseRepository;", "(Lcom/example/workout/data/repository/WeatherRepository;Lcom/example/workout/data/repository/LocationRepository;Lcom/example/workout/data/repository/DataBaseRepository;)V", "currentExercise", "Lcom/example/workout/data/model/Exercize;", "getCurrentExercise", "()Lcom/example/workout/data/model/Exercize;", "<set-?>", "", "currentExerciseIndex", "getCurrentExerciseIndex", "()I", "setCurrentExerciseIndex", "(I)V", "currentExerciseIndex$delegate", "Landroidx/compose/runtime/MutableIntState;", "Lcom/example/workout/viewmodel/WorkoutPhase;", "currentPhase", "getCurrentPhase", "()Lcom/example/workout/viewmodel/WorkoutPhase;", "setCurrentPhase", "(Lcom/example/workout/viewmodel/WorkoutPhase;)V", "currentPhase$delegate", "Landroidx/compose/runtime/MutableState;", "exercisesByDay", "", "", "", "isRunning", "()Z", "setRunning", "(Z)V", "isRunning$delegate", "restDuration", "getRestDuration", "timeLeft", "getTimeLeft", "setTimeLeft", "timeLeft$delegate", "timerJob", "Lkotlinx/coroutines/Job;", "todayExercises", "getTodayExercises", "()Ljava/util/List;", "Lcom/example/workout/data/model/WeatherResponse;", "weatherInfo", "getWeatherInfo", "()Lcom/example/workout/data/model/WeatherResponse;", "setWeatherInfo", "(Lcom/example/workout/data/model/WeatherResponse;)V", "weatherInfo$delegate", "checkIfAlreadyFinishedToday", "", "handlePhaseTransition", "loadWeather", "lat", "", "lon", "loadWeatherForCurrentLocation", "markAsFinished", "resetToOverview", "startWorkout", "stopWorkout", "app_debug"})
public final class WorkoutViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.workout.data.repository.WeatherRepository weatherRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.workout.data.repository.LocationRepository locationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.workout.data.repository.DataBaseRepository databaseRepository = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState weatherInfo$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableIntState timeLeft$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isRunning$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState currentPhase$delegate = null;
    private final int restDuration = 1;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Integer, java.util.List<com.example.workout.data.model.Exercize>> exercisesByDay = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableIntState currentExerciseIndex$delegate = null;
    
    public WorkoutViewModel(@org.jetbrains.annotations.NotNull()
    com.example.workout.data.repository.WeatherRepository weatherRepository, @org.jetbrains.annotations.NotNull()
    com.example.workout.data.repository.LocationRepository locationRepository, @org.jetbrains.annotations.NotNull()
    com.example.workout.data.repository.DataBaseRepository databaseRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.workout.data.model.WeatherResponse getWeatherInfo() {
        return null;
    }
    
    private final void setWeatherInfo(com.example.workout.data.model.WeatherResponse p0) {
    }
    
    public final int getTimeLeft() {
        return 0;
    }
    
    private final void setTimeLeft(int p0) {
    }
    
    public final boolean isRunning() {
        return false;
    }
    
    private final void setRunning(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.workout.viewmodel.WorkoutPhase getCurrentPhase() {
        return null;
    }
    
    private final void setCurrentPhase(com.example.workout.viewmodel.WorkoutPhase p0) {
    }
    
    public final int getRestDuration() {
        return 0;
    }
    
    public final int getCurrentExerciseIndex() {
        return 0;
    }
    
    private final void setCurrentExerciseIndex(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.workout.data.model.Exercize> getTodayExercises() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.workout.data.model.Exercize getCurrentExercise() {
        return null;
    }
    
    private final void checkIfAlreadyFinishedToday() {
    }
    
    public final void startWorkout() {
    }
    
    private final void handlePhaseTransition() {
    }
    
    public final void stopWorkout() {
    }
    
    public final void resetToOverview() {
    }
    
    public final void loadWeather(double lat, double lon) {
    }
    
    public final void loadWeatherForCurrentLocation() {
    }
    
    public final void markAsFinished() {
    }
}