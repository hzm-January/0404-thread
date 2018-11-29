package houzm.game.thread.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.sun.deploy.util.StringUtils;

/**
 * Package: houzm.game.thread.base
 * Author: houzm
 * Date: Created in 2018/6/18 12:39
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class InterrupteDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add(null);
        System.out.println(set);
        List<String> list = new ArrayList<String>();
        for (String s : set) {
            if (s != null) {
                list.add(s);
            }
        }
        System.out.println(list);
    }
}
