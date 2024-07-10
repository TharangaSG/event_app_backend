package com.tharanga.event_app.mappers;

public interface Mapper <A, B>{

    B mapTo(A a);

    A mapFrom(B b);
}
