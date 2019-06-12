/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author umangjain
 */
@FacesValidator("emailvalidation")
public class usernamevalidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
          String username = value.toString();
          boolean check;
          check = username.equals("");
          if(check == true){
              throw new ValidatorException(new FacesMessage("Email is invalid!"));
          }
    } 
}

