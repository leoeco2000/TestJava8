package effectiveJavaStudy.c34_enum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EnumSetTest {
    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }

    // Any Set could be passed in, but EnumSet is clearly best
    public void applyStyles(Set<Style> styles) {
        // Body goes here
        for (Style style : styles) {
            System.out.println(style);
        }
    }

    // Sample use
    public static void main(String[] args) {
        EnumSetTest text = new EnumSetTest();
        EnumSet eset = EnumSet.of(Style.BOLD, Style.ITALIC);
        
        text.applyStyles(eset);
//        Map<String, String> map = new HashMap<String, String>();
//        for (Entry e : map.entrySet()) {
//            e.getValue();
//            e.getKey();
//        }
    }
}
