package com.report.util;



public class PageHolder {

    private static final ThreadLocal<PageSearch> HOLDER = new ThreadLocal<>();

    public static PageSearch getHolder() {
        return HOLDER.get();
    }

    public static void setHolder(PageSearch ps) {
        HOLDER.set(ps);
    }

    public static void remove() {
        HOLDER.remove();
    }
}
