package net.gilmor.plate.controller.config;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import net.gilmor.plate.controller.GameController;

@ApplicationPath("/api")
public class AppConfig extends Application {

    private Set<Class<?>> controllers = new HashSet<Class<?>>();
    private Set<Object> singletons = new HashSet<Object>();

    @Override
    public Set<Class<?>> getClasses() {
        controllers.add(GameController.class);
        return controllers;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
