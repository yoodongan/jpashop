package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

}
