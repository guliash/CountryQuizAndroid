package com.guliash.countryquiz.utils;

import junit.framework.Assert;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class IOUtilsTests {

    @Test
    public void readString_empty_returnsEmptyString() {
        try {
            InputStream stream = new ByteArrayInputStream("".getBytes("UTF-8"));
            String result = IOUtils.readString(stream);
            Assert.assertEquals("", result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void readString_nullStream_throwsNullPointerException() {
        IOUtils.readString(null);
    }

    @Test
    public void readString_notEmpty_returnsEmptyString() {
        try {
            String str = "babah";
            InputStream stream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            String result = IOUtils.readString(stream);
            Assert.assertEquals(str, result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
