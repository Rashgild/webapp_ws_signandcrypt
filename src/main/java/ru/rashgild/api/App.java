package ru.rashgild.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(SignAndCryptApi.class);
        h.add(Export.class);
        h.add(Import.class);
        h.add(Forward.class);
        return h;
    }
}
