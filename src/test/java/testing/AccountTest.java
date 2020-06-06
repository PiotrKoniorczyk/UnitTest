package testing;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


@Tag("Fries")
class AccountTest {

    @Test
    void newAccountSchouldNotBeActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(), "Check if new account is not active");
        assertThat(newAccount.isActive(), equalTo(false));        //hamcreast
        assertThat(newAccount.isActive(), is(false));             //hamcreast


//        assertThat(newAccount.isActive()).isFalse();                //assertj


    }
    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(),equalTo(true));        //hamcreast

//        assertThat(newAccount.isActive()).isTrue();               //assertj
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAdressSet(){
        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());                               //hamcreast

//        assertThat(address).isNull();                                     //assertj
    }

    @Test
    void defaultDeliveryShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Krakowska","67c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);

        //assertThat()
        assertThat(defaultAddress, is(notNullValue()));                 //hamcreast

//        assertThat(defaultAddress).isNotNull();                           //assertj

    }


    @RepeatedTest(5)
    void newAccountWithNoNullAddressShouldBeActive(){

        //given
        Address address = new Address("Puławska", "46/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, ()->{
            assertTrue(account.isActive());
        });
    }



}