package com.ferhatparmak.reactivecases;

import android.app.Application;
import com.ferhatparmak.reactivecases.component.ApplicationComponent;
import com.ferhatparmak.reactivecases.component.DaggerApplicationComponent;

public class App extends Application {
  private ApplicationComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
  }

  public ApplicationComponent getComponent() {
    if (component == null) {
      component = DaggerApplicationComponent.create();
    }
    return component;
  }
}
