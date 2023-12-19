package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Receipt {
    public int id;
    public int amount;
    public String description;
    public Boolean active;
 public String toString(){
     Gson gson=new Gson();
     return gson.toJson(this);

 }
}


    


