package com.example.workout.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u0016\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u00a8\u0006\u0010"}, d2 = {"DayCircle", "", "day", "", "completed", "", "StreakCounter", "streakCount", "", "WeeklyProgress", "progressList", "", "Lcom/example/workout/viewmodel/DayProgress;", "streakScreen", "navController", "Landroidx/navigation/NavHostController;", "app_debug"})
public final class StreakScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void streakScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StreakCounter(int streakCount) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WeeklyProgress(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.workout.viewmodel.DayProgress> progressList) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DayCircle(@org.jetbrains.annotations.NotNull()
    java.lang.String day, boolean completed) {
    }
}