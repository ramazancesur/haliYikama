package hali.pro.com.haliyikama.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ramazancesur on 11/08/2017.
 */

public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {

    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Date deserialize(JsonParser paramJsonParser,
                            DeserializationContext paramDeserializationContext)
            throws IOException {

        String str = paramJsonParser.getText().trim();
        try {
            Date date = oldFormat.parse(str);
            oldFormat.applyPattern("dd/MM/yyyy");
            return newFormat.parse(oldFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paramDeserializationContext.parseDate(str);
    }

}