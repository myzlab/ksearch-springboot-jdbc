package com.myzlab.ksearch;

import com.myzlab.k.KColumn;
import static com.myzlab.k.KFunction.*;
import com.myzlab.k.KTable;
import com.myzlab.k.KValNumberField;
import com.myzlab.k.KValTextField;
import com.myzlab.k.KWindowDefinitionNamed;
import com.myzlab.k.KWindowDefinitionOrdered;
import com.myzlab.k.KWindowDefinitionPartitioned;
import com.myzlab.k.KWindowDefinitionUnnamed;
import static com.myzlab.k.optional.KOptionalHelper.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Ksearch {

    public static void main(String[] args) {
        
        final KTable author  = new KTable("author");
        final KColumn authorId = new KColumn("authorId");
        final KColumn age = new KColumn("age");
        final KColumn name = new KColumn("name");
        
        final KTable book  = new KTable("book");
        final KColumn bookId = new KColumn("bookId");
        final KColumn title = new KColumn("title");
        final KColumn description = new KColumn("description");
        final KColumn pages = new KColumn("pages");
        final KColumn data = new KColumn("data");
        
        final Number nullNumber = null;
        final String nullString = null;
        final KColumn nullKColumn = null;
        final KValTextField nullKValTextField = null;
        final KValNumberField nullKValNumberField = null;
        final List<Long> nullList = null;
        
        final List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        
        final List<Long> emptyIds = new ArrayList<>();
        
        final KWindowDefinitionOrdered wdo1 = wd().name("w1").partitionBy(title).orderBy(age.asc());
        final KWindowDefinitionOrdered wdo2 = wd("w2").partitionBy(description).orderBy(bookId.desc());
        final KWindowDefinitionOrdered wdo3 = wd().name("w3").orderBy(pages.asc());
        final KWindowDefinitionOrdered wdo4 = wd().partitionBy(age).orderBy(data.desc());
        final KWindowDefinitionOrdered wdo5 = wd("w5").orderBy(data.desc());
        final KWindowDefinitionOrdered wdo6 = wd().orderBy(data);
        final KWindowDefinitionNamed wdo7 = wd("w7");
        final KWindowDefinitionUnnamed wdo8 = wd();
        final KWindowDefinitionPartitioned wdo9 = wd("w9").partitionBy(title);
        final KWindowDefinitionPartitioned wdo10 = wd().partitionBy(title);
        
//        System.out.println(wdo1.sb.toString());
//        System.out.println(wdo2.sb.toString());
//        System.out.println(wdo3.sb.toString());
//        System.out.println(wdo4.sb.toString());
        
        new KTest()
            .select(
//                title.over(wdo1),
//                title.over(wdo2),
//                title.over(wdo3),
//                title.over(wdo4),
//                title.over(wdo5),
//                title.over(wdo6),
//                title.over(wdo7),
//                title.over(wdo8),
//                title.over(wdo9),
//                title.over(wdo10)
//                avg(val(881).add(val(3))),
                avg(561)
//                max(pages),
//                max(val(881).add(val(2))),
//                max(561)
//                min(pages),
//                min(val(881).add(val(2))),
//                min(561),
//                sum(pages),
//                sum(val(881).add(val(1))),
//                sum(5612)
//                age.cast(smallint()).as("mini")
//                cast(age, smallint()).as("mini"),
//                avg(age).cast(bigint()).as("average"),
//                cast(avg(age), bigint()).as("average"),
//                val(11).add(15).cast(bigint()).as("eleven_fifteen"),
//                cast(val(11), bigint()).as("eleven"),
//                val("2020-10-01").cast(date()).as("my-date"),
//                cast(val("2020-10-01"), date()).as("my-date")
//                val(55).as("average"),
//                as(avg(pages), "test1"),
//                as(description, "test2"),
//                as(val(77), "test3"),
//                as(val("AOA"), "test4"),
//                as(pages, "test5"),
//                as(7741, "test6"),
//                as("AOA(((I", "test7"),
//                concat(title, val("x"), avg(pages), val(7), val("2020-11-11").cast(date())),
//                    isolate(age),
//                as(isolate(concat(title, val(" "), description)), "FullName")
//                val(99).cast(bigint()).as("average")
//                concat(title, val(" "), val("3 + 2"), description).as("Full2Name")
//                avg(count()).as("total")
//                val(7).mul(val(2).add(val(3))).sub(val(8).div(val(4).mod(2))).as("values__")
//                    add(pages, val(6)),
//                    add(pages, 5),
//                    add(pages, pages)
//                    pages.add(9)
//                    pages.add(val(7).add(pages).add(val(4).add(bookId)).add(1)),
//                    pages.add(pages)
//                    mul(pages, val(6)),
//                    mul(pages, 5),
//                    mul(pages, pages),
//                    pages.mul(9),
//                    pages.mul(val(7).mul(pages).mul(val(4).mul(bookId)).mul(1)),
//                    pages.mul(pages)
//                    div(pages, val(6)),
//                    div(pages, 5),
//                    div(pages, pages),
//                    pages.div(9),
//                    pages.div(val(7).div(pages).div(val(4).div(bookId)).div(1)),
//                    pages.div(pages)
//                    mod(pages, val(6)),
//                    mod(pages, 5),
//                    mod(pages, pages),
//                    pages.mod(9),
//                    pages.mod(val(7).mod(pages).div(val(4).mod(bookId)).mod(1)),
//                    pages.mod(pages)
//                    sub(pages, val(6)),
//                    sub(pages, 5),
//                    sub(pages, pages),
//                    pages.sub(9),
//                    pages.sub(val(7).sub(pages).div(val(4).sub(bookId)).sub(1)),
//                    pages.sub(pages)
//                    bitAnd(pages, val(6)),
//                    bitAnd(pages, 5),
//                    bitAnd(pages, pages),
//                    bitAnd(pages, bitAnd(bitAnd(bitAnd(val(7), pages), bitAnd(val(4), bookId)), 1))
//                    bitOr(pages, val(6)),
//                    bitOr(pages, 5),
//                    bitOr(pages, pages),
//                    bitOr(pages, bitOr(bitOr(bitOr(val(7), pages), bitOr(val(4), bookId)), 1))
//                    bitXor(pages, val(6)),
//                    bitXor(pages, 5),
//                    bitXor(pages, pages),
//                    bitXor(pages, bitXor(bitXor(bitXor(val(7), pages), bitXor(val(4), bookId)), 1))
//                        bitShiftLeft(bookId, pages),
//                    bitShiftLeft(bookId, 7),
//                    bitShiftLeft(bitShiftLeft(val(2), 3), 4),
//                    bitShiftRight(bookId, pages),
//                    bitShiftRight(bookId, 7),
//                    bitShiftRight(bitShiftRight(val(2), 3), 4)
//                sub(mul(val(7), add(3, val(2))), div(val(8), mod(val(4), 2))).as("values_x_")
//                cast(title, varchar(255)).as("nameCasted1"),
//                cast(val(111), varchar(255)).as("nameCasted2"),
//                cast(val("abc"), varchar(255)).as("nameCasted3")
//                title.cast(varchar(255)).as("varchar_255"),
//                title.cast(varchar()).as("varchar_"),
//                title.cast(characterVarying(255)).as("characterVarying_255"),
//                title.cast(characterVarying()).as("characterVarying_"),
//                title.cast(bigint()).as("bigint_"),
//                title.cast(bigserial()).as("bigserial_"),
//                title.cast(bool()).as("bool_"),
//                title.cast(date()).as("date_"),
//                title.cast(character(255)).as("character_255"),
//                title.cast(character()).as("character_"),
//                title.cast(numeric()).as("numeric_"),
//                title.cast(decimal(7)).as("decimal7_"),
//                title.cast(numeric(4, 1)).as("numeric4_1_"),
//                title.cast(decimal(8, 2)).as("decimal8_2_"),
//                title.cast(real()).as("real_"),
//                title.cast(float4()).as("float_4"),
//                title.cast(smallserial()).as("smallserial_"),
//                title.cast(serial2()).as("serial_2"),
//                title.cast(text()).as("text_"),
//                title.cast(timetz()).as("timetz_"),
//                title.cast(timeWithoutTimeZone()).as("timeWithoutTimeZone_"),
//                title.cast(timestampWithTimeZone()).as("timestampWithTimeZone_"),
//                title.cast(timestamp()).as("timestamp_"),
//                title.cast(uuid()).as("uuid_"),
//                title.cast(json()).as("json_"),
//                title.cast(jsonb()).as("jsonb_")
//                age.mul(bookId).avg().isolate().sub(2).as("theaqvgp2")
//                getJsonArray(data.cast(jsonb()), 0).as("getJsonArrayElement"),
//                getJsonArrayAsText(data.cast(jsonb()), 0).as("getJsonArrayElementAsText"),
//                getJsonObject(data.cast(jsonb()), "attr").as("getJsonObjectElement"),
//                getJsonObjectAsText(data.cast(jsonb()), "attr").as("getJsonObjectElementAsText"),
//                getJsonObjectAtPath(data.cast(jsonb()), "a,b,c").as("getJsonObjectAtPath"),
//                getJsonObjectAtPathAsText(data.cast(jsonb()), "a,b,c").as("getJsonObjectAtPathAsText"),
//                data.cast(jsonb()).getJsonArray(0).as("getJsonArray"),
//                data.cast(jsonb()).getJsonArrayAsText(0).as("getJsonArrayAsText"),
//                data.cast(jsonb()).getJsonObject("attr").as("getJsonObject"),
//                data.cast(jsonb()).getJsonObjectAsText("attr").as("getJsonObjectAsText"),
//                data.cast(jsonb()).getJsonObjectAtPath("a,b,c").as("getJsonObjectAtPath"),
//                data.cast(jsonb()).getJsonObjectAtPathAsText("a,b,c").as("getJsonObjectAtPathAsText")
//                coalesce(bookId, val(" "), val(3).add(5), avg(pages.avg().mul(4)).mod(avg(val(1).add(5)))).add(1).as("coal")
//                now().as("now_")
//                encode(encode(encode(val("123\\000456").cast(bytea()), base64()), hex()), escape()),
//                decode(decode(decode(val("123\\000456").cast(bytea()), base64()), hex()), escape())
//                encode(val("1"), escape()),
//                decode(val("2"), hex())
//                nullif(bookId, pages).as("nullif1"),
//                nullif(title, val("-")).as("nullif2"),
//                nullif(pages, val(1).add(val(7))).as("nullif3"),
//                nullif(val(1), pages).as("nullif4"),
//                nullif(val(1), val("A")).as("nullif5"),
//                nullif(val(1), val(3)).as("nullif6")
//                left(title, 2),
//                left(val("ABD "), 5),
//                right(description, 1),
//                right(val("XXR"), 8)
//                round(val(1), val(1)),
//                round(age, age),
//                round(age, 8),
//                round(7, age),
//                round(age, val(9)),
//                round(val(0), age),
//                round(val(0), val(3)),
//                round(5, 8)
//                abs(age),
//                abs(val(11)),
//                abs(12),
//                acos(age),
//                acos(val(33)),
//                acos(35),
//                asin(age),
//                asin(val(1)),
//                asin(77),
//                ascii(age).as("ascii_"),
//                ascii(val("2")).as("ascii_"),
//                ascii("JUJU").as("ascii_")
//                bitNot(age),
//                bitNot(val(444)),
//                bitNot(7)
//                atan(age),
//                atan(val(1)),
//                atan(3),
//                atan2(val(1), val(1)),
//                atan2(age, age),
//                atan2(age, 8),
//                atan2(7, age),
//                atan2(age, val(9)),
//                atan2(val(0), age),
//                atan2(val(0), val(3)),
//                atan2(5, 8)
//                ceil(age),
//                ceil(val(1)),
//                ceil(5),
//                cos(age),
//                cos(val(1)),
//                cos(2),
//                cosh(age),
//                cosh(val(1)),
//                cosh(22),
//                cot(age),
//                cot(val(1)),
//                cot(11),
//                exp(age),
//                exp(val(1)),
//                exp(234),
//                floor(age),
//                floor(val(1)),
//                floor(957)
//                ln(age),
//                ln(val(1)),            
//                ln(2), 
//                sign(age),
//                sign(val(3)),
//                sign(4),
//                sin(age),
//                sin(val(5)),
//                sin(6),
//                sinh(age),
//                sinh(val(7)),
//                sinh(8),
//                sqrt(age),
//                sqrt(val(9)),
//                sqrt(10),
//                tan(age),
//                tan(val(11)),
//                tan(12),
//                tanh(age),
//                tanh(val(13)),
//                tanh(14),
//                trunc(age),
//                trunc(val(15)),
//                trunc(16)
//                cbrt(age),
//                cbrt(val(444)),
//                cbrt(445),
//                degrees(age),
//                degrees(val(446)),
//                degrees(447),
//                ceiling(age),
//                ceiling(val(499)),
//                ceiling(500)
//                cast(val("6"), bitVarying(255)).as("bits_")
//                radians(val(1.77)),
//                radians(age),
//                radians(2),
//                toHex(age),
//                toHex(val(0)),
//                toHex(4),
//                log10(age),
//                log10(val(1)),  
//                log10(12)
//                chr(age),
//                chr(val("2")),
//                chr("ABC"),
//                length(age),
//                length(val("2")),
//                length("p"),
//                lower(age),
//                lower(val("2")),
//                lower("A"),
//                upper(age),
//                upper(val("2")),
//                upper("T"),
//                reverse(age),
//                reverse(val("222")),
//                reverse("re"),
//                md5(val("abd")),
//                md5(age),
//                md5("md")
//                log(val(1), val(1)),
//                log(age, age),
//                log(age, 8),
//                log(7, age),
//                log(age, val(9)),
//                log(val(0), age),
//                log(val(0), val(3)),
//                log(5, 8),
//                power(val(1), val(1)),
//                power(age, age),
//                power(age, 8),
//                power(7, age),
//                power(age, val(9)),
//                power(val(0), age),
//                power(val(0), val(3)),
//                power(5, 8)
//                random().as("random_"),
//                    pi().as("pi_")
//                uuidGenerateV1().as("u1"),
//                uuidGenerateV4().as("u4"),
//                genRandomUuid().as("g")
//                    concat(val(1).cast(bit()), val(2).cast(bit(2)), val(1).cast(bit())).as("bitConcat")
//                currentDate().as("c"),
//                currentTimestamp().as("c22"),
//                currentTime().as("tc"),
//                currentTimestamp(3).as("ct3"),
//                currentTime(4).as("ci4"),
//                localTimestamp().as("ll"),
//                localTime().as("l"),
//                localTimestamp(5).as("lt5"),
//                localTime(6).as("li6")
//            currentSchema().as("sc")
//            currentUser().as("us")
//                ltrim(val("abd"), "xyz"),
//                ltrim(val("abd")),
//                ltrim(title, "xyz"),
//                ltrim(title),
//                trim(val("abd"), "xyz"),
//                trim(val("abd")),
//                trim(title, "xyz"),
//                trim(title),
//                rtrim(val("abd"), "xyz"),
//                rtrim(val("abd")),
//                rtrim(title, "xyz"),
//                rtrim(title)
//                substring(age, 1),    
//                substring(age, 2, 3),
//                substring(age, null, 7),
//                substring(val("ageageageage1"), 4),    
//                substring(val("ageageageage2"), 5, 6),
//                substring(val("ageageageage3"), null, 8),
//                substring(title, "...$"),
//                substring(title, "%#\"o_a#\"_", "#"),
//                substring(val("Thomas"), "...$"),
//                substring(val("Thomas"), "%#\"o_a#\"_", "#")
//                substring("Hola", 1),    
//                substring("Hola2", 2, 3),
//                substring("Hola3", null, 7),
//                substring("Thomas", "...$"),
//                substring("Thomas2", "%#\"o_a#\"_", "#")
//                lpad(age, 1, "a").as("lpad_"),
//                lpad(age, 2, "ab").as("lpad_"),
//                lpad(val("age"), 3, "a").as("lpad_"),
//                lpad(val("age"), 4).as("lpad_"),
//                rpad(age, 5, "a").as("rpad_"),
//                rpad(age, 6, "a").as("rpad_"),
//                rpad(val("age"), 7, "a").as("rpad_"),
//                rpad(val("age"), 8).as("rpad_")
//                overlay(age, "hom1", 1),
//                overlay(age, "abc", 2, 3),
//                overlay(val("Txxxxas1"), "homA", 6),
//                overlay(val("Txxxxas2"), "homB", 4, 5)
//                position(age, "om"),
//                position(val("age"), "om")
//                regexpReplace(val("Thomas1"), ".[mN]a.", "M1"),
//                regexpReplace(val("Thomas2"), ".[mN]b.", "M2", "gx"),
//                regexpReplace(age, ".[mN]c.", "M3"),
//                regexpReplace(age, ".[mN]d.", "M4", "gy")
//                repeat(age, 2),
//                repeat(val("age"), 4)
//                replace(val("Thomas"), "a", "b"),
//                replace(age, "c", "d")
//                toDate(age, "YYYYMMDD"),
//                toDate(val("20201010"), "YYYYMMDD"),
//                toTimestamp(age, "YYYYMMDDHH24MISS"),
//                toTimestamp(val("20200203153045"), "YYYYMMDDHH24MISS")
//                greatest(age, val(5), val("4"), val(1).add(2), avg(bookId.add(7))),
//                least(age, val(51), val("41"), val(11).add(21), avg(bookId.add(71)))
//                    cast(val("2020-02-03"), date()).add(3),
//                cast(val("2020-02-03"), date()).sub(cast(val("2020-02-04"), date()))
//                toChar(cast(age, timestamp()), "YYYY"),
//                toChar(val(2019), "9,999")
//                    widthBucket(val(9).add(age), bookId, age, bookId),
//                    widthBucket(val(10).add(bookId), val(13).add(bookId), val(12).add(age), 7),
//                    widthBucket(age, 1, 2, 3),
//                    widthBucket(4, 5, 6, 7)
//                    bitShiftLeft(bitShiftRight(bitShiftLeft(bitNot(bitXor(val(5), val(4))), 2), 9), 3).as("ooo_"),
//                bitShiftLeft(isolate(bitShiftRight(bitShiftLeft(bitNot(bitXor(val(5), val(4))), 2), 9)), 4).as("ooo_")
//                translate(concat(val(777), val("abc"), age), "143", "ax"),
//                translate(val("jjj"), "143", "ax")
//                    rawColumn("CASE WHEN rental_rate = 0.99 THEN 1 ELSE 0 END AS raw_")
//                extract(val("2020-10-10").cast(timestamp()), day()),
//                datePart(val("2020-10-11").cast(timestamp()), epoch()),
//                dateTrunc(val("2020-10-12").cast(timestamptz()), century())
//                splitPart(age, "x", 2).as("splitPart_"),
//                splitPart(val("abc~@~def~@~ghi"), "~@~", 2).as("splitPart_")
            )
                
                
                
                .where(pages.equal(optional(nullNumber)))
                .and(bookId.equal(optional(nullString)))
                .and(bookId.equal(optional(nullKValTextField)))
                .and(bookId.equal(optional(nullKColumn)))
                .and(val(1).equal(optional(bookId)))
                .and(bookId.equal(optional(nullKValNumberField)))
                
                
                
                
                
//            .from(author)
//            .from(book)
//                .where(avg(val(881).add(1)).eq("1"))
//                .where(pages.add(7).add(bookId.add(99)).eq(val(5).add(1).add(bookId)))
//                .where(pages.mul(7).mul(bookId.mul(99)).eq(val(5).mul(1).mul(bookId)))
//                .where(pages.div(7).div(bookId.div(99)).eq(val(5).div(1).div(bookId)))
//                .where(pages.mod(7).mod(bookId.mod(99)).eq(val(5).mod(1).mod(bookId)))
//                .where(pages.sub(7).sub(bookId.sub(99)).eq(val(5).sub(1).sub(bookId)))
                
//            .where(bitAnd(bitAnd(pages, 7), bitAnd(bookId, 99)).eq(bitAnd(bitAnd(val(5), 1), bookId)))
//                .where(bitOr(bitOr(pages, 7), bitOr(bookId, 99)).eq(bitOr(bitOr(val(5), 1), bookId)))
//                .where(bitXor(bitXor(pages, 7), bitXor(bookId, 99)).eq(bitXor(bitXor(val(5), 1), bookId)))
//                .where(bitShiftRight(bitXor(bitShiftRight(pages, 7), 55), 88).eq(bitShiftRight(bitShiftRight(val(5), 191), 444)))
//                .where(data.cast(jsonb()).getJsonArray(0).cast(bigint()).eq(2))
//                .where(coalesce(bookId, val(" "), val(3).add(5), avg(pages.avg().mul(4)).mod(avg(val(1).add(5)))).eq(2))
//                .where(avg(count()).eq(isolate(concat(title, val("zzz").cast(text())))))
//            .where(cast(";p", int4()).eq(avg(bookId.cast(bigint()))))
//            .where(encode(encode(encode(val("123\\000456").cast(bytea()), base64()), hex()), escape()).eq(1))
//                .where(nullif(pages, val(1).add(val(7))).eq("ABC"))                
//                .where(right(val("XXR"), 8).eq(right(description, 5)))
//                .where(round(age, 7).add(2).eq("A"))
//                .where(acos(acos(age).mod(2)).eq(2))
//                .where(asin(val(1).add(5)).eq(2))
//                .where(bitNot(4).eq(2))
//                .where(atan2(atan(68787), val(2)).eq("A"))
//                .where(md5(age).eq(md5("2")))
//                .where(ltrim(title).eq(val("thetittle")))
//                .where(substring(age, 2, 3).eq("A"))
//                .where(rpad(age, 8).eq("A"))
//                .where(regexpReplace(age, ".[mN]c.", "M3").eq("A"))
//                .where(least(age, val(51), val("41"), val(11).add(21), avg(bookId.add(71))).eq("ABC"))
//                .where(widthBucket(val(9).add(age), bookId, age, bookId).eq("ABC"))
                
//                .where(name.eq("Orwell").or(val("A").eq("B")))
//                .where(val("ALIciA").eq(title))
//                .and(val("MARia").nlkew(concat(val("JuaNita"), val("JuaNota"))))
//                .and(val("MARia").nlkew(val("JuaNA")))
//                .and(val("MARia").nlkew("Holis"))
//                .and(description.nlkew(title))
//                .and(title.nlkew(val("ROmeO")))
//                .and(title.nlkew("Kul"))
                
//                .and(val(70).iLike(pages))
//                .and(val(55).ilt(val(12)))
//                .and(pages.ilt(val(333)))True
//                .and(pages.ilt(123))
                
//                .where(title.isUnknown().and(concat(pages, age, val(" "), val("HHH")).isNotUnknown()))
//                .or(pages.bt("A", "B"))
//                .where(pages.ibt(1, 5))
//                .where(concat(description, age, val("XXX")).notIBetween(bookId, concat(title, description, val("OPA"))))
//                .and(pages.notIBetween(bookId, age))
//                .and(pages.notIBetween("A", "B"))
//                .and(pages.ibt(val(2), val(8)))
//                .and(pages.notIBetween(val("C"), val("D")))
                
//                .and(val("ini").ibt(1, 5))
//                .and(val("INI").notIBetween(bookId, age))
//                .and(val("INI").notIBetween("A", "B"))
//                .and(val("ini").iBetween(val(2), val(8)))
//                .and(val("INI").notIBetween(val("C"), val("D")))
                
//                .and(val(995).ibt(1, 5))
//                .and(val(995).ibt(bookId, age))
//                .and(val(995).ibt("A", "B"))
//                .and(val(995).ibt(val(2), val(8)))
//                .and(val(995).ibt(val("C"), val("D")))
                
                
//                .from(book)
//                .where(pages.eq(Optional.ofNullable(null)).and(pages.eq(Optional.ofNullable(3))))
//                .groupBy(bookId, val(1), concat(title, description))
//                .having(sum(pages).gt(999))
//                .window(wdo1, wdo2, wdo3)
//                .window(wdo5, wdo7)
//                .window(wdo9)
//                .orderBy(val(1), age.asc(), age)
//                .limit(1)
//                .offset(2)
//                .fetch(2)
                
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
