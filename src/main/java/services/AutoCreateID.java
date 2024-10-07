package services;

import java.util.UUID;

public abstract class AutoCreateID {

    protected String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}
