package com.myzlab.ksearch;

import com.myzlab.k.KField;
import static com.myzlab.k.KFunction.*;
import com.myzlab.k.KInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Ksearch {

    public static void main(String[] args) {
        final KField<String> name = new KField<>("name");
        final KField<String> lastName = new KField<>("last_name");
        final KField<Long> age = new KField<>("age");
        
        new KInitializer()
            .select(
                as(isolate(concat(name, inline(" "), lastName)), "FullName"),
                avg(age).as("average"),
                concat(name, inline(" "), lastName).as("Full2Name"),
                count().as("total"),
                isolate(inline(1).multiply(inline(2))).plus(inline(3)).minus(isolate(inline(8).divide(4))).as("inlines")
            )
            .single();
//            .select(new KColumn().as())
//            .from()
//            .where()
//            .and()
//            .and()
//            .groupBy()
//            .limit()
//            .offset()
//            .fetch();
        
    }
    
    public ResponseEntity toResponse() {
        return toResponse(HttpStatus.OK);
    }
    
    public ResponseEntity toResponse(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body("");
    }
}
