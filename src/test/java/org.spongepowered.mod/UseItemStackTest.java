/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.mod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.Hotbar;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.mctester.internal.BaseTest;
import org.spongepowered.mctester.internal.McTester;
import org.spongepowered.mctester.internal.TestUtils;
import org.spongepowered.mctester.junit.MinecraftRunner;

@RunWith(MinecraftRunner.class)
public class UseItemStackTest extends BaseTest {

    public UseItemStackTest(TestUtils testUtils) {
        super(testUtils);
    }

    @Test
    public void testNormalCycle() throws Throwable {

        ItemStack stack = ItemStack.of(ItemTypes.GOLDEN_APPLE, 1);

        this.testUtils.runOnMainThread(() -> {
            McTester.getThePlayer().offer(Keys.GAME_MODE, GameModes.SURVIVAL);

            Hotbar hotbar = McTester.getThePlayer().getInventory().query(QueryOperationTypes.INVENTORY_TYPE.of(Hotbar.class));
            hotbar.set(new SlotIndex(hotbar.getSelectedSlotIndex()), stack);
        });

        this.testUtils.waitForInventoryPropagation();


    }
}
