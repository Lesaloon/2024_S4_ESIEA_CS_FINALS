package edu.esiea.finals.tutoapi.services;

import java.util.List;

public class apiResponseShema<T> {
    public boolean success;
    public String message;
    public List<T> payload;
    public Object errors;
}
