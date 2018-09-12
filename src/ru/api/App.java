package ru.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/** Created by rkurbanov on 10.04.2018. */

@ApplicationPath("/api")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(SignAndCryptApi.class);
        h.add(Export.class);
        return h;
    }
}
