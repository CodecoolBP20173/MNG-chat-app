package com.mng.chat.models;

public class RoomEmit {

    private String target;
    private Object payload;

    public RoomEmit(String target, Object payload) {
        this.target = target;
        this.payload = payload;
    }

    public String getTarget() {
        return target;
    }

    public Object getPayload() {
        return payload;
    }
}
