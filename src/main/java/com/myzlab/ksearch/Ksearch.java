package com.myzlab.ksearch;

import com.myzlab.k.KField;
import static com.myzlab.k.KFunction.*;
import static com.myzlab.k.SqlDataType.*;
import static com.myzlab.k.SqlFormat.*;
import com.myzlab.k.KInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Ksearch {

    public static void main(String[] args) {
        final KField<String> name = new KField<>("name");
        final KField<String> lastName = new KField<>("last_name");
        final KField<Long> age = new KField<>("age");
        final KField<String> data = new KField<>("data");
        final KField<Long> ageWorking = new KField<>("ageWorking");
        
        new KInitializer()
            .select(
                as(isolate(concat(name, val(" "), lastName)), "FullName"),
                avg(age).cast(bigint()).as("average"),
                avg(val(11)).cast(bigint()).as("average"),
                concat(name, val(" "), val("3 + 2"), lastName).as("Full2Name"),
                count().as("total"),
                val(7).mul(val(2).add(val(3))).sub(val(8).div(val(4).mod(2))).as("values__"),
                cast(name, varchar(255)).as("nameCasted"),
                cast(val(111), varchar(255)).as("nameCasted"),
                cast(val("abc"), varchar(255)).as("nameCasted"),
                name.cast(varchar(255)).as("varchar_255"),
                name.cast(varchar()).as("varchar_"),
                name.cast(characterVarying(255)).as("characterVarying_255"),
                name.cast(characterVarying()).as("characterVarying_"),
                name.cast(bigint()).as("bigint_"),
                name.cast(bigserial()).as("bigserial_"),
                name.cast(bool()).as("bool_"),
                name.cast(date()).as("date_"),
                name.cast(character(255)).as("character_255"),
                name.cast(character()).as("character_"),
                name.cast(numeric()).as("numeric_"),
                name.cast(decimal(7)).as("decimal7_"),
                name.cast(numeric(4, 1)).as("numeric4_1_"),
                name.cast(decimal(8, 2)).as("decimal8_2_"),
                name.cast(real()).as("real_"),
                name.cast(float4()).as("float_4"),
                name.cast(smallserial()).as("smallserial_"),
                name.cast(serial2()).as("serial_2"),
                name.cast(text()).as("text_"),
                name.cast(timetz()).as("timetz_"),
                name.cast(timeWithoutTimeZone()).as("timeWithoutTimeZone_"),
                name.cast(timestampWithTimeZone()).as("timestampWithTimeZone_"),
                name.cast(timestamp()).as("timestamp_"),
                name.cast(uuid()).as("uuid_"),
                name.cast(json()).as("json_"),
                name.cast(jsonb()).as("jsonb_"),
                age.mul(ageWorking).avg().isolate().sub(2).as("theaqvgp2"),
                getJsonArray(data.cast(jsonb()), 0).as("getJsonArrayElement"),
                getJsonArrayAsText(data.cast(jsonb()), 0).as("getJsonArrayElementAsText"),
                getJsonObject(data.cast(jsonb()), "attr").as("getJsonObjectElement"),
                getJsonObjectAsText(data.cast(jsonb()), "attr").as("getJsonObjectElementAsText"),
                data.cast(jsonb()).getJsonArray(0).as("getJsonArray"),
                data.cast(jsonb()).getJsonArrayAsText(0).as("getJsonArrayAsText"),
                data.cast(jsonb()).getJsonObject("attr").as("getJsonObject"),
                data.cast(jsonb()).getJsonObjectAsText("attr").as("getJsonObjectAsText"),
                data.cast(jsonb()).getJsonObjectAtPath("a,b,c").as("getJsonObjectAtPath"),
                data.cast(jsonb()).getJsonObjectAtPathAsText("a,b,c").as("getJsonObjectAtPathAsText"),
                coalesce(name, val(" "), val(3).add(5), lastName).as("coal"),
                now().as("now_"),
                encode(encode(encode(val("'123\\000456'").cast(bytea()), base64()), hex()), escape()),
                decode(decode(decode(val("'123\\000456'").cast(bytea()), base64()), hex()), escape()),
                encode(val("1"), escape()),
                decode(val("1"), hex()),
                nullif(name, lastName).as("nullif1"),
                nullif(name, val("")).as("nullif2"),
                nullif(val(1), lastName).as("nullif3"),
                nullif(val(1), val("1")).as("nullif4"),
                left(name, 2),
                left(val("11"), 5),
                right(name, 1),
                right(val("11"), 8),
                abs(age),
                abs(val(11)),
                acos(age),
                acos(val(33)),
                asin(age),
                asin(val(1)),
                atan(age),
                atan(val(1)),
                atan2(val(1), val(1)),
                atan2(age, age),
                ceil(age),
                ceil(val(1)),
                cos(age),
                cos(val(1)),
                cosh(age),
                cosh(val(1)),
                cot(age),
                cot(val(1)),
                exp(age),
                exp(val(1)),
                floor(age),
                floor(val(1)),
                greatest(age, val(1), val("1")),
                least(age, val(1), val("1")),
                ln(age),
                ln(val(1)),
                atan2(val(1), val(1)),
                log(age, age),
                log(val(1), val(1)),
                log10(age),
                log10(val(1)),
                pi().as("pi_"),
                power(age, age),
                power(val(1), val(1)),
                random().as("random_")
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
