package org.bukkit;

import static org.bukkit.support.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.bukkit.material.Colorable;
import org.bukkit.material.Dye;
import org.bukkit.material.Wool;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class DyeColorTest {

    @ParameterizedTest
    @EnumSource(DyeColor.class)
    public void getByData(DyeColor dye) {
        byte data = dye.getWoolData();

        DyeColor byData = DyeColor.getByWoolData(data);
        assertThat(byData, is(dye));
    }

    @ParameterizedTest
    @EnumSource(DyeColor.class)
    public void getByWoolData(DyeColor dye) {
        byte data = dye.getWoolData();

        DyeColor byData = DyeColor.getByWoolData(data);
        assertThat(byData, is(dye));
    }
}
