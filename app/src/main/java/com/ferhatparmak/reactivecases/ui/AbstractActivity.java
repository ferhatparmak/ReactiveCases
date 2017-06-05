package com.ferhatparmak.reactivecases.ui;

import android.support.v7.app.AppCompatActivity;
import com.ferhatparmak.reactivecases.App;
import com.ferhatparmak.reactivecases.ApplicationComponent;
import io.reactivex.Completable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public abstract class AbstractActivity extends AppCompatActivity {
  private BehaviorSubject<Boolean> onResumePauseSubject = BehaviorSubject.create();
  private PublishSubject<Object> onDestroySubject = PublishSubject.create();

  public ApplicationComponent getComponent() {
    App app = (App) getApplication();
    return app.getComponent();
  }

  @Override
  protected void onResume() {
    super.onResume();
    onResumePauseSubject.onNext(true);
  }

  @Override
  protected void onPause() {
    onResumePauseSubject.onNext(false);
    super.onPause();
  }

  public Completable onResumeCompletable() {
    return onResumePauseSubject
        .takeWhile(onResume -> onResume)
        .ignoreElements();
  }

  public Completable onPauseCompletable() {
    return onResumePauseSubject
        .takeWhile(onResume -> !onResume)
        .ignoreElements();
  }

  public Completable onDestroyCompletable() {
    return onDestroySubject.ignoreElements();
  }

  @Override
  protected void onDestroy() {
    onResumePauseSubject.onComplete();
    onDestroySubject.onComplete();
    super.onDestroy();
  }
}
