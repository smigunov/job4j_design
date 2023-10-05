package ru.job4j.generics.store;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsFrontendDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Frontend Developer");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsFrontendDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        store.add(new Role("1", "Analyst"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Frontend Developer");
    }

    @Test
    void whenReplaceThenRolenameIsAnalyst() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        store.replace("1", new Role("1", "Analyst"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Analyst");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        store.replace("10", new Role("10", "Analyst"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Frontend Developer");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsFrontendDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Frontend Developer");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        boolean result = store.replace("1", new Role("1", "Analyst"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Frontend Developer"));
        boolean result = store.replace("10", new Role("10", "Analyst"));
        assertThat(result).isFalse();
    }
}