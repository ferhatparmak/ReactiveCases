package com.ferhatparmak.reactivecases.component;

import com.ferhatparmak.reactivecases.main.MainModule;
import com.ferhatparmak.reactivecases.util.AndroidModule;
import dagger.Component;

@Component(modules = { AndroidModule.class, MainModule.class })
public interface ApplicationComponent {

}
