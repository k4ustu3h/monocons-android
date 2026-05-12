/*
 * Copyright 2026 Lawnchair Launcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.lawnchair.lawnicons.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PreferenceManagerTest {

    private class TestPreferenceManager(
        store: PreferenceStore,
    ) : BasePreferenceManager(store) {
        val boolPref = BoolPref("bool_key", false)
        val intPref = IntPref("int_key", 7)
    }

    @Test
    fun `bool pref returns default before writes`() {
        val manager = TestPreferenceManager(InMemoryPreferenceStore())

        assertFalse(manager.boolPref.get())
    }

    @Test
    fun `bool pref set persists value`() {
        val manager = TestPreferenceManager(InMemoryPreferenceStore())

        manager.boolPref.set(true)

        assertTrue(manager.boolPref.get())
    }

    @Test
    fun `bool pref toggle flips current value`() {
        val manager = TestPreferenceManager(InMemoryPreferenceStore())

        manager.boolPref.toggle()
        assertTrue(manager.boolPref.get())

        manager.boolPref.toggle()
        assertFalse(manager.boolPref.get())
    }

    @Test
    fun `int pref returns default before writes`() {
        val manager = TestPreferenceManager(InMemoryPreferenceStore())

        assertEquals(7, manager.intPref.get())
    }

    @Test
    fun `int pref set persists value`() {
        val manager = TestPreferenceManager(InMemoryPreferenceStore())

        manager.intPref.set(42)

        assertEquals(42, manager.intPref.get())
    }

    @Test
    fun `prefs keep values isolated by key`() {
        val manager = TestPreferenceManager(InMemoryPreferenceStore())

        manager.boolPref.set(true)
        manager.intPref.set(99)

        assertTrue(manager.boolPref.get())
        assertEquals(99, manager.intPref.get())
    }

    @Test
    fun `in memory store notifies listeners for changed key`() {
        val store = InMemoryPreferenceStore()
        val changedKeys = mutableListOf<String>()
        val listener = PreferenceChangeListener { key -> changedKeys += key }

        store.registerListener(listener)
        store.putBoolean("flag", true)
        store.putInt("count", 3)
        store.unregisterListener(listener)
        store.putBoolean("ignored", false)

        assertEquals(listOf("flag", "count"), changedKeys)
    }

    @Test
    fun `preference manager force icon request defaults false and can be enabled`() {
        val manager = PreferenceManager(InMemoryPreferenceStore())

        assertFalse(manager.forceEnableIconRequest.get())
        manager.forceEnableIconRequest.set(true)

        assertTrue(manager.forceEnableIconRequest.get())
    }
}
