package com.github.zipcodewilmington.casino;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class CasinoAccountManagerTest {

    @Test
    void getAccount() {
        // Given
        CasinoAccountManager testManager = new CasinoAccountManager();
        CasinoAccount testAccount;
        String accountName = "test";
        String accountPassword = "test123";

        // When
        testAccount = new CasinoAccount(accountName, accountPassword);
        testManager.registerAccount(testAccount);
        CasinoAccount expected = testAccount;
        CasinoAccount actual = testManager.getAccount(accountName,accountPassword);
        // Then
        assertEquals(expected,actual);
    }
    @Test
    void getAccountForUnregisteredAccount() {
        // Given
        CasinoAccountManager testManager = new CasinoAccountManager();
        CasinoAccount testAccount;
        String accountName = "test";
        String accountPassword = "test123";

        // When
        testAccount = new CasinoAccount(accountName, accountPassword);
        CasinoAccount expected = null;
        CasinoAccount actual = testManager.getAccount(accountName,accountPassword);
        // Then
        assertEquals(expected,actual);
    }
//    @Test
//    void getAccountForErroneousPassword() {
//        // Given
//        CasinoAccountManager testManager = new CasinoAccountManager();
//        CasinoAccount testAccount;
//        String accountName = "test";
//        String accountPassword = "test123";
//
//        // When
//        testAccount = new CasinoAccount(accountName, accountPassword);
//        testManager.registerAccount(testAccount);
//        CasinoAccount expected = testAccount;
//        CasinoAccount actual = testManager.getAccount();
//        // Then
//        assertEquals(expected,actual);
//    }

//    @Test
//    void createAccount() {
//    }
//
//    @Test
//    void registerAccount() {
//    }
//
//    @Test
//    void getCasinoAccount() {
//    }

    @Test
    void getAccountByUsername() {
        // Given
        CasinoAccountManager testManager = new CasinoAccountManager();
        CasinoAccount testAccount;
        String accountName = "test";
        String accountPassword = "test123";

        // When
        testAccount = new CasinoAccount(accountName, accountPassword);
        testManager.registerAccount(testAccount);
        CasinoAccount expected = testAccount;
        CasinoAccount actual = testManager.getAccountByUsername(testAccount.getAccountName());
        // Then
        assertEquals(expected,actual);
    }
}