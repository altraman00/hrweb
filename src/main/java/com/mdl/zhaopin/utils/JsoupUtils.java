package com.mdl.zhaopin.utils;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.utils
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月15日 15:42
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class JsoupUtils {

    private static final String EMPTY_STRING = "";

    public static String getText(Document doc, String cssQuery) {
        Elements elems = doc.select(cssQuery);
        if (elems == null || elems.isEmpty()) {
            return EMPTY_STRING;
        } else {
            return trimText(elems.first().text());
        }
    }

    public static String getText(Elements elems, int pos) {
        if (elems == null || elems.size() < pos) {
            return EMPTY_STRING;
        } else {
            return trimText(elems.get(pos).text());
        }
    }

    public static String trimText(String text) {
        return text == null ? EMPTY_STRING : text.trim();
    }

}
