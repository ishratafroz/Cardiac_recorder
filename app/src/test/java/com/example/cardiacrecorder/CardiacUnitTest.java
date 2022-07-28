package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class CardiacUnitTest {
    private CardiacArrayList mockCardiacList() {
        CardiacArrayList cardiacArrayList= new CardiacArrayList();
        cardiacArrayList.addCardiac(mockCardiac());
        return cardiacArrayList;
    }

    /**
     * taking user input
     * @return
     */
    private cardiaclist mockCardiac(){
        return new cardiaclist
                ("120","80","60","normal","normal","Friday,8 July 2022","05:00 pm" );

    }

    /**
     * testing for add button
     */
    @Test
    public void testAddCardiac(){
        CardiacArrayList cardiacArrayList=mockCardiacList();
        assertEquals(1,cardiacArrayList.getCardiacs().size());

        cardiaclist cardiaclist=new cardiaclist
                ("160","60","50","high","exceptional","Friday,8 July 2022","05:22 pm" );

        cardiacArrayList.addCardiac(cardiaclist);
        assertEquals(2,cardiacArrayList.getCardiacs().size());
        assertTrue(cardiacArrayList.getCardiacs().contains(cardiaclist));
    }

    /**
     * exception handling
     */
    @Test
    public void testAddException(){
        CardiacArrayList cardiacArrayList=new CardiacArrayList();
        cardiaclist cardiaclist=mockCardiac();
        cardiacArrayList.addCardiac(cardiaclist);

        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                cardiacArrayList.addCardiac(cardiaclist);
            }
        });
    }

    /**
     *  checking whether we are getting the list or not
     */
    @Test
    public void testGetCardiacs(){
        CardiacArrayList cardiacArrayList=mockCardiacList();
        assertEquals(0,mockCardiac().compareTo(cardiacArrayList.getCardiacs().get(0)));

        cardiaclist c1= new cardiaclist
                ("190","60","50","high","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c1);

        cardiaclist c2= new cardiaclist
                ("90","60","50","low","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c2);

        assertEquals(0,c1.compareTo(cardiacArrayList.getCardiacs().get(1)));
        assertEquals(0,mockCardiac().compareTo(cardiacArrayList.getCardiacs().get(0)));
        assertEquals(0,c2.compareTo(cardiacArrayList.getCardiacs().get(2)));
    }
    /*
    checking by deletion
     */
    @Test
    public void testdelete(){
        CardiacArrayList cardiacArrayList=new CardiacArrayList();
        cardiaclist c1= new cardiaclist
                ("190","60","50","high","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c1);

        cardiaclist c2= new cardiaclist
                ("90","60","50","low","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c2);

        cardiacArrayList.delete(c1);
        assertTrue(!cardiacArrayList.getCardiacs().contains(c1));
    }

    /**
     *  exception handling by deletion
     */
    @Test
    public void testDeleteException(){
        CardiacArrayList cardiacArrayList=new CardiacArrayList();
        cardiaclist c1= new cardiaclist
                ("190","60","50","high","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c1);

        cardiaclist c2= new cardiaclist
                ("90","60","50","low","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c2);

        cardiacArrayList.delete(c1);
        cardiacArrayList.delete(c2);
        // trying to delete an element which is not in the list
        assertThrows(IllegalArgumentException.class,()->{
            cardiacArrayList.delete(c1);
        });
    }

    /**
     * list items size  checking
     */
    @Test
    public void testcount(){
        CardiacArrayList cardiacArrayList=new CardiacArrayList();
        cardiaclist c1= new cardiaclist
                ("190","60","50","high","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c1);

        cardiaclist c2= new cardiaclist
                ("90","60","50","low","exceptional","Friday,8 July 2022","05:22 pm" );
        cardiacArrayList.addCardiac(c2);

        assertEquals(2,cardiacArrayList.count());
        cardiacArrayList.delete(c1);
        assertEquals(1,cardiacArrayList.count());
        cardiacArrayList.delete(c2);
        assertEquals(0,cardiacArrayList.count());
    }
}