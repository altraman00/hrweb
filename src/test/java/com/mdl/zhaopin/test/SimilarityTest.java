package com.mdl.zhaopin.test;

import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.mdl.zhaopin.utils.SimilarityUtil;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.test
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月13日 13:39
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class SimilarityTest {

    public static void main(String[] args) {
        String s1 = "子类可以覆盖父类的成员方法，也可以覆盖父类的成员变量";
        String s2 = "子类不可以覆盖父类的成员方法，也不可以覆盖父类的成员变量";
        System.out.println("\"" + s1 + "\"" + "与" + "\"" + s2 + "的相似度是：" + SimilarityUtil.getSimilarity(s1, s2));

        s1 = "世界你好";
        s2 = "你好世界";
        System.out.println("\"" + s1 + "\"" + "与" + "\"" + s2 + "的相似度是：" + SimilarityUtil.getSimilarity(s1, s2));


    }

}
