package tn.esprit.springproject.Service;

import java.util.List;

public interface Iservice <object>{

    object Create(object T);
    List<object> Read();
    object Update(int ID,object T);
    String  Delete(int ID );

    object getOne(Integer ID);


}
