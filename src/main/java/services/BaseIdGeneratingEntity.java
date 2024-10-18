package services;

import java.util.UUID;

public abstract class BaseIdGeneratingEntity {

    protected String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
