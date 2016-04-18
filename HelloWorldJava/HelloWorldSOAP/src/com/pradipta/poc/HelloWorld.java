package com.pradipta.poc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class HelloWorld {
    public HelloWorld() {
        super();
    }

    @WebMethod
    public String HelloHuman(@WebParam(name = "name") String name) {
        return "Hello "+name;
    }  
}
