package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleTests
{
    @Test
    @Tag("noBrowser")
    void checkTrueIsTrue()
    {
        assertThat(true, is(true));
    }

    @Test
    @Tag("noBrowser")
    void negativeCheckTrueIsFalse()
    {
        assertThat(true, is(false));
    }
}
