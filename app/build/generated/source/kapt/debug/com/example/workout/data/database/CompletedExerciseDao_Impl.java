package com.example.workout.data.database;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class CompletedExerciseDao_Impl implements CompletedExerciseDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<CompletedExercise> __insertAdapterOfCompletedExercise;

  public CompletedExerciseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfCompletedExercise = new EntityInsertAdapter<CompletedExercise>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workout_history` (`date`,`isCompleted`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final CompletedExercise entity) {
        if (entity.getDate() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getDate());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(2, _tmp);
      }
    };
  }

  @Override
  public Object insertOrUpdateWorkout(final CompletedExercise completedExercise,
      final Continuation<? super Unit> $completion) {
    if (completedExercise == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfCompletedExercise.insert(_connection, completedExercise);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getWorkoutByDate(final String date,
      final Continuation<? super CompletedExercise> $completion) {
    final String _sql = "SELECT * FROM workout_history WHERE date = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (date == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, date);
        }
        final int _cursorIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _cursorIndexOfIsCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCompleted");
        final CompletedExercise _result;
        if (_stmt.step()) {
          final String _tmpDate;
          if (_stmt.isNull(_cursorIndexOfDate)) {
            _tmpDate = null;
          } else {
            _tmpDate = _stmt.getText(_cursorIndexOfDate);
          }
          final boolean _tmpIsCompleted;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsCompleted));
          _tmpIsCompleted = _tmp != 0;
          _result = new CompletedExercise(_tmpDate,_tmpIsCompleted);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Flow<List<CompletedExercise>> getAllWorkouts() {
    final String _sql = "SELECT * FROM workout_history";
    return FlowUtil.createFlow(__db, false, new String[] {"workout_history"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _cursorIndexOfIsCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCompleted");
        final List<CompletedExercise> _result = new ArrayList<CompletedExercise>();
        while (_stmt.step()) {
          final CompletedExercise _item;
          final String _tmpDate;
          if (_stmt.isNull(_cursorIndexOfDate)) {
            _tmpDate = null;
          } else {
            _tmpDate = _stmt.getText(_cursorIndexOfDate);
          }
          final boolean _tmpIsCompleted;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsCompleted));
          _tmpIsCompleted = _tmp != 0;
          _item = new CompletedExercise(_tmpDate,_tmpIsCompleted);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
