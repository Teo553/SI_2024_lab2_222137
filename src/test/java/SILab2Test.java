import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void evryBranchTest(){
        List<Item> lista=new ArrayList<>();
        //0.1->2->39
        RuntimeException ex;
        ex=assertThrows(RuntimeException.class, () -> SILab2.checkCart(null,0));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));
        //nobracodeException
        lista=new ArrayList<Item>();
        lista.add(new Item(null,null,100,1));
        List<Item> finalLista = lista;
        ex=assertThrows(RuntimeException.class,() -> SILab2.checkCart(finalLista,0));
        assertTrue(ex.getMessage().contains("No barcode!"));
        //Invalid caracter barcode exception
        lista=new ArrayList<Item>();
        lista.add(new Item("item1","12asdsadsdsda",100,1));
        lista.add(new Item("item2","123asda",200,1));
        List<Item> finalLista1 = lista;
        ex=assertThrows(RuntimeException.class,() -> SILab2.checkCart(finalLista1,10));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //33->36->37->39
        lista=new ArrayList<>();
        for(int i=0;i<5;i++){
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(i+1);
            stringBuilder.append(i*1000);
            lista.add(new Item("",stringBuilder.toString(),i+1,0));
        }
        assertEquals(false,SILab2.checkCart(lista,1));
        //33->34->39
        lista=new ArrayList<>();
        for(int i=0;i<5;i++){
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(i+1);
            stringBuilder.append(i*1000);
            lista.add(new Item("",stringBuilder.toString(),i+1,0));
        }
        assertEquals(true,SILab2.checkCart(lista,100));
        //normalna lista test
        lista=new ArrayList<>();
        for(int i=0;i<5;i++){
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(0);
            stringBuilder.append(i+1*1000);
            StringBuilder nov=new StringBuilder();
            nov.append("Name");
            nov.append(i+1);
            lista.add(new Item(nov.toString(),stringBuilder.toString(),i+1*100,0+i));
        }
        assertEquals(false,SILab2.checkCart(lista,100));
    }

    @Test
    void multipleConditionTest(){
        List<Item> lista=new ArrayList<Item>();
        //if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
        //T&&T&&T
        lista.add(new Item("name1","01234",500,1));
        assertEquals(false,SILab2.checkCart(lista,100));
        //F&&X&&X
        lista=new ArrayList<>();
        lista.add(new Item("name1","01234",100,1));
        assertEquals(true,SILab2.checkCart(lista,100));
        //X&&F&&X
        lista=new ArrayList<>();
        lista.add(new Item("name1","01234",100,0));
        assertEquals(true,SILab2.checkCart(lista,100));
        //X&&X&&F
        lista=new ArrayList<>();
        lista.add(new Item("name1","1234",100,1));
        assertEquals(true,SILab2.checkCart(lista,100));


    }
}
