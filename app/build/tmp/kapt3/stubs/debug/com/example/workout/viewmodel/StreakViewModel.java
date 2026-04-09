package com.example.workout.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bH\u0002R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/workout/viewmodel/StreakViewModel;", "Landroidx/lifecycle/ViewModel;", "databaseRepository", "Lcom/example/workout/data/repository/DataBaseRepository;", "(Lcom/example/workout/data/repository/DataBaseRepository;)V", "currentStreak", "Lkotlinx/coroutines/flow/StateFlow;", "", "getCurrentStreak", "()Lkotlinx/coroutines/flow/StateFlow;", "last7DaysProgress", "", "Lcom/example/workout/viewmodel/DayProgress;", "getLast7DaysProgress", "workoutHistory", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/workout/data/database/CompletedExercise;", "calculateStreak", "history", "app_debug"})
public final class StreakViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.workout.data.repository.DataBaseRepository databaseRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.workout.data.database.CompletedExercise>> workoutHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.workout.viewmodel.DayProgress>> last7DaysProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> currentStreak = null;
    
    public StreakViewModel(@org.jetbrains.annotations.NotNull()
    com.example.workout.data.repository.DataBaseRepository databaseRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.workout.viewmodel.DayProgress>> getLast7DaysProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getCurrentStreak() {
        return null;
    }
    
    private final int calculateStreak(java.util.List<com.example.workout.data.database.CompletedExercise> history) {
        return 0;
    }
}