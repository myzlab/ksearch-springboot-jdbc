package com.myzlab.ksearch;

import com.myzlab.k.KColumn;
import static com.myzlab.k.KFunction.*;
import static com.myzlab.k.SqlDataType.*;
import static com.myzlab.k.SqlFormat.*;
import com.myzlab.k.KInitializer;
import com.myzlab.k.KTable;
import static com.myzlab.k.SqlExtractField.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Ksearch {

    public static void main(String[] args) {
        final KColumn<String> name = new KColumn<>("name");
        final KColumn<String> lastName = new KColumn<>("last_name");
        final KColumn<Long> age = new KColumn<>("age");
        final KColumn<String> data = new KColumn<>("data");
        final KColumn<Long> ageWorking = new KColumn<>("ageWorking");
        
        final KTable author  = new KTable("author");
        final KTable book  = new KTable("book");
        
        new KInitializer()
            .select(
                as(isolate(concat(name, val(" "), lastName)), "FullName"),
                avg(age).cast(bigint()).as("average"),
                avg(val(11)).cast(bigint()).as("average"),
                concat(name, val(" "), val("3 + 2"), lastName).as("Full2Name"),
                count().as("total"),
                val(7).mul(val(2).add(val(3))).sub(val(8).div(val(4).mod(2))).as("values__"),
                sub(mul(val(7), add(3, val(2))), div(val(8), mod(val(4), 2))).as("values_x_"),
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
                random().as("random_"),
                round(age),
                round(val(1.77)),
                sign(age),
                sign(val(1.77)),
                sin(age),
                sin(val(1.77)),
                sinh(age),
                sinh(val(1.77)),
                sqrt(age),
                sqrt(val(1.77)),
                tan(age),
                tan(val(1.77)),
                tanh(age),
                tanh(val(1.77)),
                trunc(age),
                trunc(val(1.77)),
                widthBucket(val(5.22), val(1.77), val(5.22), age),
                radians(val(1.77)),
                radians(age),
                cbrt(age),
                cbrt(val(444)),
                degrees(age),
                degrees(val(444)),
                ceiling(age),
                ceiling(val(5)),
                round(val(951), val(2)),
                round(age, val(2)),
                cast(val("6"), bitVarying(255)).as("bits_"),
                bitNot(age),
                bitNot(val(444)),
                bitNot(7),
                bitShiftLeft(bitShiftRight(bitShiftLeft(bitNot(bitXor(val(5), val(4))), 2), 9), 3).as("ooo_"),
                bitShiftLeft(isolate(bitShiftRight(bitShiftLeft(bitNot(bitXor(val(5), val(4))), 2), 9)), age).as("ooo_"),
                concat(val(1).cast(bit()), val(2).cast(bit(2)), val(1).cast(bit())).as("bitConcat"),
                ascii(age).as("ascii_"),
                ascii(val("2")).as("ascii_"),
                chr(age).as("chr_"),
                chr(val("2")).as("chr_"),
                length(age).as("length_"),
                length(val("2")).as("length_"),
                lower(age).as("lower_"),
                lower(val("2")).as("lower_"),
                upper(age).as("upper_"),
                upper(val("2")).as("upper_"),
                lpad(age, 2, "a").as("lpad_"),
                lpad(val("age"), 2, "a").as("lpad_"),
                lpad(val("age"), 2).as("lpad_"),
                rpad(val("abd"), 2, "a").as("rpad_"),
                rpad(age, 2, "a").as("rpad_"),
                rpad(age, 2).as("rpad_"),
                ltrim(val("abd"), "xyz"),
                ltrim(val("abd")),
                trim(val("abd"), "xyz"),
                trim(val("abd")),
                rtrim(val("abd"), "xyz"),
                rtrim(val("abd")),
                md5(val("abd")),
                md5(age),
                substring(age, 4, 8),
                substring(val("ageageageage"), null, 8),
                substring(val("ageageageage"), 4),
                substring(val("Thomas"), "...$"),
                substring(val("Thomas"), "%#\"o_a#\"_", "#"),
                overlay(val("Txxxxas"), "hom", 2, 4),
                overlay(val("Txxxxas"), "hom", 2),
                overlay(age, "hom", 2),
                position(age, "om"),
                position(val("age"), "om"),
                regexpReplace(val("Thomas"), ".[mN]a.", "M", "g"),
                regexpReplace(age, ".[mN]a.", "M"),
                repeat(age, 2),
                repeat(val("age"), 2),
                replace(val("Thomas"), "a", "b"),
                replace(age, "a", "b"),
                reverse(age).as("reverse_"),
                reverse(val("222")).as("reverse_"),
                splitPart(age, "x", 2).as("splitPart_"),
                splitPart(val("abc~@~def~@~ghi"), "~@~", 2).as("splitPart_"),
                toChar(cast(age, timestamp()), "YYYY"),
                toChar(val(2019), "9,999"),
                toHex(age),
                toHex(val(0)),
                translate(val("12345"), "143", "ax"),
                translate(age, "143", "ax"),
                uuidGenerateV1().as("u1"),
                uuidGenerateV4().as("u4"),
                genRandomUuid().as("g"),
                extract(val("2020-10-10").cast(timestamp()), day()),
                datePart(val("2020-10-10").cast(timestamp()), day()),
                currentDate().as("c"),
                currentTimestamp().as("c22"),
                currentTime().as("tc"),
                currentTimestamp(3).as("ct3"),
                currentTime(4).as("ci4"),
                localTimestamp().as("ll"),
                localTime().as("l"),
                localTimestamp(5).as("lt5"),
                localTime(6).as("li6"),
                cast(val("2020-02-03"), date()).add(3),
                cast(val("2020-02-03"), date()).sub(cast(val("2020-02-04"), date())),
                toDate(age, "YYYYMMDD"),
                toDate(val("20201010"), "YYYYMMDD"),
                toTimestamp(age, "YYYYMMDDHH24MISS"),
                toTimestamp(val("20200203153045"), "YYYYMMDDHH24MISS"),
                dateTrunc(val("2020-10-10").cast(timestamp()), day()),
                rawColumn("CASE WHEN rental_rate = 0.99 THEN 1 ELSE 0 END AS raw_")
            )
            .select(currentSchema().as("sc"))
            .select(currentUser().as("us"))
            .from(author)
            .from(book)
            .where(name.neq("Matia"))
//            .where(name.eq(val("abc")))
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
