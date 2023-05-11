package org.example.model;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.List;

public abstract class Savable {

    public void save() throws Exception {
        DataBase.getInstance().save(this);
    }

    public void validate() throws Exception {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(this);
        if (violations.size() > 0) throw new Exception("validation");
    }
}
