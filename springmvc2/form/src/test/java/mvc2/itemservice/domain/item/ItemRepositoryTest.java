package mvc2.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 1000, 10);
        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 2000, 5);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> items = itemRepository.findAll();

        Assertions.assertThat(items.size()).isEqualTo(2);
        Assertions.assertThat(items).contains(itemA, itemB);
    }

    @Test
    void update() {
        Item item = new Item("itemA", 1000, 10);
        Item savedItem = itemRepository.save(item);

        Item updateParams = new Item("itemUpdated", 2000, 30);
        itemRepository.update(savedItem.getId(), updateParams);

        Item findItem = itemRepository.findById(savedItem.getId());

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParams.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParams.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateParams.getQuantity());

    }
}