package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    private String valueToParse;

    String delimeter = "[:|@|\\^|\\%|\\*|;]";

    String pattern = "naMe" + delimeter + "(\\w+)" + delimeter +
            "price" + delimeter + "(\\d+.?\\d*)" + delimeter +
            "type" + delimeter + "(\\w+)" + delimeter +
            "expiration" + delimeter + "(\\d{1,2}/\\d{1,2}/\\d{2,4})##";

    //    public List<Item> parseItemList(String valueToParse) {
//        ArrayList<Item> items = new ArrayList<>();
//        Pattern pattern1 = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern1.matcher(valueToParse);
//
//        String[] splitted = valueToParse.split("##");
//        int i = 0;
//        Integer len = splitted.length;
//
//        while (matcher.find() && i<len) {
//            try {
//                if(parseSingleItem(splitted[i] + "##")!= null) {
//                    items.add(parseSingleItem(splitted[i] + "##")); }
//                i++;
//            } catch (ItemParseException e) { //i++;
//                e.printStackTrace();
//            }
//        }
//        return items;
//    }
    public List<Item> parseItemList(String valueToParse) {
        ArrayList<Item> items = new ArrayList<>();

        String[] splitted = valueToParse.split("##");
        int i = 0;
        Integer len = splitted.length;

        while (i < len) {
            Item item = null;
            try {
                item = parseSingleItem(splitted[i] + "##");
                items.add(item);
            } catch (ItemParseException e) {
                e.printStackTrace();
            }
            i++;
        }
        return items;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        Item item = null;
        try {
            Double price1;
            Pattern pattern1 = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern1.matcher(singleItem);
            matcher.find();

            String name = matcher.group(1).toLowerCase();
            String price = matcher.group(2);
            String type = matcher.group(3).toLowerCase();
            String year = matcher.group(4);


            price1 = Double.parseDouble(price);

            item = new Item(name, price1, type, year);

        } catch (Exception e) {
            throw new ItemParseException();
        }
        return item;

    }


}
