package com.test.luntan.model;

import javax.persistence.Column;

public class BaseModel {


    @Column(name = "enabled")
    public int enabled;



    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
