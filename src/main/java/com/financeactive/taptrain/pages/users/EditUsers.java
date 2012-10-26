package com.financeactive.taptrain.pages.users;

import com.financeactive.taptrain.components.EditUser;
import com.financeactive.taptrain.entities.User;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

/**
 * @author olemerdy
 * @since 25/10/12
 */
public class EditUsers {

    @InjectComponent
    EditUser editUser;

    @PageActivationContext
    @Property
    User user;
}